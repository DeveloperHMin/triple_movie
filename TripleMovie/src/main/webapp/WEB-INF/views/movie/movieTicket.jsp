<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<link rel="stylesheet" href="/res/css/ticket.css">

<div class="ticket_container">
	<form action="/movie/movieSheets" id="frm" >
	<input type="hidden" name="i_movie" value="0">
	<section class="day_select">
		<header class="title">
			<span>날짜선택</span>
		</header>
		<c:forEach items="${movieDay}" var="movieDay" varStatus="i">
			<div class="day_div" id="day_btn${i.index}" onclick="choiceDay(${movieDay.r_dt},${i.index})">
				<span id="day_week${i.index}">
						<fmt:parseDate var="chkInDayString" value="${movieDay.r_dt}" pattern="yyyyMMdd" />
						<fmt:formatDate value="${chkInDayString}" pattern="MM/dd (E)"/>
				</span>
			</div>
		</c:forEach>
	</section>
	<section class="movie_select">
		<header class="title">
			<span>영화선택</span>
		</header>
	</section>
	<section class="cinema_select">
		<header class="title">
			<span>관선택</span>
		</header>
	</section>
	<section class="time_select">
		<header class="title">
			<span>시간선택</span>
		</header>
		<!-- foreach문 -->
		<div id="time" class="time_div">
		</div>
	
	</section>
	<div id="submit" class="ticketing_submit">
	</div>
	</form>
<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
<script type="text/javascript">

	var v_movieNm;
	var v_movieId;
	var v_movieSeq;
	var v_room;
	var v_r_dt;
	var v_s_dt;
	var v_e_dt;
	var cancel_idx;

//날짜 선택시 함수
function choiceDay(r_dt,idx){
	
	axios.get('/movie/selMovieNm',{
		params:{
			r_dt:r_dt
		}
	}).then(function(res){
		time.innerHTML = ''
		submit.innerHTML = ''
		
		for(let i=0; i<3; i++){
			dayBtn = document.getElementById('day_btn'+i)
			if(i == idx){
				dayBtn.style.backgroundColor = '#bebebe'
			}else{
				dayBtn.style.backgroundColor = 'white'
			}
		}
			
		
		for(let i=0; i<5; i++){
			movieNm = document.getElementById("movie_btn" + i); 
			if(movieNm == null){break;}
			movieNm.remove();
		}
		
		
		for(let i=1; i<5;i++ ){
			roomBtn = document.getElementById('room_btn'+i)
			if(roomBtn == null){break;}
			roomBtn.remove();
		}
		
		v_r_dt = r_dt
		let section =  document.querySelector('.movie_select')
		for(var i=0; i< res.data.length; i++){
			let div=document.createElement('div')
			div.setAttribute('id', 'movie_btn' + i)
			div.setAttribute('class', 'movie_div')
			div.setAttribute('onclick', 'choiceMv(' + '"' + res.data[i].movieSeq+ '"' + ','  + '"' + res.data[i].movieId + '"' +  ',' + i + ',' + '"'+ res.data[i].movieNm + '"' + ')')
			
			let span= document.createElement('span')
			span.innerHTML = res.data[i].movieNm
			div.appendChild(span)
			
			section.appendChild(div)
		}
	})
	


	
}

//영화 선택시 함수
function choiceMv(movieSeq, movieId, idx, movieNm){
	
	axios.get('/movie/ajaxSelRoom',{
		params:{
			r_dt:v_r_dt,
			movieId:movieId,
			movieSeq:movieSeq
		}
		}).then(function(res){
			time.innerHTML = ''
			submit.innerHTML = ''
			
			
			for(let i=1; i<5;i++ ){
				roomBtn = document.getElementById('room_btn'+i)
				if(roomBtn == null){break;}
				roomBtn.remove();
			}
			
			for(let i=0; i<5;i++ ){
				movieBtn = document.getElementById('movie_btn'+i)
				if(i == idx){
					movieBtn.style.backgroundColor = '#bebebe'
				}else{
					movieBtn.style.backgroundColor = 'white'
				}
			}
			
			v_movieNm = movieNm
			let section =  document.querySelector('.cinema_select')
			for(let i=0; i<res.data.length; i++){
				let div=document.createElement('div');
				div.setAttribute('id', 'room_btn' + (i+1))
				div.setAttribute('class', 'cinema_div')
				div.setAttribute('onclick', 'choiceRoom(' + res.data[i].room  + ',' + (i+1) + ')')
				
				let span =document.createElement('span');
				span.innerHTML = res.data[i].room + '관'
				
				div.appendChild(span)
				
				section.appendChild(div)
			}
		})
	
}
// 관선택시 함수
function choiceRoom(room, idx){
	
		time.innerHTML = ''
		v_room = room;
		roomBtn = document.getElementById('room_btn'+ idx)
		console.log(roomBtn)
		cancelRoom = document.getElementById('room_btn'+ cancel_idx)
		console.log(cancelRoom)
		if(roomBtn != null){
			roomBtn.style.backgroundColor = '#bebebe'
			if(cancelRoom != null && cancel_idx != idx){
				cancelRoom.style.backgroundColor= 'white'	
			}
			selTime(room)
		}
		
		cancel_idx = idx;

}

function selTime(room){
	var r_dt = v_r_dt;
	var room = room;
	var movieNm = v_movieNm;
	
	axios.get('/movie/ajaxSelTime',{
		params:{
			room : room,
			r_dt : r_dt,
			movieNm:movieNm
		}
	}).then(function(res){
		time.innerHTML = ''
		for(let i=0; i<res.data.length; i++){
			time_btn = document.createElement('div')
			time_btn.id = 'time_btn'+i
			time_btn.setAttribute('onclick','choiceTime('+ res.data[i].s_dt + ',' + i +','+res.data.length +  ',' + res.data[i].e_dt + ',' + res.data[i].i_movie + ')')
			time_span = document.createElement('span')
			time_span.innerHTML = res.data[i].sTime + ' ~ ' + res.data[i].eTime
			time_btn.appendChild(time_span)
			time.appendChild(time_btn)
		}
	})
}

//시간 선택시 함수
function choiceTime(s_dt, idx, length, e_dt, i_movie){
	submit.innerHTML = ''
	frm.i_movie.value = i_movie;
	console.log('i_movie: ' + i_movie)
	for(let i=0; i<length;i++ ){
		timeBtn = document.getElementById('time_btn'+i)
		console.log("i: " + i)
		console.log("idx: " + idx)
		if(i == idx){
			timeBtn.style.backgroundColor = '#bebebe'
			submit_btn = document.createElement('input')
			submit_btn.type = 'submit'
			submit_btn.value = '예매하기'
			submit.appendChild(submit_btn)
		}else{
			timeBtn.style.backgroundColor= 'white'
		}
	}
	
}
</script>
</div>
