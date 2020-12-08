<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>TripleMovie</title>
    <link rel="stylesheet" href="/res/css/temp.css">
    <link rel="stylesheet" href="/res/css/modal.css">
</head>
<body>
    <div class="temp_container">
        <div class="container categorycolor topBotomBordersOut">
            <div id="popup1" class="overlay">
				<div class="popup">
					<h2>로그인</h2>
					<a class="close" href="#">&times;</a>
					<div class="content">
				      <form action="/user/login" method="post">
					      <input type="text" name="user_id" placeholder="id">
					      <input type="password" name="user_pw" placeholder="pw">
				      	  <button>로그인</button>
				      </form>
				    </div>
				</div>
			</div>
            <div class="top_menubox">
                <a href="/movie/movieSearch">영화검색</a>
                <!-- c:if !로그인 > 로그인 and 로그인 > 로그아웃 -->
				<c:choose>
					<c:when test="${loginUser.nick_nm == null}">
						<a href="#popup1">로그인</a>
                		<a href="/user/join">회원가입</a>
					</c:when>
					<c:otherwise>
					    <a href="/movie/movieTicket">영화예매</a>
					    <a href="/user/myPage">예매내역</a>
						<a href="/user/logout">로그아웃</a>
					</c:otherwise>
				</c:choose>
            </div>
        </div>
        <header>
            <a href="/movie/main"><img src="/res/img/logo.png"></a>
        </header>
        <main>
        	<jsp:include page="/WEB-INF/views/${view}.jsp"></jsp:include>
        </main>
        <footer>
            <span> &copy;트리플메이커Movie Corp. </span>
        </footer>
    </div>
</body>
<script type="text/javascript">
	if(`${data.msg}` != ''){
		location.href="#popup1"
		alert(`${data.msg}`)
	}
</script>
</html>