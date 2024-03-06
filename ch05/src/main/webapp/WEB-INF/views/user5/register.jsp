
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>user5::register</title>
    <link rel="stylesheet" href="/ch05/css/user.css">
    <link href="https://hangeul.pstatic.net/hangeul_static/css/NanumGeumEunBoHwa.css" rel="stylesheet">
</head>

<style>

</style>
<body>
    <div><h3>user5 등록</h3></div>
    <div>
        <a href="/ch05">처음으로</a>
    </div>
    <form action="/ch05/user5/register" method="post">
        <table border="1px">



            <tr>
                <td>이름</td>
                <td><input type="text" name="name"></td>
            </tr>

            <tr>
                <td>성별</td>
                <td><input type="text" name="gender"></td>
            </tr>

            <tr>
                <td>나이</td>
                <td><input type="number" name="age"></td>
            </tr>

            <tr>
                <td>주소</td>
                <td><input type="text" name="addr"></td>
            </tr>
            <tr>
                <td colspan="2" align="right"><input type="submit" value="등록"></td>
            </tr>
        </table>
    </form>
</body>
</html>
