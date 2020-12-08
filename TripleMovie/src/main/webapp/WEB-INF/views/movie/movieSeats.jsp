<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" href="/res/css/sheets.css">
<div class="sheet_container">
	<form id="frm" action="/movie/insTicket" method="post" onsubmit="return seatChk()">
		<div class="sheet_select">
			<table class="sheet_table">
				<tr>
					<td>성인</td>
					<td>청소년</td>
				</tr>
				<tr>
					<td>
						<select name="adultCnt" onchange="choicePer()">
							<option value="0">0</option>		
							<c:forEach begin="1" end="4" var="i">
								<option value="${i}">${i} 명</option>
							</c:forEach>
					    </select>
					</td>
					<td>
						<select name="studentCnt" onchange="choicePer()">
							<option value="0">0</option>
							<c:forEach begin="1" end="4" var="i">		
								<option value="${i}">${i} 명</option>
							</c:forEach>		
					    </select>
					</td>
				</tr>
			</table>			
		</div>
    	<input type="hidden" name="movieNm" value="${userTicket.movieNm}">
		<input type="hidden" name="i_movie" value="${userTicket.i_movie}">
	    <input type="hidden" name="seats">
		<input type="hidden" name="seatCnt" value="0">
		<div id="screen">Screen</div>
		<div id="seat"></div>
		<input class="ticketing_btn" type="submit" value="예매하기">
	</form>
</div>
<script>
	if(`${errorTicket}` != ''){
		alert(`${errorTicket}`);
	}

	var selectedSeats = []

	function makeSeats(){
		for(let i=0; i<10; i++){
	        div = document.createElement("div")
			for(let j=0; j<10; j++){
				input = document.createElement("input")
				input.type = 'button'
				let seat = mapping(i, j);
				input.id = 'seat'+mapping(i, j)
				input.value = seat
				<c:forEach var="seats" items="${selSeats}">	
					if(`${seats.seat}` == 'seat'+seat){
						input.style.backgroundColor = 'black'; // 지환이한테 해달라하셈
						div.appendChild(input)
						continue;
					}
				</c:forEach>				
				input.setAttribute('onclick','choiceSeat('+input.id+')')
				div.appendChild(input)
			}
	        seat.appendChild(div)
		}
	}
	//좌석선택시
	function choiceSeat(id){
    	if (id.classList.contains("selected")){ //selected 클래스명을 가진 좌석이라면(이미 선택)
    		console.log('선택된 좌석')
    		id.classList.remove("selected") //클래스명 selected 제거
    		id.style.backgroundColor = 'white'
    		frm.seatCnt.value += 1;
    		let idx = selectedSeats.indexOf(id.id)
    		selectedSeats.splice(idx,1)
    		frm.seats.value = selectedSeats
    	}else{ //선택되지 않은 좌석
    		if(frm.seatCnt.value == 0){
        		alert('인원을 확인해주세요')
        	}else{
        	id.classList.add("selected");
        	id.style.backgroundColor = '#c8e6c9'
    		frm.seatCnt.value -= 1;
        	selectedSeats.push(id.id)
        	frm.seats.value = selectedSeats
        	}
    	}
		
	}
	
	//인원선택시 로직
    function choicePer(){
    	seat.innerHTML = '';
		makeSeats();
    	frm.seatCnt.value = Number(frm.adultCnt.value) + Number(frm.studentCnt.value)
    }
	
	function mapping(i, j){
		let seat = ""
		switch (i){
	    case 0:
	    	seat = "A" + j;
	        break;
	    case 1:
	    	seat = "B" + j;
	        break;
	    case 2:
	    	seat = "C" + j;
	        break;
	    case 3:
	    	seat = "D" + j;
	        break;
	    case 4:
	    	seat = "E" + j;
	        break;
	    case 5:
	    	seat = "F" + j;
	        break;
	    case 6:
	    	seat = "G" + j;
	        break;
	    case 7:
	    	seat = "H" + j;
	        break;
	    case 8:
	    	seat = "I" + j;
	        break;
	    case 9:
	    	seat = "J" + j;
	        break;
	}
		return seat;
	}
	
	function seatChk(){
		if(frm.seatCnt.value > 0){
			alert('좌석을 골라주세요')
			return false
		}
		return true
	}
	
	makeSeats();
</script>