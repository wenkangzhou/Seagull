package com.usernet.product.web.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.usernet.product.dao.FeedBackDao;
import com.usernet.product.entity.Upload;

public class UploadService extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private FeedBackDao feedbackDao = null;
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		feedbackDao = new FeedBackDao();
	}

	@Override
	public void destroy() {
		super.destroy();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		String title=request.getParameter("title");
		String authorname=request.getParameter("authorname");
		String link=request.getParameter("link");
		String otherfile=request.getParameter("otherfile");
		String description=request.getParameter("description");
		String nickname=request.getParameter("nickname");
		String contact=request.getParameter("contact");
		Upload upload = new Upload();
		upload.setFilename(title);
		upload.setAuthor(authorname);
		upload.setSource(link);
		upload.setAttachment(otherfile);
		upload.setRemark(description);
		upload.setNickname(nickname);
		upload.setContact(contact);
		upload.setTime(new Timestamp(System.currentTimeMillis()));
		try {
			feedbackDao.save(upload);
			writer.print(0);
		} catch (Exception e) {
			writer.print(1);
		}
		writer.flush();
	}
}
