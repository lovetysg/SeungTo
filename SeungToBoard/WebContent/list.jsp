<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="./common/head.jsp"></jsp:include>

<!-- //컨텐츠 -->
<div id="l-container">
	<div class="l-contents">
	<h1 class="title">게시판</h1>
	
	<table class="tbl-list">
		<tr>
			<td>No.</td>
			<td>ID</td>
			<td>TITLE</td>
			<td>DATE</td>
			<td>NAME</td>
			<td>HIT</td>
		</tr>
		<c:forEach items="${noticelist}" var="dto">
		<tr style="font-weight: bold;">
			<td align="center">
			   <img src="./image/icon_notice.gif" alt="공지사항">
			</td>
			<td>${dto.bId}</td>
			<td>
				<c:set var = "reply" scope = "session" value = "${dto.bIndent}"/>
				<c:choose>
					<c:when test = "${reply eq '1'}">
				  		<img src="./image/icon_reply.gif">
						<a href="reply_view.do?bId=${dto.bId}">${dto.bTitle}</a>
				    </c:when>
				    <c:otherwise>
				       <a href="content_view.do?bId=${dto.bId}">${dto.bTitle}</a>
				     </c:otherwise>
				</c:choose>
			</td>
			<td>${dto.bDate}</td>
			<td>${dto.bName}</td>
			<td>${dto.bHit}</td>
		</tr>
		</c:forEach>
		<c:forEach items="${list}" var="dto">
		<tr>
			<td align="center">
			   <c:out value = "${dto.lc}"/>
			</td>
			<td>${dto.bId}</td>
			<td>	
				<c:set var = "reply" scope = "session" value = "${dto.bIndent}"/>
				<c:choose>
					<c:when test = "${reply eq '1'}">
				  		<img src="./image/icon_reply.gif">
						<a href="reply_view.do?bId=${dto.bId}">${dto.bTitle}</a>
				    </c:when>
				    <c:otherwise>
				       <a href="content_view.do?bId=${dto.bId}">${dto.bTitle}</a>
				     </c:otherwise>
				</c:choose>
			</td>
			<td>${dto.bDate}</td>
			<td>${dto.bName}</td>
			<td>${dto.bHit}</td>
		</tr>
		</c:forEach>
	</table>
	
	<span class="button-s"><a href="write_view.do">글작성</a></span>
	<br>
		<link rel="stylesheet" href="http://itstandard.co.kr/css/page/page.css">
		<div id="ptc_page">
			${paging_mode}
		</div>
	<br>
	<form method="get" action="?" name="searchForm">
	<div class="search" align="center">
		<select name="smode" id="smode">
			<option value="bName">이름</option>
			<option value="bContent">내용</option>
		</select>
		<input type="text" class="i_text" name="skey" title="검색어 입력" value="">
		<span class="button-s"><input type="button" onclick="document.forms['searchForm'].submit();" value="검색"></span>
	</div>
	</form>
	</div>
	<jsp:include page="./common/lnb.jsp"></jsp:include>
</div>



<!-- 컨텐츠// -->		
<jsp:include page="./common/foot.jsp"></jsp:include>
	