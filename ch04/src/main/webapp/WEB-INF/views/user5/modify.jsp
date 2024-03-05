<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" %>
<html>
<head>
    <title>user5::register</title>
    <link rel="stylesheet" href="/ch04/css/user1.css">
    <link href="https://hangeul.pstatic.net/hangeul_static/css/NanumGeumEunBoHwa.css" rel="stylesheet">
</head>

<style>

</style>
<body>
    <div><h3>user5 수정</h3></div>
    <div>
        <a href="/ch04">처음으로</a>
    </div>
    <form action="/ch04/user5/modify" method="post">
        <table border="1px">
            <tr>
                <td>번호</td>
                <td><input type="text" name="seq" readonly value="${user.seq}"></td>
            </tr>

            <tr>
                <td>이름</td>
                <td><input type="text" name="name" value="${user.name}"></td>
            </tr>

            <tr>
                <td>성별</td>
                <td><input type="text" name="gender" value="${user.gender}"></td>
            </tr>

            <tr>
                <td>나이</td>
                <td><input type="number" name="age" value="${user.age}"></td>
            </tr>

            <tr>
                <td>주소</td>
                <td><input type="text" name="addr" value="${user.addr}"></td>
            </tr>

            <tr>
                <td colspan="2" align="right"><input type="submit" value="등록"></td>
            </tr>
        </table>
    </form>
</body>
</html>
