package com.wx.servlet;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;
/**
 * 处理文件上传请求
 * @author dxw
 *
 */
public class UploadFileServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		uploadHeadImg(req,resp);
	}
	private void uploadHeadImg(HttpServletRequest req, HttpServletResponse resp) {
		//设置文件在浏览器中只能下载，不直接打开
		resp.setHeader("Content-Disposition", "attachment;");
		ServletContext context = this.getServletContext();
		FileItemFactory factory = new DiskFileItemFactory();	
		ServletFileUpload upload = new ServletFileUpload(factory);
		try {
			List<FileItem> items =  upload.parseRequest(req);
			String realPaths = context.getRealPath("/chatFile");
			for (FileItem fileItem : items) {
				String path = realPaths+"/"+fileItem.getName();
				InputStream is = fileItem.getInputStream();
				FileOutputStream os = new FileOutputStream(path);
				IOUtils.copy(is, os);
				os.close();
				is.close();
				resp.getWriter().write(fileItem.getName());
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

}
