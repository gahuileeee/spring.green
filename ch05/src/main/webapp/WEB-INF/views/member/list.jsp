
<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>member:: list</title>
    <link rel="stylesheet" href="/ch05/css/user.css">
    <link href="https://hangeul.pstatic.net/hangeul_static/css/NanumGeumEunBoHwa.css" rel="stylesheet">
</head>
<style>

</style>
<body>
        <div><h3>member 목록</h3></div>
        <div>
            <a href="/ch05">처음으로</a>
            <a href="/ch05/member/register">등록</a>
        </div>
        <br>

        <div>
            <form action="/ch05/member/search" method="get">
                <select name="type">
                    <option value="uid">아이디</option>
                    <option value="name">이름</option>
                    <option value="hp">휴대폰</option>
                </select>
                <input type="text" name="value">
                <p>
                    <label><input type="checkbox" name="pos" value="사원">사원</label>
                    <label><input type="checkbox" name="pos" value="대리">대리</label>
                    <label><input type="checkbox" name="pos" value="과장">과장</label>
                    <label><input type="checkbox" name="pos" value="차장">차장</label>
                    <label><input type="checkbox" name="pos" value="부장">부장</label>
                </p>
                <input type="submit" value="검색">
            </form>
        </div>

        <table border="1px">
            <tr>
                <th>아이디</th>
                <th>이름</th>
                <th>휴대폰</th>
                <th>직급</th>
                <th>부서</th>
                <th>입사일</th>
                <th>관리</th>
            </tr>
            <c:forEach var="member" items="${members}">
            <tr>
                <td>${member.uid}</td>
                <td>${member.name}</td>
                <td>${member.hp}</td>
                <td>${member.pos}</td>
                <td>${member.dep}</td>
                <td>${member.rdate.substring(0,10)}</td>
                <td>
                    <a href="/ch05/member/modify?uid=${member.uid}">수정</a>
                    <a href="/ch05/member/delete?uid=${member.uid}">삭제</a>
                </td>
            </tr>
            </c:forEach>
        </table>
</body>
</html>
