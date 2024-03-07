
<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>lunch:: list</title>
    <link rel="stylesheet" href="/ch05/css/user.css">
    <link href="https://hangeul.pstatic.net/hangeul_static/css/NanumGeumEunBoHwa.css" rel="stylesheet">
</head>
<style>
</style>
<body>
        <div><h3>lunch 목록</h3></div>
        <div>
            <a href="/ch05">처음으로</a>
            <a href="/ch05/lunch/register">등록</a>
        </div>

        <table border="1px">
            <tr>
                <th>이름</th>
                <th>종류</th>
                <th>코멘트</th>
                <th>이동시간</th>
                <th>별점</th>
                <th>관리</th>
            </tr>
            <c:forEach var="lunch" items="${lunches}">
            <tr>
                <td>${lunch.name}</td>
                <td>${lunch.kind}</td>
                <td>${lunch.comment}</td>
                <td>${lunch.time} 분</td>
                <td>${lunch.score}</td>
                <td>
                    <a href="/ch05/lunch/modify?seq=${lunch.seq}">수정</a>
                    <a href="/ch05/lunch/delete?seq=${lunch.seq}">삭제</a>
                </td>
            </tr>
            </c:forEach>
        </table>
</body>
</html>
