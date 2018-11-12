<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="<c:url value='/resources/js/jquery-3.3.1.min.js'/>"></script>
<%-- 															웹 소켓을 사용할 수 있도록 하는 script --%>
<script src="https://cdn.jsdelivr.net/npm/sockjs-client@1/dist/sockjs.min.js"></script>
</head>
<body>

	<input type="text" id="message" />
	<input type="button" id="sendBtn" value="전송" />
	<div id="data"></div>


<script type="text/javascript">
	$(document).ready(function() {
		$("#sendBtn").click(function() {
			sendMessage();
			$('#message').val('');
		});

		$("#message").keydown(function(key) {
			// 엔터키 입력 감지
			if (key.keyCode == 13) {
				sendMessage();
				// 전송 버튼 클릭 시 입력한 내용들 전송textfield 에서 사라지게
				$('#message').val('');
			}
		});

	});

	// 웹소켓을 지정한 url로 연결한다.
							// mvc-config 에서 입력한 /echo
	let sock = new SockJS("<c:url value="/echo"/>");
	// 콜백함수
	sock.onmessage = onMessage;
	sock.onclose = onClose;

	// 메시지 전송
	function sendMessage() {
		sock.send($("#message").val());
	}

	// 서버로부터 메시지를 받았을 때
	function onMessage(msg) {
		var data = msg.data;
		$("#data").append(data + "<br/>");
	}

	// 서버와 연결을 끊었을 때
	function onClose(evt) {
		$("#data").append("연결 끊김");
	}
</script>

</body>
</html>