<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" %>
<html>
<head>
    <title>lunch::register</title>
    <link rel="stylesheet" href="/ch05/css/user.css">
    <link href="https://hangeul.pstatic.net/hangeul_static/css/NanumGeumEunBoHwa.css" rel="stylesheet">
</head>

<style>

</style>
<body>
    <div><h3>lunch 수정</h3></div>
    <div>
        <a href="/ch05">처음으로</a>
    </div>
    <form action="/ch05/lunch/modify" method="post">
        <table border="1px">
            <tr>
                <td>이름</td>
                <td><input type="text" name="name" value="${lunch.name}"></td>
            </tr>

            <tr>
                <td>시간</td>
                <td><input type="text" name="time" value="${lunch.time}"></td>
            </tr>

            <tr>
                <td>종류</td>
                <td><input type="text" name="kind" value="${lunch.kind}"></td>
            </tr>

            <tr>
                <td>코멘트</td>
                <td><input type="text" name="comment" value="${lunch.comment}"></td>
            </tr>

            <tr>
                <td>별점</td>
                <td><input type="text" name="score" value="${lunch.score}"></td>
            </tr>

            <tr>
                <td colspan="2" align="right"><input type="submit" value="등록"></td>
            </tr>
        </table>
    </form>
</body>
</html>
