package com.wx.servlet;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
/**
 * 处理文件下载请求
 * @author dxw
 *
 */
public class DownloadFileServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String fileName = req.getParameter("fileName");
		//对文件名称进行编码
		resp.setHeader("Content-Disposition", "attachment;filename = "+URLEncoder.encode(fileName, "UTF-8").replace("\\+", "%20"));
		ServletContext context = this.getServletContext();
		String realPath = context.getRealPath("/chatFile/"+fileName);
		FileInputStream is = new FileInputStream(realPath);
		OutputStream os = resp.getOutputStream();
		IOUtils.copy(is, os);
		
		os.close();
		is.close();
	}
}
