<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link rel="stylesheet" href="/res/css/login.css">
<div class="body">
	<div class="grad"></div>
	<div class="header">
		<div>Movie<span>Travel</span></div>
	</div>
	<br>
	<form action="/user/login" class="login" method="post">
		<input type="text" placeholder="username" name="user"><br>
		<input type="password" placeholder="password" name="password"><br>
		<input type="button" value="Login">
	</form>
</div>
	