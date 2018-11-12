package com.tje.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tje.model.Member;

@Repository
public class MemberDAO {
	/*
	 * 이미 application-config.xml 에서 bean 으로 등록해놔 @Autrowired 사용
	 */
	@Autowired
	private SqlSessionTemplate sqlSession;

	private String strNameSpace = "com.tje.model.MemberMapper";

	public void setSqlSession(SqlSessionTemplate sqlSession) {
		this.sqlSession = sqlSession;
	}

	public int getRecordCount() {
								// 결과가 여러개가아니라 한개일때 One
		return sqlSession.selectOne("com.tje.model.MemberMapper.count");
	}

	public List<Member> getList() {
		return sqlSession.selectList(strNameSpace + ".allRecord");
	}

	public Member selectOne(Member member) {
		return sqlSession.selectOne("com.tje.model.MemberMapper.oneRecord",member);
	}
	
	public int insert(Member member) {
		return sqlSession.insert(strNameSpace+".insert",member);
	}
	
	public int deleteOne(Member member) {
		return sqlSession.delete(strNameSpace+".deleteOne",member);
	}

}
