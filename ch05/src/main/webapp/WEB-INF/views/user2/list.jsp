
<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>user2:: list</title>
    <link rel="stylesheet" href="/ch05/css/user.css">
    <link href="https://hangeul.pstatic.net/hangeul_static/css/NanumGeumEunBoHwa.css" rel="stylesheet">
</head>
<style>
</style>
<body>
        <div><h3>user2 목록</h3></div>
        <div>
            <a href="/ch05">처음으로</a>
            <a href="/ch05/user2/register">등록</a>
        </div>

        <table border="1px">
            <tr>
                <th>아이디</th>
                <th>이름</th>
                <th>생년월일</th>
                <th>주소</th>
                <th>관리</th>
            </tr>
            <c:forEach var="user" items="${users}">
            <tr>
                <td>${user.uid}</td>
                <td>${user.name}</td>
                <td>${user.birth}</td>
                <td>${user.addr}</td>
                <td>
                    <a href="/ch05/user2/modify?uid=${user.uid}">수정</a>
                    <a href="/ch05/user2/delete?uid=${user.uid}">삭제</a>
                </td>
            </tr>
            </c:forEach>
        </table>
</body>
</html>
