
<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>user1:: list</title>
    <link rel="stylesheet" href="/ch04/css/user1.css">
    <link href="https://hangeul.pstatic.net/hangeul_static/css/NanumGeumEunBoHwa.css" rel="stylesheet">
</head>
<style>
</style>
<body>
        <div><h3>user1 목록</h3></div>
        <div>
            <a href="/ch04">처음으로</a>
            <a href="/ch04/user1/register">등록</a>
        </div>

        <table border="1px">

            <tr>
                <th>아이디</th>
                <th>이름</th>
                <th>생년월일</th>
                <th>휴대폰</th>
                <th>나이</th>
                <th>관리</th>
            </tr>
            <c:forEach var="user" items="${users}">
            <tr>
                <td>${user.uid}</td>
                <td>${user.name}</td>
                <td>${user.birth}</td>
                <td>${user.hp}</td>
                <td>${user.age}</td>
                <td>
                    <a href="/ch04/user1/modify?uid=${user.uid}">수정</a>
                    <a href="/ch04/user1/delete?uid=${user.uid}">삭제</a>
                </td>
            </tr>
            </c:forEach>
        </table>
</body>
</html>
