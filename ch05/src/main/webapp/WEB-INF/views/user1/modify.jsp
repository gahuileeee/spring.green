<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" %>
<html>
<head>
    <title>user1::register</title>
    <link rel="stylesheet" href="/ch05/css/user.css">
    <link href="https://hangeul.pstatic.net/hangeul_static/css/NanumGeumEunBoHwa.css" rel="stylesheet">
</head>

<style>

</style>
<body>
    <div><h3>user1 수정</h3></div>
    <div>
        <a href="/ch05">처음으로</a>
    </div>
    <form action="/ch05/user1/modify" method="post">
        <table border="1px">
            <tr>
                <td>아이디</td>
                <td><input type="text" name="uid" readonly value="${user.uid}"></td>
            </tr>

            <tr>
                <td>이름</td>
                <td><input type="text" name="name" value="${user.name}"></td>
            </tr>

            <tr>
                <td>생년월일</td>
                <td><input type="date" name="birth" value="${user.birth}"></td>
            </tr>

            <tr>
                <td>휴대폰</td>
                <td><input type="text" name="hp" value="${user.hp}"></td>
            </tr>

            <tr>
                <td>나이</td>
                <td><input type="number" name="age" value="${user.age}"></td>
            </tr>
            <tr>
                <td colspan="2" align="right"><input type="submit" value="등록"></td>
            </tr>
        </table>
    </form>
</body>
</html>
