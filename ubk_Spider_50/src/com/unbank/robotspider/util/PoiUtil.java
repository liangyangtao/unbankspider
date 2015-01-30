package com.unbank.robotspider.util;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class PoiUtil {

	public static void write_Excel(String xls_write_Address,
			List<Map<String, String>> dayNums,
			List<Map<String, String>> webDayNums, int webNum) {
		try {

			GregorianCalendar calendar = new GregorianCalendar();
			calendar.add(GregorianCalendar.DATE, 0);
			Date date0 = calendar.getTime();
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
					"yyyy-MM-dd");
			String today = simpleDateFormat.format(date0);
			calendar.add(GregorianCalendar.DATE, -1);
			date0 = calendar.getTime();
			String yestday = simpleDateFormat.format(date0);

			calendar.add(GregorianCalendar.DATE, -1);
			date0 = calendar.getTime();
			String yes2day = simpleDateFormat.format(date0);

			calendar.add(GregorianCalendar.DATE, -1);
			date0 = calendar.getTime();
			String yes3day = simpleDateFormat.format(date0);

			calendar.add(GregorianCalendar.DATE, -1);
			date0 = calendar.getTime();
			String yest4day = simpleDateFormat.format(date0);

			calendar.add(GregorianCalendar.DATE, -1);
			date0 = calendar.getTime();
			String yest5day = simpleDateFormat.format(date0);

			FileOutputStream output = new FileOutputStream(new File(
					xls_write_Address));

			XSSFWorkbook wb = new XSSFWorkbook();
			XSSFSheet sheet = wb.createSheet("0");
			wb.setSheetName(0, "各网址现在抓取统计");
			XSSFSheet sheet1 = wb.createSheet("1");
			wb.setSheetName(1, "各网址一周抓取统计");
			XSSFSheet sheet2 = wb.createSheet("2");
			wb.setSheetName(2, "每日抓取数量统计");
			// XSSFSheet sheet3 = wb.createSheet("3");
			// wb.setSheetName(3, "无抓取内容网址");
			// 第一页的第一行
			XSSFRow row = sheet.createRow(0);
			XSSFCell cell = row.createCell(0);
			cell.setCellType(XSSFCell.CELL_TYPE_STRING);// 文本格式
			cell.setCellValue("抓取网址编号");// 写入内容
			XSSFCell cell1 = row.createCell(1);
			cell1.setCellType(XSSFCell.CELL_TYPE_STRING);// 文本格式
			cell1.setCellValue("网站名称");// 写入内容
			XSSFCell cell2 = row.createCell(2);
			cell2.setCellType(XSSFCell.CELL_TYPE_STRING);// 文本格式
			cell2.setCellValue("网址");// 写入内容
			XSSFCell cell3 = row.createCell(3);
			cell3.setCellType(XSSFCell.CELL_TYPE_STRING);// 文本格式
			cell3.setCellValue("抓取数量");// 写入内容

			int i = 0;
			for (int j = 0; j < webDayNums.size(); j++) {
				Map<String, String> map = webDayNums.get(j);
				if (map.get("crawl_time").equals(today)) {
					XSSFRow rowTemp = sheet.createRow(++i);
					XSSFCell cellTemp = rowTemp.createCell(0);
					cellTemp.setCellType(XSSFCell.CELL_TYPE_STRING);// 文本格式
					cellTemp.setCellValue(map.get("website_id"));// 写入内容

					XSSFCell cellTemp1 = rowTemp.createCell(1);
					cellTemp1.setCellType(XSSFCell.CELL_TYPE_STRING);// 文本格式
					cellTemp1.setCellValue(map.get("web_name"));// 写入内容

					XSSFCell cellTemp2 = rowTemp.createCell(2);
					cellTemp2.setCellType(XSSFCell.CELL_TYPE_STRING);// 文本格式
					cellTemp2.setCellValue(map.get("url_home"));// 写入内容

					XSSFCell cellTemp3 = rowTemp.createCell(3);
					cellTemp3.setCellType(XSSFCell.CELL_TYPE_STRING);// 文本格式
					cellTemp3.setCellValue(map.get("num"));// 写入内容
				}
			}

			// // 第二页
			XSSFRow row20 = sheet1.createRow(0);
			XSSFCell cell20 = row20.createCell(0);
			cell20.setCellType(XSSFCell.CELL_TYPE_STRING);// 文本格式
			cell20.setCellValue("抓取网址编号");// 写入内容
			XSSFCell cell21 = row20.createCell(1);
			cell21.setCellType(XSSFCell.CELL_TYPE_STRING);// 文本格式
			cell21.setCellValue("网址");// 写入内容

			XSSFCell cell22 = row20.createCell(2);
			cell22.setCellType(XSSFCell.CELL_TYPE_STRING);// 文本格式
			cell22.setCellValue(today);// 写入内容

			XSSFCell cell23 = row20.createCell(3);
			cell23.setCellType(XSSFCell.CELL_TYPE_STRING);// 文本格式
			cell23.setCellValue(yestday);// 写入内容

			XSSFCell cell24 = row20.createCell(4);
			cell24.setCellType(XSSFCell.CELL_TYPE_STRING);// 文本格式
			cell24.setCellValue(yes2day);// 写入内容

			XSSFCell cell25 = row20.createCell(5);
			cell25.setCellType(XSSFCell.CELL_TYPE_STRING);// 文本格式
			cell25.setCellValue(yes3day);// 写入内容

			XSSFCell cell26 = row20.createCell(6);
			cell26.setCellType(XSSFCell.CELL_TYPE_STRING);// 文本格式
			cell26.setCellValue(yest4day);// 写入内容

			XSSFCell cell27 = row20.createCell(7);
			cell27.setCellType(XSSFCell.CELL_TYPE_STRING);// 文本格式
			cell27.setCellValue(yest5day);// 写入内容

			String website = webDayNums.get(0).get("website_id");
			int rownum = 0;
			XSSFRow row21 = sheet1.createRow(++rownum);

			for (int j = 0; j < webDayNums.size(); j++) {
				String temp = webDayNums.get(j).get("website_id");

				if (website.equals(temp)) {

				} else {
					website = temp;
					row21 = sheet1.createRow(++rownum);
				}

				XSSFCell cell210 = row21.createCell(0);
				cell210.setCellType(XSSFCell.CELL_TYPE_STRING);// 文本格式
				cell210.setCellValue(temp);// 写入内容

				XSSFCell cell211 = row21.createCell(1);
				cell211.setCellType(XSSFCell.CELL_TYPE_STRING);// 文本格式
				cell211.setCellValue(webDayNums.get(j).get("url_home"));// 写入内容

				String crawlerTime = webDayNums.get(j).get("crawl_time");
				for (int k = 0; k < row20.getLastCellNum(); k++) {
					if (row20.getCell(k).getStringCellValue()
							.equals(crawlerTime)) {
						int columnNum = row20.getCell(k).getColumnIndex();
						XSSFCell cell2112 = row21.createCell(columnNum);
						cell2112.setCellType(XSSFCell.CELL_TYPE_STRING);// 文本格式
						cell2112.setCellValue(webDayNums.get(j).get("num"));// 写入内容
						break;
					} else {
						continue;
					}

				}

			}
			// 第三页
			XSSFRow row30 = sheet2.createRow(0);
			XSSFCell cell30 = row30.createCell(0);
			cell30.setCellType(XSSFCell.CELL_TYPE_STRING);// 文本格式
			cell30.setCellValue("抓取日期");// 写入内容
			XSSFCell cell31 = row30.createCell(1);
			cell31.setCellType(XSSFCell.CELL_TYPE_STRING);// 文本格式
			cell31.setCellValue("抓取数量");// 写入内容

			for (int j = 0; j < dayNums.size(); j++) {
				XSSFRow row30Temp = sheet2.createRow(j + 1);
				XSSFCell cell301 = row30Temp.createCell(0);
				cell301.setCellType(XSSFCell.CELL_TYPE_STRING);// 文本格式
				cell301.setCellValue(dayNums.get(j).get("crawl_time"));// 写入内容
				XSSFCell cell302 = row30Temp.createCell(1);
				cell302.setCellType(XSSFCell.CELL_TYPE_STRING);// 文本格式
				cell302.setCellValue(dayNums.get(j).get("num"));// 写入内容

			}
			// 第四页

			wb.write(output);
			output.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
