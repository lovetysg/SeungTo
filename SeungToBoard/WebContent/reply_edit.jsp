<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<jsp:include page="./common/head.jsp"></jsp:include>
	
<!-- //컨텐츠 -->
<div id="l-container">
	<div class="l-contents">
	<h1 class="title">답글 입력</h1>
	
	<table class="tbl-app">
		<form action="reply.do" method="post">
			<input type="hidden" name="bId" value="${reply_view.bId}">
			<input type="hidden" name="bGroup" value="${reply_view.bGroup}">
			<input type="hidden" name="bStep" value="${reply_view.bStep}">
			<input type="hidden" name="bIndent" value="${reply_view.bIndent}">
			<tr>
				<th> 번호 </th>
				<td> ${reply_view.bId} </td>
			</tr>
			<tr>
				<th> 히트 </th>
				<td> ${reply_view.bHit} </td>
			</tr>
			<tr>
				<th> 이름 </th>
				<td> <input type="text" name="bName" value="${reply_view.bName}"></td>
			</tr>
			<tr>
				<th> 제목 </th>
				<td> <input type="text" name="bTitle" value="${reply_view.bTitle}"></td>
			</tr>
			<tr>
				<th> 내용 </th>
				<td> <textarea rows="10"  name="bContent">${reply_view.bContent}</textarea></td>
			</tr>
			<tr >
				<td colspan="2"><span class="button-s"><input type="submit" value="저장"></span>&nbsp;&nbsp;<span class="button-s"><a href="content_view.do?bId=${reply_view.bId}">취소</a></span></td>
			</tr>
		</form>
	</table>
	
	</div>
	<jsp:include page="./common/lnb.jsp"></jsp:include>
</div>



<!-- 컨텐츠// -->		
<jsp:include page="./common/foot.jsp"></jsp:include>
	