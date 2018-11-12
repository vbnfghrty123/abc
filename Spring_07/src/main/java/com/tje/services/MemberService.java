package com.tje.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tje.dao.MemberDAO;
import com.tje.model.Member;

@Service
public class MemberService {

	@Autowired
	private MemberDAO dao;

	public void setDao(MemberDAO dao) {
		this.dao = dao;
	}
	
	public int getRecordCount() {
		return this.dao.getRecordCount();
	}
	
	public List<Member> selectList(){
		return this.dao.getList();
	}
	
	public Member selectOne(Member member) {
		return this.dao.selectOne(member);
	}
	
	@Transactional
	public int insert(Member member) {
		this.dao.insert(member);
	return this.dao.insert(member);
	}
	
	
	public int deleteOne(Member member) {
	return this.dao.deleteOne(member);
	}
	
}
