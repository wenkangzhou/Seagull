package com.usernet.product.web.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.usernet.product.dao.FeedBackDao;
import com.usernet.product.entity.FeedBack;

public class FeedBackService extends HttpServlet {

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
		String mes=request.getParameter("mes");
		String contact=request.getParameter("contact");
		String myTime=request.getParameter("myTime");
		FeedBack feedback = new FeedBack();
		feedback.setContact(contact);
		feedback.setMsgcontent(mes);
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
		try {
			feedback.setTime(new Timestamp(sdf.parse(myTime).getTime()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		try {
			feedbackDao.save(feedback);
			writer.print(0);
		} catch (Exception e) {
			writer.print(1);
		}
		writer.flush();
	}
}
