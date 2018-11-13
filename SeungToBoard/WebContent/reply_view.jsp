<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<jsp:include page="./common/head.jsp"></jsp:include>
	
<!-- //컨텐츠 -->
<div id="l-container">
	<div class="l-contents">
	<h1 class="title">답글 보기</h1>
	
	<form action="reply_edit.do" method="post">
	<div align="right"><span class="button-s"><a href="list.do">목록보기</a></span>&nbsp;&nbsp;<span class="button-sd"><a href="delete.do?bId=${reply_view.bId}">삭제</a></span></div>
	<table class="tbl-app">
			<input type="hidden" name="bId" value="${reply_view.bId}">
			<input type="hidden" name="bGroup" value="${reply_view.bGroup}">
			<input type="hidden" name="bStep" value="${reply_view.bStep}">
			<input type="hidden" name="bIndent" value="${reply_view.bIndent}">
			<tr>
				<th width="25%"> 번호 </th>
				<td> ${reply_view.bId} </td>
			</tr>
			<tr>
				<th width="25%"> 히트 </th>
				<td> ${reply_view.bHit} </td>
			</tr>
			<tr>
				<th width="25%"> 이름 </th>
				<td> ${reply_view.bName}</td>
			</tr>
			<tr>
				<th width="25%"> 제목 </th>
				<td> ${reply_view.bTitle}</td>
			</tr>
			<tr height="250px;">
				<th width="25%"> 내용 </th>
				<td> ${reply_view.bContent}</td>
			</tr>
	</table>
	</form>
	
	<h3>댓글</h3>
	<table class="tbl-app">
		<c:forEach items="${replylist}" var="dto">
		<tr>
			<th width="10%" align="center"><img src="./image/logo.JPG" width="50px;" alt="사진"></th>
			<td>${dto.rContent}</td>
			<td width="20%">${dto.rDate}</td>
		</tr>
		</c:forEach>
	</table>
	
	<form action="s_r_reply.do" method="post">
		<table class="tbl-app">
			<input type="hidden" name="bId" value="${reply_view.bId}">
			<tr>
				<th width="15%">
					댓글
				</th>
				<td>
					<textarea name="rContent" rows="10" ></textarea>
				</td>
				<th width="15%">
					<div class="btn-area">
						<span class="button-b"><input type="submit" value="입력"></span>
					</div>
				</th>
			</tr>
		</table>
	</form>
	
	</div>
	<jsp:include page="./common/lnb.jsp"></jsp:include>
</div>



<!-- 컨텐츠// -->		
<jsp:include page="./common/foot.jsp"></jsp:include>
	