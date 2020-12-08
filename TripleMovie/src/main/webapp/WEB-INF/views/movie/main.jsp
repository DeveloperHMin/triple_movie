<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<link rel="stylesheet" href="../package/swiper-bundle.min.css">
<link rel="stylesheet" href="/res/css/slide.css">
<div class="swiper-container">
	<div class="swiper-wrapper">
		<div class="main_title">
			<p>박스오피스</p>
		</div>
		<div class="swiper-slide">
		<!--  -->
			<c:forEach	items="${movieList}" var="movieList">
				<table>
					<tr>
						<td colspan="2">
							 <img src="${movieList.data[0].result[0].posters }" class="poster" onclick="toDetail('${movieList.data[0].result[0].movieSeq}','${movieList.data[0].result[0].movieId}')">
						</td>
					</tr>
					<tr>
						<td class="title">
							<p>${movieList.data[0].result[0].title}</p>
						</td>
					</tr>
					<c:if test="${loginUser != null}">
						<tr>
							<td colspan="2">
								<a href="/movie/movieTicket">
									<button type="button">예매하기</button>
								</a>
							</td>
						</tr>
					</c:if>
				</table>
				 
			</c:forEach>
		</div>		
	</div>
	<!-- Add Pagination -->
	<div class="swiper-pagination"></div>
</div>

<!-- Swiper JS -->
<script src="../package/swiper-bundle.min.js"></script>

<!-- Initialize Swiper -->
<script>
	if(`${successTicket}` != ''){
		alert(`${successTicket}`)
	}
	var swiper = new Swiper('.swiper-container', {
	pagination: {
			el: '.swiper-pagination',
		},
	});
	
	function toDetail(movieSeq, movieId){
		location.href = "/movie/detail?movieSeq="+movieSeq+"&movieId="+movieId
	}
</script>