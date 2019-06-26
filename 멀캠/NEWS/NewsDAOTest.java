package model.dao;

import java.util.ArrayList;

import org.junit.Test;

import model.vo.MeetingVO;
import model.vo.NewsVO;

public class NewsDAOTest {

	@Test
	public void test() {
		NewsVO vo = new NewsVO();
		NewsDAO dao = new NewsDAO();
		ArrayList<NewsVO> al;
		
//		vo.setWriter("가나ㅏㄷ");
//		vo.setTitle("타이틀테스트");
//		vo.setContent("컨텐트테스트");
//		vo.setWritedate("2019-06-11T17:11");
//		vo.setCnt(0);
//		dao.insert(vo);
		al = (ArrayList<NewsVO>) dao.listAll();
		System.out.println("--------------------------------");
		System.out.println("Test ListAll");
		for (NewsVO data : al)
			System.out.println(data);
		
//		System.out.println("--------------------------------");
//		System.out.println("Test Delete");
//		System.out.println(dao.delete(3));
//		al = (ArrayList<NewsVO>) dao.listAll();
//		for (NewsVO data : al)
//			System.out.println(data);
		
		System.out.println("--------------------------------");
		System.out.println("Test ListOne");
		System.out.println(dao.listOne(4));
		
		System.out.println("--------------------------------");
		System.out.println("Test Update");
		vo = new NewsVO();
		vo.setWriter("라이터수정");
		vo.setTitle("타이틀수정");
		vo.setContent("컨텐트수정");
		vo.setWritedate("2018-06-11T22:33");
		vo.setCnt(3);
		vo.setId(2);
		System.out.println(dao.update(vo));
		al = (ArrayList<NewsVO>) dao.listAll();
		for (NewsVO data : al)
			System.out.println(data);
	}

}
