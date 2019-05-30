package com.system.service;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
/* 导出需要实现该接口*/
public interface ExcelInfo {
	XSSFWorkbook exportExcelInfo() throws Exception;
}
