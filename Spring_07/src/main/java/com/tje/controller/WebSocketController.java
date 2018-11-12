package com.tje.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebSocketController {

	@RequestMapping("/ws-echo")
	public String echo_ws(HttpSession session, HttpServletRequest request) {
		
											// 새로운 클라이언트가 들어올때 주소값 가져온다
		session.setAttribute("address", request.getRemoteAddr());
		return "websocket-echo";
	}
	
}
