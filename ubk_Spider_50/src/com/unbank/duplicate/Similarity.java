package com.unbank.duplicate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.unbank.duplicate.entity.SimBean;
import com.unbank.duplicate.util.ConnectionFactory;
import com.unbank.duplicate.util.Parameter;
import com.unbank.duplicate.util.PoolManager;
import com.unbank.duplicate.util.Shingling;
import com.unbank.duplicate.util.publicTools;

public class Similarity {
	public static List<SimBean> simHashlist=new ArrayList<SimBean>();
	// 日志
	public static Logger logger = Logger.getLogger(Similarity.class);
	/**
	 * @param args
	 */
	public static void pancheng() {
		logger.info("判重-----------------"+simHashlist.size());
		Connection conn=null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ConnectionFactory dbConFactory = new ConnectionFactory();
		PoolManager dmgr = PoolManager.getInstance();
		Statement qtstmt = null;
		
	    try{
	    	conn = dbConFactory.getConnection("form2");
			conn.setAutoCommit(false);
		    long start=System.currentTimeMillis();
		    ps = conn.prepareStatement("select p2.*,p1.crawl_title from ptf_crawl p1 LEFT JOIN ptf_crawl_text p2 on p1.crawl_id=p2.crawl_id where p1.file_index=7 and p1.task=2 order by p1.crawl_id desc limit 500 ");
			rs = ps.executeQuery();
			List<Map<String,Object>> stateList=new ArrayList<Map<String,Object>>();
			while (rs.next()){
				int id =rs.getInt("crawl_id");
				String text=rs.getString("text");
				String title=rs.getString("crawl_title");
				//标题去标点和空格
				title=title.replaceAll("[\\pP' ']", "");
				//内容去掉html标签
				text=delHTMLTag(text);
				//内容去掉标点、空格、回车
				text=text.replaceAll("[\\pP' '\n]", "");
				
				boolean stopflag=false;
				SimBean sb=new SimBean();
				sb.setId(id);
				sb.setTitle(title);
				Set<String> mergedSet=new HashSet<String>();
				mergedSet.addAll(Shingling.getNGramList(text, Parameter.shinglingwordsvalue));
				sb.setText(mergedSet);
				
				SimBean sb1=null;
				
				double bijiaozhi=0;

				for(int i=0;i<simHashlist.size();i++){
					sb1 = simHashlist.get(i);
					//标题相同字数打到20%可以比较
					if(RSAC(sb1.getTitle(),sb.getTitle())>=0.2){
						
						bijiaozhi=Shingling.jaccardIndex(sb1.getText(), sb.getText());
						//相似比例打到阀值0.3视为相同
						if(bijiaozhi>0.3){
							System.out.println(sb1.getTitle()+"==========="+sb.getTitle());
							stopflag=true;
							break;
						}
					}
					if(sb1.getTitle().equals(sb.getTitle())){
						bijiaozhi=10;
						stopflag=true;
						break;
					}
				}
				
				Map<String,Object> statemap=new HashMap<String,Object>();
				if(stopflag){
					statemap.put("crawl_id", id);
					statemap.put("state", 8);
					statemap.put("maincrawl_id", sb1.getId());//System.out.println("1-------"+bijiaozhi);
					statemap.put("deviation", bijiaozhi);
					
					stateList.add(statemap);
					
					
				}else{
					statemap.put("crawl_id", id);
					statemap.put("state", 0);
					statemap.put("maincrawl_id", id);
					statemap.put("deviation", -1);
					
					stateList.add(statemap);
					
					//始终保持比较源3万条数据
					simHashlist.remove(0);
					simHashlist.add(sb);
				}
			}
			ps.close();
		    rs.close();
		    long end=System.currentTimeMillis();
		    
		    System.out.println("比较耗时============="+(end-start));
		    //批量插入
		    batchaddstate(conn,stateList);
		    batchupdatestate(conn,stateList);
		    
		    conn.commit();
		}catch(Exception e){
			publicTools.loggerException(logger, e);
        	try {conn.rollback();} catch (SQLException e1) {}
		}finally{
			if(conn!=null)try {conn.close();} catch (SQLException e) {}
		}
	}
	/**
	 * 每天加载一次比较数据*/
	public static void loaddata(){
		Connection conn=null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ConnectionFactory dbConFactory = new ConnectionFactory();
		PoolManager dmgr = PoolManager.getInstance();
		Statement qtstmt = null;
		
	    try{
	    	simHashlist.clear();
		    long start=System.currentTimeMillis();
			conn = dbConFactory.getConnection("form2");
			conn.setAutoCommit(true);
//			ps = conn.prepareStatement("select * from (select p2.*,p1.crawl_title from ptf_crawl p1 LEFT JOIN ptf_crawl_text p2 on p1.crawl_id=p2.crawl_id where p1.crawl_time<'"+publicTools.GetToday()+"' order by p1.crawl_id desc limit 5000) a order by crawl_id");
			ps = conn.prepareStatement("select * from (select p2.*,p1.crawl_title from ptf_crawl p1 LEFT JOIN ptf_crawl_text p2 on p1.crawl_id=p2.crawl_id where (p1.file_index=0 or p1.file_index=1) and p1.task=2 order by p1.crawl_id desc limit 10000) a order by crawl_id");
			rs = ps.executeQuery();
			while (rs.next()){
				int id =rs.getInt("crawl_id");
				String text=rs.getString("text");
				String title=rs.getString("crawl_title");
				try{
					if(text!=null){
						//标题去标点和空格
						title=title.replaceAll("[\\pP' ']", "");
						//内容去掉html标签
						text=delHTMLTag(text);
						//内容去掉标点、空格、回车
						text=text.replaceAll("[\\pP' '\n]", "");
						
						SimBean sb=new SimBean();
						sb.setId(id);
						sb.setTitle(title);
						
						Set<String> mergedSet=new HashSet<String>();
						mergedSet.addAll(Shingling.getNGramList(text, Parameter.shinglingwordsvalue));
						sb.setText(mergedSet);
						
						simHashlist.add(sb);
					}
				}catch(Exception e){
					e.printStackTrace();
				}
				
			}
			ps.close();
		    rs.close();
		    long end=System.currentTimeMillis();
		    System.out.println("加载耗时============="+(end-start));
		}catch(Exception e){
			publicTools.loggerException(logger, e);
		}finally{
			if(conn!=null)try {conn.close();} catch (SQLException e) {}
		}
	}
	private static final String regEx_script = "<script[^>]*?>[\\s\\S]*?<\\/script>"; // 定义script的正则表达式
    private static final String regEx_style = "<style[^>]*?>[\\s\\S]*?<\\/style>"; // 定义style的正则表达式
    private static final String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式
    
    public static String delHTMLTag(String htmlStr) {
        Pattern p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
        Matcher m_script = p_script.matcher(htmlStr);
        htmlStr = m_script.replaceAll(""); // 过滤script标签

        Pattern p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
        Matcher m_style = p_style.matcher(htmlStr);
        htmlStr = m_style.replaceAll(""); // 过滤style标签

        Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
        Matcher m_html = p_html.matcher(htmlStr);
        htmlStr = m_html.replaceAll(""); // 过滤html标签

        return htmlStr.trim(); // 返回文本字符串
    }
	//批量插入
	public static void batchaddstate(Connection con,List<Map<String,Object>> stateList) throws Exception {
	    try{
			PreparedStatement ps=null ;
			String sql = "INSERT INTO ptf_crawl_state (crawl_id,maincrawl_id,deviation) VALUES(?,?,?) ";
		    ps=con.prepareStatement(sql);
		    for(int i=0;i<stateList.size();i++) {
		    		Map<String,Object> map=stateList.get(i);
		    		ps.setInt(1, (Integer)map.get("crawl_id"));
		    		//ps.setString(2, (String)map.get("state"));//System.out.println("2-------"+(Double)map.get("deviation"));
		    		ps.setInt(2, (Integer)map.get("maincrawl_id"));
		    		ps.setString(3, map.get("deviation")+"");
		    		
					if(i%500==0)
						ps.executeBatch();
					
					ps.addBatch();
			}
			ps.executeBatch();
			ps.close();
			
		}catch(Exception e){
			publicTools.loggerException(logger, e);
        	throw new Exception("批量插入状态失败。");
		}
	}
	//批量插入
	public static void batchupdatestate(Connection con,List<Map<String,Object>> stateList) throws Exception {
		
	    try{
			PreparedStatement ps=null ;
			String sql = "update ptf_crawl set  file_index=? where task=2 and crawl_id=?";
		    ps=con.prepareStatement(sql);
		    for(int i=0;i<stateList.size();i++) {
		    		Map<String,Object> map=stateList.get(i);
		    		ps.setInt(1, (Integer)map.get("state"));
		    		//ps.setString(2, (String)map.get("state"));//System.out.println("2-------"+(Double)map.get("deviation"));
		    		ps.setInt(2, (Integer)map.get("crawl_id"));
		    		
					if(i%500==0)
						ps.executeBatch();
					
					ps.addBatch();
			}
			ps.executeBatch();
			ps.close();
			
		}catch(Exception e){
			publicTools.loggerException(logger, e);
        	throw new Exception("批量修改状态失败。");
		}
	}
	/**
     * 两个字符串的相同率*/
    public static float RSAC(String str1,String str2){
    	List<String> list=new ArrayList<String>();
    	for (int k = 0; k < str1.length(); k++) {
			for (int j = 0; j < str2.length(); j++) {
				if (str1.charAt(k) == str2.charAt(j) && !isExist(list, str1.substring(k, k + 1))){
					list.add(str1.substring(k, k + 1));
					break;
				}
			}
		}

		float size = (float) (list.size()*2) / (str1.length()+str2.length());
		DecimalFormat df = new DecimalFormat("0.00");// 格式化小数，不足的补0
		String ratiostr = df.format(size);// 返回的是String类型的
		float ratio = Float.parseFloat(ratiostr);
		return ratio;
    }
    /**
     * 是否存在相同*/
    private static boolean isExist(List<String> list, String dest) {
		for (String s : list) {
			if (dest.equals(s))
				return true;
		}
		return false;
	}
}
