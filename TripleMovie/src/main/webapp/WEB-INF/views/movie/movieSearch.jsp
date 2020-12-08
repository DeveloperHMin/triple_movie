<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<link rel="stylesheet" href="/res/css/search.css">
<div class="search_container">
    <div class="search_box">
        <div class="search_title">
            <h1>MOVIE SEARCH</h1>
        </div>
        <div class="search">
        	<form action="/movie/searchResult">
	            <input type="text" name="movieNm" class="searchTerm" placeholder="영화명을 작성해주세요.">
	            <button type="submit" class="searchButton">검색</button>
            </form>
        </div>
    </div>
</div>