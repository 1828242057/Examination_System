package com.system.service.impl;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.system.po.ExcelBean;
import com.system.service.CourseService;
import com.system.service.ExcelInfo;
import com.system.util.ExcelUtil;

@Service
public class stuExportCourseInfo implements ExcelInfo{   //学生导出课程信息  未实现 缺少数据库支持
	
	
	@Resource(name = "courseServiceImpl")
    private CourseService courseService;
	@Override
	public XSSFWorkbook exportExcelInfo() throws Exception{
		//根据条件查询数据
		//这里需要数据库配合写新的查询语句 返回map 具体看 https://blog.csdn.net/w_m_5/article/details/80815712
		/*List<Map<String,Object>> list = sysExcelDao.findUserObject();*/
		//System.out.println(list);
		List<ExcelBean> excel = new ArrayList<>();
		Map<Integer,List<ExcelBean>> map = new LinkedHashMap<>();
		//设置标题栏
		excel.add(new ExcelBean("序号","id",0));
		excel.add(new ExcelBean("用户名","username",0));
		excel.add(new ExcelBean("部门", "deptName", 0));
		excel.add(new ExcelBean("邮箱","email",0));
		excel.add(new ExcelBean("电话","mobile",0));
		excel.add(new ExcelBean("状态","valid", 0));
		excel.add(new ExcelBean("创建时间","createdTime",0));
		excel.add(new ExcelBean("修改时间","modifiedTime",0));
		map.put(0,excel);
		String sheetName = "用户信息表";
		//调用ExcelUtil方法
		//XSSFWorkbook xssfWorkbook = ExcelUtil.createExcelFile(SysUser.class, list, map, sheetName);
		//System.out.println(xssfWorkbook);
		return xssfWorkbook;
	}
}
