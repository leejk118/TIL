package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.NewsDAO;
import model.vo.NewsVO;

@WebServlet("/news")
public class NewsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 String action = request.getParameter("action");
		 String url = "/jspexam/news.jsp";
		 
		 NewsDAO dao = new NewsDAO();
		 if (action == null) {
			 request.setAttribute("list", dao.listAll());
		 }
		 else if (action.equals("read")) {
			 int newsId = Integer.parseInt(request.getParameter("newsid"));
			 NewsVO vo = dao.listOne(newsId);
			 if (vo != null) {
				 vo.setCnt(vo.getCnt() + 1);
				 dao.update(vo);
				 request.setAttribute("readVO", vo);
			 }
		 }
		 else if (action.equals("delete")) {
			 int newsId = Integer.parseInt(request.getParameter("newsid"));
			 if (dao.delete(newsId)) request.setAttribute("msg", "삭제 성공");
			 else request.setAttribute("msg", "삭제 실패");
		 }
		 request.setAttribute("list", dao.listAll());
		 request.getRequestDispatcher(url).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String writer = request.getParameter("writer");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String action = request.getParameter("action");
		String writedate = request.getParameter("writedate");
		String url = "/jspexam/news.jsp";
		
		NewsDAO dao = new NewsDAO();
		NewsVO vo = new NewsVO();
		vo.setWriter(writer);
		vo.setTitle(title);
		vo.setContent(content);
		vo.setWritedate(writedate);
		
		if (action != null && action.equals("insert")) {
			if (dao.insert(vo)) request.setAttribute("msg", "작성 성공");
			else request.setAttribute("msg", "작성 실패");
		}
		else if (action.equals("update")){
			int newsid = Integer.parseInt(request.getParameter("newsid"));
			vo = dao.listOne(newsid);
			vo.setWriter(writer);
			vo.setTitle(title);
			vo.setContent(content);
			vo.setWritedate(writedate);
			vo.setCnt(vo.getCnt() + 1);
			if (dao.update(vo)) request.setAttribute("msg", " 수정 성공");
			else request.setAttribute("msg", " 수정 실패");
		}
		request.setAttribute("list", dao.listAll());
		request.getRequestDispatcher(url).forward(request, response);
	}

}
