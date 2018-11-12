package com.tje.websocket;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.*;
							// extends 로 override 불러오기
public class EchoHandler extends TextWebSocketHandler {	
				// WebScoket 라이브러리 사용해 접속한경우 WebSocketSession 만들어짐
	private List<WebSocketSession> sessionList = new ArrayList<WebSocketSession>();

	// 클라이언트와 연결 이후에 실행되는 메서드
	@Override			
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		sessionList.add(session);
		// System.out.printf("%s 연결됨\n", session.getId());
		// httpsession 에 저장된 정보를 websocketsession 에서 사용 (<websocket:handshake-interceptors>) 에 의해 httpSession 에서 getAttribute
		Map<String,Object> attrMap = session.getAttributes();
		System.out.printf("%s 연결됨\n", attrMap.get("address"));
		
	}

	// 클라이언트가 서버로 메시지를 전송했을 때 실행되는 메서드
	@Override						
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		
		// System.out.printf("%s로 부터 %s 받음\n", session.getId(), message.getPayload());
		Map<String, Object> attrMap = session.getAttributes();
		System.out.printf("%s로 부터 %s 받음\n", attrMap.get("address"), message.getPayload());
					// 모든 클라이언트에게 뿌리는 메소드
		for (WebSocketSession sess : sessionList) {
			// sess.sendMessage(new TextMessage(session.getId() + " : " + message.getPayload()));
			sess.sendMessage(new TextMessage(attrMap.get("address") + " : " + message.getPayload()));
		}
	}

	// 클라이언트와 연결을 끊었을 때 실행되는 메소드
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		sessionList.remove(session);
		// System.out.printf("%s 연결 끊김\n", session.getId());
		Map<String,Object> attrMap = session.getAttributes();
		System.out.printf("%s 연결 끊김\n", attrMap.get("address"));
		
	}
}
