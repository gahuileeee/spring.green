<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<!--
    날짜: 2024/03/05
    이름: 김철학
    내용: Spring MVC 실습하기

    Spring MVC 라이브러리
    - spring- context 6.1.4
    - spring-webmvc 6.1.4
    - jakarta.servlet-api 6.0.0
    - jakarta.servlet.jsp.jstl-api 3.0.0
    - jakarta.servlet.jsp.jstl 3.0.1

    * 반드시 톰캣은 apache-tomcat-10.1.19으로 해야됨

    WebApplicationInitializer
    - 스프링 웹 애플리케이션 초기화를 위한 인터페이스
    - DispatcherServlet 을 생성하고 컨테이너에 등록
    - 웹 애플리케이션 컨텍스트(컨테이너)를 설정하고 필요한 서블릿, 필터, 리스너 등 설정

    WebMVCConfigurer (AppConfig에)
    - 스프링 웹 MVC 구성 컴포넌트 설정을 위한 인터페이스
    - 뷰리졸버(ViewResolver) 설정 및 ResourceHandler 설정 등 애플리케이션 전반에 걸친 자원 설정

    @EnableWebMVC
    - 스프링 MVC를 구성하고 MVC 관련 기능을 활성화하는 어노테이션

    tomcat 관련 설정
    - 반드시 톰켓은 apache-tomcat-19.1.19으로 해야함

-->
<link href="https://hangeul.pstatic.net/hangeul_static/css/NanumGeumEunBoHwa.css" rel="stylesheet">
<style>
    *{
        font-family: 'NanumGeumEunBoHwa';
        font-weight: bold;
        font-size: 32px;
    }

    div {
        width: fit-content;
        margin: 0  auto;
        text-align: center;
    }
    h3{
        color: #be8484;
    }
    a {
        display: inline-block;
        border:  1px solid #cc9191;
        margin: 0 10px;
        padding: 5px;
        text-decoration: none;
       color: #e28899;
        font-weight: bold;
        font-size: large;
    }
</style>
<body>
<div><h2>ch04.Spring MVC</h2></div>
    <div>
        <a href="/ch04/hello">hello</a>
        <a href="/ch04/welcome">welcome</a>
        <a href="/ch04/greeting">greeting</a>
    </div>

    <div>
        <h3>user1 실습</h3>
        <a href="/ch04/user1/register">user1 등록</a>
        <a href="/ch04/user1/list">user1 리스트</a>
    </div>

<div>
    <h3>user2 실습</h3>
    <a href="/ch04/user2/register">user2 등록</a>
    <a href="/ch04/user2/list">user2 리스트</a>
</div>

<div>
    <h3>user3 실습</h3>
    <a href="/ch04/user3/register">user3 등록</a>
    <a href="/ch04/user3/list">user3 리스트</a>
</div>

<div>
    <h3>user4 실습</h3>
    <a href="/ch04/user4/register">user4 등록</a>
    <a href="/ch04/user4/list">user4 리스트</a>
</div>

<div>
    <h3>user5 실습</h3>
    <a href="/ch04/user5/register">user5 등록</a>
    <a href="/ch04/user5/list">user5 리스트</a>
</div>


</body>
</html>
