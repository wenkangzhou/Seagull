package com.usernet.product.web.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.usernet.product.dao.MusicDao;
import com.usernet.product.dao.PhotoDao;
import com.usernet.product.dao.SuiDao;
import com.usernet.product.dao.TaoDao;
import com.usernet.product.entity.Music;
import com.usernet.product.entity.Photo;
import com.usernet.product.entity.Sui;
import com.usernet.product.entity.Tao;
import com.usernet.product.utils.ProductUtils;
public class GetDataService extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private PhotoDao photoDao = null;
	private MusicDao musicDao = null;
	private TaoDao taoDao = null;
	private SuiDao suiDao = null;
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		photoDao = new PhotoDao();
		musicDao = new MusicDao();
		taoDao = new TaoDao();
		suiDao = new SuiDao();
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
		String n=request.getParameter("n");
		String nowdate=request.getParameter("nowdate");
		String aryDate[] = new String[3]; 
		aryDate = nowdate.split("-");
		@SuppressWarnings("deprecation")
		Date d=new Date(Integer.parseInt(aryDate[0])-1900,Integer.parseInt(aryDate[1])-1,Integer.parseInt(aryDate[2]));   
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");  
		String beforedate = df.format(new Date(d.getTime() - (long)(Integer.parseInt(n)-1) * 24 * 60 * 60 * 1000));
		String type=request.getParameter("type");
		if(type.equals("photo")){
			List<Photo> list =  photoDao.getData(nowdate,beforedate);
			StringBuffer buf = new StringBuffer();
			buf.append("[");
			for (int i = 0; i < list.size(); i++) {
				Photo photo = list.get(i);
				buf.append("{");
				buf.append("\"time\":\"");
				buf.append(photo.getTime());
				buf.append("\",\"vol\":\"");
				buf.append(photo.getVol());
				buf.append("\",\"src\":\"");
				buf.append(photo.getSrc());
				buf.append("\",\"name\":\"");   //JSONTools.string2Json()
				buf.append(photo.getName());
				buf.append("\",\"author\":\"");
				buf.append(photo.getAuthor());
				buf.append("\",\"quotation\":\"");
				buf.append(photo.getQuotation());
				buf.append("\"}");
				if (i < list.size() - 1) {
					buf.append(",");
				}
			}
			buf.append("]");
			String ret = ProductUtils.getResJson(buf.toString());		
			writer.print(ret);
		}else if(type.equals("music")){
			List<Music> list =  musicDao.getData(nowdate,beforedate);
			StringBuffer buf = new StringBuffer();
			buf.append("[");
			for (int i = 0; i < list.size(); i++) {
				Music music = list.get(i);
				buf.append("{");
				buf.append("\"time\":\"");
				buf.append(music.getTime());
				buf.append("\",\"vol\":\"");
				buf.append(music.getVol());
				buf.append("\",\"coversrc\":\"");
				buf.append(music.getCoversrc());
				buf.append("\",\"name\":\"");   //JSONTools.string2Json()
				buf.append(music.getName());
				buf.append("\",\"author\":\"");
				buf.append(music.getAuthor());
				buf.append("\",\"musicsrc\":\"");
				buf.append(music.getMusicsrc());
				buf.append("\"}");
				if (i < list.size() - 1) {
					buf.append(",");
				}
			}
			buf.append("]");
			String ret = ProductUtils.getResJson(buf.toString());		
			writer.print(ret);
		}else if(type.equals("tao")){
			List<Tao> list =  taoDao.getData(nowdate,beforedate);
			StringBuffer buf = new StringBuffer();
			buf.append("[");
			for (int i = 0; i < list.size(); i++) {
				Tao tao = list.get(i);
				buf.append("{");
				buf.append("\"time\":\"");
				buf.append(tao.getTime());
				buf.append("\",\"vol\":\"");
				buf.append(tao.getVol());
				buf.append("\",\"coversrc\":\"");
				buf.append(tao.getCoversrc());
				buf.append("\",\"etc\":\"");   //JSONTools.string2Json()
				buf.append(tao.getEtc());
				buf.append("\",\"websrc\":\"");
				buf.append(tao.getWebsrc());
				buf.append("\"}");
				if (i < list.size() - 1) {
					buf.append(",");
				}
			}
			buf.append("]");
			String ret = ProductUtils.getResJson(buf.toString());		
			writer.print(ret);
		}else{
			List<Sui> list =  suiDao.getData(nowdate,beforedate);
			StringBuffer buf = new StringBuffer();
			buf.append("[");
			for (int i = 0; i < list.size(); i++) {
				Sui sui = list.get(i);
				buf.append("{");
				buf.append("\"time\":\"");
				buf.append(sui.getTime());
				buf.append("\",\"vol\":\"");
				buf.append(sui.getVol());
				buf.append("\",\"coversrc\":\"");
				buf.append(sui.getCoversrc());
				buf.append("\",\"etc\":\"");   //JSONTools.string2Json()
				buf.append(sui.getEtc());
				buf.append("\",\"websrc\":\"");
				buf.append(sui.getWebsrc());
				buf.append("\",\"type\":\"");
				buf.append(sui.getType());
				buf.append("\",\"typesrc\":\"");
				buf.append(sui.getTypesrc());
				buf.append("\",\"title\":\"");
				buf.append(sui.getTitle());
				buf.append("\"}");
				if (i < list.size() - 1) {
					buf.append(",");
				}
			}
			buf.append("]");
			String ret = ProductUtils.getResJson(buf.toString());		
			writer.print(ret);
		}
		writer.flush();
	}
}
