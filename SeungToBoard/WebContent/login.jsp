<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<jsp:include page="./common/head.jsp"></jsp:include>
	
<!-- //컨텐츠 -->
<div id="l-container">
	<div class="l-contents">
		<h1 class="title">로그인</h1>
		<div id="login_page">
			
		<form action="?mode=prc_login" method="POST" name="LoginForm" onSubmit="">
		<!--<input type="hidden" name="redirect" value="<?=$_SERVER['HTTP_REFERER']?>">-->
			<div id="action_section">
				<h1><span class="icon"></span></h1>
				<div class="item">
					<span class="icon_id"></span>
					<input type="text" class="i_text" id="logId" name="logId" title="아이디" placeholder="User ID">
				</div>
				<div class="item">
					<span class="icon_pw"></span>
					<input type="password" class="i_text" id="logPass" name="logPass" title="비밀번호" placeholder="Password">
				</div>
				<input type="submit" value="login" class="btn_login">

				<div id="keeplogin">
					<input type="checkbox" id="">
					<label for="">로그인 상태 유지</label>
				</div>

			</div>
		</form>

			<div id="link_section">
				<h1 class="title_join">아직 계정이 없으십니까?</h1>
				<p class="dsc">회원이 아닌 분들은 회원가입 후<br>승토보드 이용이 가능합니다. </p>
				<a href="join_agree.html" class="btn_join icon_arrow">회원가입</a>
				
				<p class="findid"><a href="findid.html">아이디 또는 비밀번호 찾기</a></p>
			</div>
		</div>		
	</div>
	<jsp:include page="./common/lnb.jsp"></jsp:include>
</div>



<!-- 컨텐츠// -->		
<jsp:include page="./common/foot.jsp"></jsp:include>
	