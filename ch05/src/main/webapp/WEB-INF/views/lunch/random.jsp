
<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" %>
<html>
<head>
    <title>lunch :: random</title>
    <link rel="stylesheet" href="/ch05/css/user.css">
    <link href="https://hangeul.pstatic.net/hangeul_static/css/NanumGeumEunBoHwa.css" rel="stylesheet">
</head>
<body>
    <div>
        <p>오늘의 추천 메뉴는 바로</p>
        <p> ${lunch.name} 입니다</p>
    </div>
    <div>
        <table border="1px">
            <tr>
                <td>이름</td>
                <td><input type="text" name="name" value="${lunch.name}" readonly></td>
            </tr>

            <tr>
                <td>시간</td>
                <td><input type="text" name="time" value="${lunch.time}" readonly></td>
            </tr>

            <tr>
                <td>종류</td>
                <td><input type="text" name="kind" value="${lunch.kind}" readonly></td>
            </tr>

            <tr>
                <td>코멘트</td>
                <td><input type="text" name="comment" value="${lunch.comment}" readonly></td>
            </tr>

            <tr>
                <td>별점</td>
                <td><input type="text" name="score" value="${lunch.score}" readonly></td>
            </tr>
        </table>
    </div>
    <br>
    <div>
        <a href="/ch05/lunch/list">목록가기</a>
    </div>
</body>
</html>
