<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h1>전화번호 등록</h1>

	<p>
		전화번호를 등록 하려면<br>
		아래 항목을 기입하고 "등록" 버튼을 클릭하세요.
	</p>
	
	<form action="/phonebook3/phone/modify" method="get">
		이름(name): <input type="text" name="name" value="${requestScope.personVo.name}"><br>
		핸드폰(hp): <input type="text" name="hp" value="${personVo.hp}"><br>
		회사(company): <input type="text" name="company" value="${personVo.company}"><br>
		id:<input type="text" name="id" value="${personVo.personId}"><br>
		
		<button type="submit">수정</button>
	</form>
	
	<br>
	<a href="/WEB-INF/phone/views/modify">리스트 바로 가기</a>
	
	
</body>
</html>