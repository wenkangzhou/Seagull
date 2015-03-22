package com.usernet.product.web.action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import com.usernet.product.tools.Property;
import com.usernet.product.utils.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.FileUploadBase.SizeLimitExceededException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class FileUploadServlet extends HttpServlet {

	private static final long serialVersionUID = 8425528122006011510L;

	private final String PHOTO_SRC = Property.getPara("resources","photo_src");
	private final String MUSIC_COVER_SRC = Property.getPara("resources","music_cover_src");
	private final String MUSIC_FILE_SRC = Property.getPara("resources","music_file_src");
	private final String TAO_SRC = Property.getPara("resources","tao_src");
	private final String SUI_SRC = Property.getPara("resources","sui_src");
	private final String UPLOAD = Property.getPara("resources","upload");
	final long MAX_SIZE = 10 * 1024 * 1024;// 设置上传文件最大为 10M
	// 允许上传的文件格式的列表
	final String[] allowtype = new String[] { "jpg", "jpeg", "png","mp3","m4a"};

	public FileUploadServlet() {
		super();
	}

	public void destroy() {
		super.destroy();
	}

	@SuppressWarnings("deprecation")
	@Override
	protected void service(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		String type = request.getParameter("type");
		String time = request.getParameter("time");
		response.setContentType("text/html");
		// 设置字符编码为UTF-8, 这样支持汉字显示
		response.setCharacterEncoding("UTF-8");
		// 实例化一个硬盘文件工厂,用来配置上传组件ServletFileUpload
		DiskFileItemFactory dfif = new DiskFileItemFactory();
		dfif.setSizeThreshold(4096);// 设置上传文件时用于临时存放文件的内存大小,这里是4K.多于的部分将临时存在硬盘
		dfif.setRepository(new File(request.getRealPath("/") + "download"));// 设置存放临时文件的目录,web根目录下的download目录
		// 用以上工厂实例化上传组件
		ServletFileUpload sfu = new ServletFileUpload(dfif);
		// 设置最大上传尺寸
		sfu.setSizeMax(MAX_SIZE);
		PrintWriter out = response.getWriter();
		// 从request得到 所有 上传域的列表
		@SuppressWarnings("rawtypes")
		List fileList = null;
		try {
			fileList = sfu.parseRequest(request);
		} catch (FileUploadException e) {// 处理文件尺寸过大异常
			if (e instanceof SizeLimitExceededException) {
				out.println("{message:'文件尺寸超过规定大小:" + MAX_SIZE + "字节'}");
				return;
			}
			e.printStackTrace();
		}
		// 没有文件上传
		if (fileList == null || fileList.size() == 0) {
			out.println("{message:'请选择上传文件'}");
			return;
		}
		// 得到所有上传的文件
		@SuppressWarnings("rawtypes")
		Iterator fileItr = fileList.iterator();
		// 循环处理所有文件
		while (fileItr.hasNext()) {
			FileItem fileItem = null;
			String path = null;
			long size = 0;
			// 得到当前文件
			fileItem = (FileItem) fileItr.next();
			// 忽略简单form字段而不是上传域的文件域(<input type="text" />等)
			if (fileItem == null || fileItem.isFormField()) {
				continue;
			}
			// 得到文件的完整路径
			path = fileItem.getName();
			// 得到文件的大小
			size = fileItem.getSize();
			if ("".equals(path) || size == 0) {
				out.println("{message:'请选择上传文件'}");
				return;
			}
			// 得到去除路径的文件名
			String t_name = path.substring(path.lastIndexOf("\\") + 1);
			// 得到文件的扩展名(无扩展名时将得到全名)
			String t_ext = t_name.substring(t_name.lastIndexOf(".") + 1);
			// 拒绝接受规定文件格式之外的文件类型
			int allowFlag = 0;
			int allowedExtCount = allowtype.length;
			for (; allowFlag < allowedExtCount; allowFlag++) {
				if (allowtype[allowFlag].equals(t_ext))
					break;
			}
			
			if (allowFlag == allowedExtCount) {
				String message = "";
				for (allowFlag = 0; allowFlag < allowedExtCount; allowFlag++) {
					message += "*." + allowtype[allowFlag] + " ";
				}
				out.println("{message:'请上传以下类型的文件" + message + "'}");
				return;
			}

		//	String fileName = StringUtils.getUUID() + "." + t_ext;
		//	fileName = t_name;
			String fileName = "";
			if(type.equals("photo"))
				fileName = "photo_"+time.split("-")[0]+time.split("-")[1]+time.split("-")[2]+"."+t_ext;
			if(type.equals("music"))
				fileName = "music_"+time.split("-")[0]+time.split("-")[1]+time.split("-")[2]+"."+t_ext;
			if(type.equals("tao"))
				fileName = "tao_"+time.split("-")[0]+time.split("-")[1]+time.split("-")[2]+"."+t_ext;
			if(type.equals("sui"))
				fileName = "sui_"+time.split("-")[0]+time.split("-")[1]+time.split("-")[2]+"."+t_ext;
			if(type.equals("m4a"))
				fileName = time.split("-")[0]+time.split("-")[1]+time.split("-")[2]+"."+t_ext;
			if(type.equals("typeicon"))
				fileName = "sui_title_"+time.split("-")[0]+time.split("-")[1]+time.split("-")[2]+"."+t_ext;
			if(type.equals("other"))
				fileName = StringUtils.getUUID() + "." + t_ext;
			// 原来的文件名
		//	path = UPLOAD_DIR + "/" + fileName;
			if(type.equals("photo"))
				path = PHOTO_SRC + "/" +  fileName;
			if(type.equals("music"))
				path = MUSIC_COVER_SRC + "/" +  fileName;
			if(type.equals("tao"))
				path = TAO_SRC + "/" +  fileName;
			if(type.equals("sui")||type.equals("typeicon"))
				path = SUI_SRC + "/" +  fileName;
			if(type.equals("m4a"))
				path = MUSIC_FILE_SRC + "/" +  fileName;
			if(type.equals("other"))
				path = UPLOAD + "/" +  fileName;	
			//path = "E:/TOMCAT/dyml/webapps/ROOT/img/photo" + "/" +  fileName;
			try {
				// 保存文件
				fileItem.write(new File(path));
				response.setStatus(200);
				out.println("{message:\"文件上传成功. 已保存为: " + fileName + " 文件大小: " + size + "字节\",fileName:\"" + fileName + "\"}");
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
	}

}
