<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<link rel="stylesheet" href="/res/css/mypage.css">
<div class="mypage_container">
	<c:forEach var="item" items="${ticketInfo}">
		<div class="myticket">
			<table class="ticket_info">
				<tr>
					<td colspan="7" class="ticket_title">
						<span>ticket</span>
						<h1>${item.movieNm}</h1>
					</td>
				</tr>
				<tr class="ticket_day">
					<td class="value_cell">
						<fmt:parseDate var="chkInDayString" value="${item.r_dt}" pattern="yyyyMMdd" />
						<fmt:formatDate value="${chkInDayString}" pattern="yyyy-MM-dd (E)"/>
					</td>

				</tr>
				<tr class="ticket_time">
					<td>
						${item.s_Time} ~ ${item.e_Time}
					</td>
				</tr>
				<tr>
					<td class="ticket_human">
						총 인원 ${item.adultCnt + item.studentCnt}명,
						성인 ${item.adultCnt}명
						청소년 ${item.studentCnt}명
					</td>
				</tr>
				<tr>
					<td>${item.room}관</td>
				</tr>
				<tr class="ticket_sheet_info">
					<td>
						<c:forEach var="seats" items="${item.seats}" varStatus="status">
							 ${seats}
						</c:forEach>
					</td>
				</tr>
				<tr>
					<td class="ticket_cancel">
						<button onclick="test(${item.i_ticket})">예매취소</button>
					</td>
				</tr>
			</table>
		</div>
	</c:forEach>
</div>
<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
<script type="text/javascript">
	function test(i_ticket){
		if(!confirm("정말로 취소 하시겠습니까?")){return;}
		axios.post('/user/delTicket',{
			i_ticket:i_ticket
		}).then(function(res){
			if(res.data == 1){
				location.reload(true);
				alert('예매가 취소되었습니다');
			}
		})
	}
	

</script>