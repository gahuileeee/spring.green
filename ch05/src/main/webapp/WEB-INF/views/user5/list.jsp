
<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>user5:: list</title>
    <link rel="stylesheet" href="/ch05/css/user.css">
    <link href="https://hangeul.pstatic.net/hangeul_static/css/NanumGeumEunBoHwa.css" rel="stylesheet">
</head>
<style>
</style>
<body>
        <div><h3>user5 목록</h3></div>
        <div>
            <a href="/ch05">처음으로</a>
            <a href="/ch05/user5/register">등록</a>
        </div>

        <table border="1px">
            <tr>
                <th>아이디</th>
                <th>이름</th>
                <th>성별</th>
                <th>나이</th>
                <th>주소</th>
                <th>관리</th>
            </tr>
            <c:forEach var="user" items="${users}">
            <tr>
                <td>${user.seq}</td>
                <td>${user.name}</td>
                <td>${user.gender}</td>
                <td>${user.age}</td>
                <td>${user.addr}</td>
                <td>
                    <a href="/ch05/user5/modify?seq=${user.seq}">수정</a>
                    <a href="/ch05/user5/delete?seq=${user.seq}">삭제</a>
                </td>
            </tr>
            </c:forEach>
        </table>
</body>
</html>
