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

public class UploadImgServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		uploadHeadImg(req,resp);
	}
	//上传头像
	private void uploadHeadImg(HttpServletRequest req, HttpServletResponse resp) {
		
		ServletContext context = this.getServletContext();
		FileItemFactory factory = new DiskFileItemFactory();	
		ServletFileUpload upload = new ServletFileUpload(factory);
		try {
			List<FileItem> items =  upload.parseRequest(req);
			String realPaths = context.getRealPath("/headImg");
			String value = "";
			for (FileItem fileItem : items) {
				if(fileItem.isFormField()){//获取文件名称
					value = fileItem.getString();
					
				}else{
					System.out.println("文件");
					String path = realPaths+"/"+value+".jpg";
					InputStream is = fileItem.getInputStream();
					FileOutputStream os = new FileOutputStream(path);
					IOUtils.copy(is, os);
					os.close();
					is.close();
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
}
