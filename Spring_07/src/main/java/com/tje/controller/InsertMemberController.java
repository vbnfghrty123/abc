package com.tje.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tje.model.Member;

@Controller
public class InsertMemberController {

	@Autowired
	private com.tje.services.MemberService service;

	@RequestMapping("/member/insert")
	public String insertMember() {
		Member member = new Member();
		member.setId("eee");
		member.setPw("E123Q");
		member.setName("ENAME");
		service.insert(member);
		System.out.println("insert");
		return "showMessage";
	}
}
