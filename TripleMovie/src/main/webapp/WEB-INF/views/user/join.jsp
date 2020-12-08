<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link rel="stylesheet" href="/res/css/join.css">
<div id="joinDiv">
	<div id="joinContainer">
		<form id="frm" class="frm" action="/user/join" method="post">
			<h2>MovieTravel JOIN</h2>
			<div id="idChkResult" class="msg"></div>
			<div>
				<input type="text" name="user_id" placeholder="아이디">
				<button type="button" onclick="chkId()">아이디 중복체크</button>
			</div>
			<hr>
			<div>
				<input type="password" name="user_pw" placeholder="비밀번호">
			</div>
			<hr>
			<div>
				<input type="password" name="user_pwre" placeholder="비밀번호 확인">
			</div>
			<hr>
			<div>
				<input type="text" name="nick_nm" placeholder="닉네임">
				<button type="button" onclick="chkNick()">닉네임 중복체크</button>
			</div>
			<hr>
			<div>
				<input type="submit" class="submit_btn" value="회원가입">
			</div>
			<div class="a_tag">
				<a href="/user/login">로그인으로</a>
			</div>
		</form>
	</div>
</div>
<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
<script type="text/javascript">
	function chkId() {
		user_id = frm.user_id.value
		console.log(user_id)
		axios.post('/user/ajaxIdChk', {
			user_id:user_id
		}).then(function(res) {
			if(res.data == 2){
				alert('가입할 수 있는 아이디 입니다')
			}else if(res.data == 3){
				alert('이미 가입되어 있는 아이디 입니다')
			}
		})
	}
	
	function chkNick(){
		nick_nm = frm.nick_nm.value
		axios.post('/user/ajaxChkNick', {
			nick_nm:nick_nm
		}).then(function(res) {
			if(res.data == 2){
				alert('가입할 수 있는 닉네임 입니다')
			}else if(res.data == 3){
				alert('이미 가입되어 있는 닉네임 입니다')
			}
		})
	}
	
	function chkJoin(){
		if(frm.user_id.value == null || !idPattern.test(frm.user_id.value)){
			return false;
		}else if(frm.user_pw == null || !pwPattern.test(frm.user_pw)){
			return false;
		}else if(frm.nick_nm.value == null || !namePattern.test(frm.nick_nm.value)){
			return false;
		}else if(frm.user_pwre.value == null){
			return false;
		}else if(frm.age.value == 0){
			return false;
		}else if(frm.gender.value == 0){
			return false;
		}
		
		return true;
	}
</script>
