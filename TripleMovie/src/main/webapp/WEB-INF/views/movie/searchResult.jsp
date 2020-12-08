<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<link rel="stylesheet" href="/res/css/searchresult.css">
<div class="search_container">
	<!-- 밑에 text, button 연결좀 -->
	<form action="/movie/searchResult">
		<input type="text" name="movieNm" class="searchTerm" placeholder="영화명을 작성해주세요.">
		<button type="submit" class="searchButton">검색</button>
	</form>
	<c:forEach items="${movieList.data[0].result}" var="item">
		<div class="search_box" onclick="test('${item.movieId}','${item.movieSeq}')" class="search_box">
			<table class="search_table">
				<tr>
					<td rowspan="3" class="search_image">
						<c:choose>
							<c:when test="${item.posters == ''}">
								<img src="https://ssl.pstatic.net/static/movie/2012/06/dft_img203x290.png">
							</c:when>
							<c:otherwise>
								<img src="${item.posters}">
							</c:otherwise>
						</c:choose>	
					</td>
					<td colspan="4" class="search_title">
					<c:set var="string1" value="${fn:replace(item.title, '!HE', '')}" />
					<c:set var="string2" value="${fn:replace(string1, '!HS', '')}" />
						${string2}<span>(${item.rating})</span>
					</td>
				</tr>
				<tr>
					<td>
						장르 : ${item.genre}
					</td>
					<td>
						감독 : ${item.directors.director[0].directorNm}
					</td>
					<td>
						${item.nation}
					</td>
				</tr>
				<tr>
					<td>
						상영시간 : ${item.runtime}
					</td>
					<td colspan="2">
						출연진 : 
						<c:forEach items="${item.actors.actor}" end="2" var="actor">
							${actor.actorNm} 
						</c:forEach>	
					</td>
				</tr>
			</table>			
		</div>
	</c:forEach>
</div>

<script type="text/javascript">
	function test(movieId,movieSeq){
		location.href = '/movie/detail?movieId=' + movieId + "&movieSeq="+movieSeq ;
	}
</script>
