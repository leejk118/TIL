package model.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import model.vo.NewsVO;

@Repository
public class NewsDAO {
	@Autowired
	SqlSession session = null;
	
	public List<NewsVO> listAll(){
		List<NewsVO> li = null;
		String statement = "resource.NewsMapper.selectNews";
		li = session.selectList(statement);
		return li;
	}
	public NewsVO listOne(int id) {
		NewsVO vo = null;
		String statement = "resource.NewsMapper.listOneNews";
		vo = session.selectOne(statement, id);
		return vo;
	}
	public boolean delete(int id) {
		String statement = "resource.NewsMapper.deleteNews";
		if (session.delete(statement, id) != 1) return false;
		return true;
	}
	public List<NewsVO> search(String key, String searchType){
		List<NewsVO> li = null;
		Map<String, String> map = new HashMap<String, String>();
		map.put("key", key);
		map.put("searchType", searchType);
		String statement = "resource.NewsMapper.searchNews";
		li = session.selectList(statement, map);
		return li;
	}
	public List<NewsVO> listWriter(String writer){
		System.out.println(writer);
		List<NewsVO> li = null;
		String statement = "resource.NewsMapper.writerNews";
		li = session.selectList(statement, writer);
		return li;
	}
	//--------------------------------------------------------------------------//
	public boolean insert(NewsVO vo) {
		String statement = "resource.NewsMapper.insertNews";
		if (session.insert(statement, vo) != 1) return false;
		return true;
	}
	public boolean update(NewsVO vo) {
		String statement = "resource.NewsMapper.updateNews";
		if (session.update(statement, vo) != 1) return false;
		return true;
	}
}
