
<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>user4:: list</title>
    <link rel="stylesheet" href="/ch04/css/user1.css">
    <link href="https://hangeul.pstatic.net/hangeul_static/css/NanumGeumEunBoHwa.css" rel="stylesheet">
</head>
<style>
</style>
<body>
        <div><h3>user4 목록</h3></div>
        <div>
            <a href="/ch04">처음으로</a>
            <a href="/ch04/user4/register">등록</a>
        </div>

        <table border="1px">
            <tr>
                <th>아이디</th>
                <th>이름</th>
                <th>성별</th>
                <th>나이</th>
                <th>휴대폰번호</th>
                <th>주소</th>
                <th>관리</th>
            </tr>
            <c:forEach var="user" items="${users}">
            <tr>
                <td>${user.uid}</td>
                <td>${user.name}</td>
                <td>${user.gender}</td>
                <td>${user.age}</td>
                <td>${user.hp}</td>
                <td>${user.addr}</td>
                <td>
                    <a href="/ch04/user4/modify?uid=${user.uid}">수정</a>
                    <a href="/ch04/user4/delete?uid=${user.uid}">삭제</a>
                </td>
            </tr>
            </c:forEach>
        </table>
</body>
</html>
