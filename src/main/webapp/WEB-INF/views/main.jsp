<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
</head>
<body>

<h1> URL 링크 단축 서비스 </h1>
<form action="http://localhost:8080/shortenedURL" method="POST">
    <input type="text" name="originalURL" placeholder="단축할 주소를 입력하세요">
    <input type="submit" value="링크 단축하기">
</form>
</body>
</html>