package com.huashu.busi.pm.dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import jxl.Workbook;
import jxl.write.WritableCell;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class ExcelUtil {

	public ExcelUtil() {

	}
	
	public static void exportExcel(OutputStream os,String title,Object[] head,Object[][] data){
		WritableWorkbook wwb = null;
		try {
			wwb = Workbook.createWorkbook(os);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (wwb != null) {
			// 创建一个可写入的工作表
			// Workbook的createSheet方法有两个参数，第一个是工作表的名称，第二个是工作表在工作薄中的位置
			WritableSheet ws = wwb.createSheet(title, 0);
			
			writeExcel(ws, 0, title,head.length);
			writeExcel(ws, 1, head);
			writeExcel(ws, 2, data);
			try {
				// 从内存中写入文件中
				wwb.write();
				// 关闭资源，释放内存
				wwb.close();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (WriteException e) {
				e.printStackTrace();
			}
		}
	}
	
	private static void writeExcel(WritableSheet ws, int startRow, Object[][] data) {
		// 下面开始添加单元格
		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < data[i].length; j++) {
				// 这里需要注意的是，在Excel中，第一个参数表示列，第二个表示行
				try {
					// 将生成的单元格添加到工作表中
					ws.addCell(parseData(j, i + startRow, data[i][j]));
				} catch (RowsExceededException e) {
					e.printStackTrace();
				} catch (WriteException e) {
					e.printStackTrace();
				}

			}
		}
	}

	private static void writeExcel(WritableSheet ws, int startRow, Object[] data) {
		// 下面开始添加单元格
		try {
			for (int i = 0; i < data.length; i++) {
				// 将生成的单元格添加到工作表中
				ws.addCell(parseData(i, startRow, data[i]));
			}
		} catch (RowsExceededException e) {
			e.printStackTrace();
		} catch (WriteException e) {
			e.printStackTrace();
		}
	}
	
	private static void writeExcel(WritableSheet ws, int startRow, String data,int colspan) {
		// 下面开始添加单元格
		try {
			ws.addCell(parseData(0, startRow, data));
			ws.mergeCells(0, 0, colspan-1, 0); // 合并单元格
		} catch (RowsExceededException e) {
			e.printStackTrace();
		} catch (WriteException e) {
			e.printStackTrace();
		}
	}
	private static WritableCell parseData(int colum,int row,Object o){
		if(String.class.isInstance(o)){
			return new jxl.write.Label(colum,row,(String)o);
		}else{
			return new jxl.write.Label(colum,row,o.toString());
		}
	}
	public static void main(String[] args){
		Object[][] data = new Object[][]{{1,2,3,4,5},{3,4,6,7,4},{8,5,3,2,6},{3,5,2,1,23}};
		try {
			new ExcelUtil().exportExcel(new FileOutputStream(new File("E:\\test.xls")), "test",new Object[]{"一","二","三","四","五",},data);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
