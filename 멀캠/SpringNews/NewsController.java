package edu.spring.springnews;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import model.dao.NewsDAO;
import model.vo.NewsVO;

@Controller
public class NewsController {
	@Autowired
	NewsDAO dao;
	
	@RequestMapping(value="/news", method=RequestMethod.GET )
	public ModelAndView get(NewsVO vo, String action, String newsid, String key, String searchType) {
		ModelAndView mav = new ModelAndView();
		if (action == null) mav.addObject("list", dao.listAll());
		else if (action.equals("listOne")) {
			int newsId = Integer.parseInt(newsid);
			vo = dao.listOne(newsId);
			if (vo != null) {
				vo.setCnt(vo.getCnt() + 1);
				dao.update(vo);
				mav.addObject("readVO", vo);
			}
			mav.addObject("list", dao.listAll());
		} 
		else if (action.equals("delete")) {
			int newsId = Integer.parseInt(newsid);
			if (dao.delete(newsId))
				mav.addObject("msg", "삭제 완료");
			else
				mav.addObject("msg", "삭제 실패");
			mav.addObject("list", dao.listAll());
		} 
		else if (action.equals("writer")) mav.addObject("list", dao.listWriter(vo.getWriter()));
		else if (action.equals("search")) mav.addObject("list", dao.search(key, searchType));
		
		mav.setViewName("news");
		return mav;
	}
	
	@RequestMapping(value="/news", method=RequestMethod.POST)
	public ModelAndView post(NewsVO vo, String action, String newsid) {
		ModelAndView mav = new ModelAndView();
		String writer = vo.getWriter();
		String title = vo.getTitle();
		String content = vo.getContent();
		String writedate = vo.getWritedate();
		vo.setWriter(writer);
		vo.setTitle(title);
		vo.setContent(content);
		vo.setWritedate(writedate);
		
		if (action != null && action.equals("insert")) {
			if (dao.insert(vo)) mav.addObject("msg", "작성 완료");
			else mav.addObject("msg", "작성 실패");
		}
		else if (action.equals("update")){
			int newsId = Integer.parseInt(newsid);
			vo = dao.listOne(newsId);
			vo.setWriter(writer);
			vo.setTitle(title);
			vo.setContent(content);
			vo.setWritedate(writedate);
			vo.setCnt(vo.getCnt() + 1);
			if (dao.update(vo)) mav.addObject("msg", "수정 완료");
			else mav.addObject("msg", "수정 실패");
		}
		mav.addObject("list", dao.listAll());

		mav.setViewName("news");
		return mav;
	}

}
