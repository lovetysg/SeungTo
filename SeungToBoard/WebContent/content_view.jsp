<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<jsp:include page="./common/head.jsp"></jsp:include>
	
<!-- //컨텐츠 -->
<div id="l-container">
	<div class="l-contents">
	<h1 class="title">게시글 보기</h1>
	
	<form action="modify_page.do" method="post">
					<c:set var = "notice" scope = "session" value = "${content_view.bNotice}"/>
					<c:choose>
				      <c:when test = "${notice eq '1'}">
				        <div align="right"><span class="button-s"><input type="submit" value="수정"></span>&nbsp;&nbsp;<span class="button-s"><a href="list.do">목록보기</a></span>&nbsp;&nbsp;<span class="button-sd"><a href="delete.do?bId=${content_view.bId}">삭제</a></span>&nbsp;&nbsp;</div>
				      </c:when>
				      <c:otherwise>
				      	<div align="right"><span class="button-s"><input type="submit" value="수정"></span>&nbsp;&nbsp;<span class="button-s"><a href="list.do">목록보기</a></span>&nbsp;&nbsp;<span class="button-sd"><a href="delete.do?bId=${content_view.bId}">삭제</a></span>&nbsp;&nbsp;<span class="button-s"><a href="reply_edit.do?bId=${content_view.bId}">답변</a></span></div>
				      </c:otherwise>
				    </c:choose>
		<table class="tbl-app">
			<input type="hidden" name="bId" value="${content_view.bId}">
			<tr>
				<th width="25%"> ID </th>
				<td> ${content_view.bId} </td>
			</tr>
			<tr>
				<th> Notice </th>
				<td>					
					<c:set var = "notice" scope = "session" value = "${content_view.bNotice}"/>
					<c:choose>
				      <c:when test = "${notice eq '1'}">
				        	공지사항
				      </c:when>
				      <c:otherwise>
				      		일반글
				      </c:otherwise>
				    </c:choose>
				</td>
			</tr>
			<tr>
				<th width="25%"> HIT </th>
				<td> ${content_view.bHit} </td>
			</tr>
			<tr>
				<th width="25%"> NAME </th>
				<td> ${content_view.bName}</td>
			</tr>
			<tr>
				<th width="25%"> TITLE </th>
				<td> ${content_view.bTitle}</td>
			</tr>
			<tr height="250px;">
				<th width="25%"> CONTENT </th>
				<td>${content_view.bContent}</td>
			</tr>
			<tr>
				<th width="25%"> File </th>
				<td> <a href="./upload/${content_view.bFile}" download>${content_view.bFile}</a></td>
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
	
	<form action="s_reply.do" method="post">
		<table class="tbl-app">
			<input type="hidden" name="bId" value="${content_view.bId}">
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
	