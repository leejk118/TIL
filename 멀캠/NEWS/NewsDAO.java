package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.vo.NewsVO;

public class NewsDAO {
	private Connection connectDB() {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		Connection conn = null;
		try {
			conn = DriverManager.getConnection
					("jdbc:oracle:thin:@localhost:1521:xe", "jdbctest", "jdbctest");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	private void close(Connection conn, Statement stmt, ResultSet rs) {
		try {
			if (rs != null)
				rs.close();
			stmt.close();
			conn.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	public boolean insert(NewsVO vo) {
		Connection conn = connectDB();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		boolean result = true;
		String sql = "INSERT INTO NEWS VALUES (NEWS_SEQ.nextval, "
				+ "?, ?, ?, "
				+ "TO_DATE(?, 'yyyy-mm-dd\"T\"hh24:mi'), ?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getWriter());
			pstmt.setString(2, vo.getTitle());
			pstmt.setString(3, vo.getContent());
			pstmt.setString(4, vo.getWritedate());
			pstmt.setInt(5, vo.getCnt());
			pstmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
			result = false;
		}
		close(conn, pstmt, rs);
		return result;
	}
	public boolean update(NewsVO vo) {
		Connection conn = connectDB();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		boolean result = true;
		String sql = "UPDATE NEWS SET "
						+ " WRITER = ?, " 
						+ " TITLE = ?, "
						+ " CONTENT = ?, "
						+ " CNT = ?, "
						+ " WRITEDATE = TO_DATE(?, 'yyyy-mm-dd\"T\"hh24:mi') " 
						+ " WHERE ID = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getWriter());
			pstmt.setString(2, vo.getTitle());
			pstmt.setString(3, vo.getContent());
			pstmt.setInt(4, vo.getCnt());
			pstmt.setString(5, vo.getWritedate());
			pstmt.setInt(6, vo.getId());
			pstmt.executeUpdate();
		}
		catch (SQLException e) {
			e.printStackTrace();
			result = false;
		}
		close(conn, pstmt, rs);
		return result;
	}
	public boolean delete(int id) {
		Connection conn = connectDB();
		PreparedStatement pstmt = null;
		ResultSet rs = null; 
		
		boolean result = true;
		String sql = "DELETE FROM NEWS WHERE ID=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			int delNum = pstmt.executeUpdate();
			if (delNum != 1) result = false;
		}
		catch (SQLException e) {
			e.printStackTrace();
			result = false;
		}
		close(conn, pstmt, rs);
		return result;
	}
	public List<NewsVO> listAll(){
		ArrayList<NewsVO> al = new ArrayList<>();
		Connection conn = connectDB();
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT ID, WRITER, TITLE, CONTENT, WRITEDATE, CNT FROM NEWS";
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			NewsVO vo;
			while (rs.next()) {
				vo = new NewsVO();
				vo.setId(rs.getInt(1));
				vo.setWriter(rs.getString(2));
				vo.setTitle(rs.getString(3));
				vo.setContent(rs.getString(4));
				vo.setWritedate(rs.getString(5));
				vo.setCnt(rs.getInt(6));
				al.add(vo);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		close(conn, stmt, rs);
		return al;
	}
	public NewsVO listOne(int id) {
		Connection conn = connectDB();
		Statement stmt = null;
		ResultSet rs = null;
		NewsVO vo = null;
		String sql = "SELECT ID, WRITER, TITLE, CONTENT, TO_CHAR(WRITEDATE, 'yyyy-mm-dd\"T\"hh24:mi'), CNT "
					+ "FROM NEWS WHERE ID =" + id;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				vo = new NewsVO();
				vo.setId(rs.getInt(1));
				vo.setWriter(rs.getString(2));
				vo.setTitle(rs.getString(3));
				vo.setContent(rs.getString(4));
				vo.setWritedate(rs.getString(5));
				vo.setCnt(rs.getInt(6));
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		close(conn, stmt, rs);
		return vo;
	}
	
}
