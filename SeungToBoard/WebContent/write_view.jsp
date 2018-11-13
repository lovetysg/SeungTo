<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<jsp:include page="./common/head.jsp"></jsp:include>
	
<!-- //컨텐츠 -->
<div id="l-container">
	<div class="l-contents">
	<h1 class="title">게시글 작성</h1>
	<table class="tbl-app">
		<form action="write.do" method="post" enctype="Multipart/form-data">
			<tr>
				<th> Notice </th>
				<td> <input type="checkbox" name="bNotice" id="bNotice" value="1"><label for="bNotice">공지사항</label></td>
			</tr>
			<tr>
				<th> NAME </th>
				<td> <input type="text" name="bName" size = "50"> </td>
			</tr>
			<tr>
				<th> TITLE </th>
				<td> <input type="text" name="bTitle" size = "50"> </td>
			</tr>
			<tr>
				<th> CONTENT </th>
				<td> <textarea name="bContent" rows="10" ></textarea> </td>
			</tr>
			<tr>
				<th> FILE </th>
				<td> <input type="file" name="bFile"> </td>
			</tr>
			<tr >
				<td colspan="2"><span class="button-s"><input type="submit" value="입력"></span> &nbsp;&nbsp; <span class="button-s"><a href="list.do">목록보기</a></span></td>
			</tr>
		</form>
	</table>
	
	</div>
	<jsp:include page="./common/lnb.jsp"></jsp:include>
</div>



<!-- 컨텐츠// -->		
<jsp:include page="./common/foot.jsp"></jsp:include>
	