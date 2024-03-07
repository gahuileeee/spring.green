<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" %>
<html>
<head>
    <title>member::register</title>
    <link rel="stylesheet" href="/ch05/css/user.css">
    <link href="https://hangeul.pstatic.net/hangeul_static/css/NanumGeumEunBoHwa.css" rel="stylesheet">
</head>

<style>

</style>
<body>
    <div><h3>member 수정</h3></div>
    <div>
        <a href="/ch05">처음으로</a>
    </div>
    <form action="/ch05/member/modify" method="post">
        <table border="1px">
            <tr>
                <td>아이디</td>
                <td><input type="text" name="uid" readonly value="${member.uid}"></td>
            </tr>

            <tr>
                <td>이름</td>
                <td><input type="text" name="name" value="${member.name}"></td>
            </tr>

            <tr>
                <td>휴대폰</td>
                <td><input type="text" name="hp" value="${member.hp}"></td>
            </tr>

            <tr>
                <td>직급</td>
                <td><input type="text" name="pos" value="${member.pos}"></td>
            </tr>

            <tr>
                <td>부서</td>
                <td><input type="text" name="dep" value="${member.dep}"></td>
            </tr>

            <tr>
                <td>입사일</td>
                <td><input type="text" name="rdate" value="${member.rdate}"></td>
            </tr>

            <tr>
                <td colspan="2" align="right"><input type="submit" value="등록"></td>
            </tr>
        </table>
    </form>
</body>
</html>
