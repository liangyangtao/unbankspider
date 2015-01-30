package com.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

public class MyWordExtractor {
//	/**
//	 * 通过XWPFWordExtractor访问XWPFDocument的内容
//	 * 
//	 * @throws Exception
//	 */
//	@Test
//	public void testReadByExtractor() throws Exception {
//		InputStream is = new FileInputStream("D:\\test.docx");
//		XWPFDocument doc = new XWPFDocument(is);
//		XWPFWordExtractor extractor = new XWPFWordExtractor(doc);
//		String text = extractor.getText();
//		System.out.println(text);
//		CoreProperties coreProps = extractor.getCoreProperties();
//		this.printCoreProperties(coreProps);
//		this.close(is);
//	}
//
//	  public void testReadByDoc() throws Exception {
//	      InputStream is = new FileInputStream("D:\\table.docx");
//	      XWPFDocument doc = new XWPFDocument(is);
//	      List<XWPFParagraph> paras = doc.getParagraphs();
//	      for (XWPFParagraph para : paras) {
//	         //当前段落的属性
////	       CTPPr pr = para.getCTP().getPPr();
//	         System.out.println(para.getText());
//	      }
//	      //获取文档中所有的表格
//	      List<XWPFTable> tables = doc.getTables();
//	      List<XWPFTableRow> rows;
//	      List<XWPFTableCell> cells;
//	      for (XWPFTable table : tables) {
//	         //表格属性
////	       CTTblPr pr = table.getCTTbl().getTblPr();
//	         //获取表格对应的行
//	         rows = table.getRows();
//	         for (XWPFTableRow row : rows) {
//	            //获取行对应的单元格
//	            cells = row.getTableCells();
//	            for (XWPFTableCell cell : cells) {
//	                System.out.println(cell.getText());;
//	            }
//	         }
//	      }
//	      this.close(is);
//	   }
	  
	
	
	
	public static void main(String[] args) {
		File file = new File("E:\\2014年9月24日数据源自测文档.docx");
		try {

//			InputStream is = new FileInputStream(file.getCanonicalPath());
//			XWPFDocument doc = new XWPFDocument(is);
//			XWPFWordExtractor extractor = new XWPFWordExtractor(doc);
//			String text = extractor.getText();
//			System.out.println(text);
//			is.close();

			// FileInputStream fis = new FileInputStream(file);
			// WordExtractor wordExtractor = new WordExtractor(fis);
			// System.out.println("【 使用getText()方法提取的Word文件的内容如下所示：】");
			// System.out.println(wordExtractor.getText());

			// XWPFDocument docx = new
			// XWPFDocument(POIXMLDocument.openPackage(file.getAbsolutePath()));
			// int pages =
			// docx.getProperties().getExtendedProperties().getUnderlyingProperties().getPages();//总页数
			// int characters =
			// docx.getProperties().getExtendedProperties().getUnderlyingProperties().getCharacters();//
			//
			// System.out.println(docx.get);
			
		      InputStream is = new FileInputStream(file.getCanonicalPath());
		      XWPFDocument doc = new XWPFDocument(is);
		      List<XWPFParagraph> paras = doc.getParagraphs();
		      for (XWPFParagraph para : paras) {
		         //当前段落的属性
//		       CTPPr pr = para.getCTP().getPPr();
		         System.out.println(para.getText());
		      }
		      //获取文档中所有的表格
		      List<XWPFTable> tables = doc.getTables();
		      List<XWPFTableRow> rows;
		      List<XWPFTableCell> cells;
		      for (XWPFTable table : tables) {
		         //表格属性
//		       CTTblPr pr = table.getCTTbl().getTblPr();
		         //获取表格对应的行
		         rows = table.getRows();
		         for (XWPFTableRow row : rows) {
		            //获取行对应的单元格
		            cells = row.getTableCells();
		            for (XWPFTableCell cell : cells) {
		                System.out.println(cell.getText());;
		            }
		         }
		      }
		      is.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
