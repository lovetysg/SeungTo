<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<jsp:include page="./common/head.jsp"></jsp:include>
	
<!-- //컨텐츠 -->
<div id="l-container">
	<div class="l-contents">
	<h1 class="title">게시글 수정</h1>
	
	<table class="tbl-app">
		<form action="modify.do" method="post">
			<input type="hidden" name="bId" value="${content_view.bId}">
			<tr>
				<td> ID </td>
				<td> ${content_view.bId} </td>
			</tr>
			<tr>
				<td> Notice </td>
				<td>
					<c:set var = "notice" scope = "session" value = "${content_view.bNotice}"/>
					
					<c:choose>
				      <c:when test = "${notice eq '1'}">
				         <input type="checkbox" name="bNotice" id="bNotice" value="1" checked><label for="bNotice">공지사항</label>
				      </c:when>
				      <c:otherwise>
				      	<input type="checkbox" name="bNotice" id="bNotice" value="1"><label for="bNotice">공지사항</label>
				      </c:otherwise>
				    </c:choose>
				</td>
			</tr>
			<tr>
				<td> HIT </td>
				<td> ${content_view.bHit} </td>
			</tr>
			<tr>
				<td> NAME </td>
				<td> <input type="text" name="bName" value="${content_view.bName}"></td>
			</tr>
			<tr>
				<td> TITLE </td>
				<td> <input type="text" name="bTitle" value="${content_view.bTitle}"></td>
			</tr>
			<tr>
				<td> CONTENT </td>
				<td> <textarea rows="10" name="bContent" >${content_view.bContent}</textarea></td>
			</tr>
			<tr>
				<td colspan="2">  <span class="button-s"><input type="submit" value="저장"></span> &nbsp;&nbsp; <span class="button-s"><a href="content_view.do?bId=${content_view.bId}">취소</a></span> &nbsp;&nbsp;</td>
			</tr>
		</form>
	</table>
	
	</div>
	<jsp:include page="./common/lnb.jsp"></jsp:include>
</div>



<!-- 컨텐츠// -->		
<jsp:include page="./common/foot.jsp"></jsp:include>
	