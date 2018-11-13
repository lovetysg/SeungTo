<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    	<% 
			String uri = request.getRequestURI();
			String conPath = request.getContextPath();
    		String com = uri.substring(conPath.length());
		%>
		<nav class="lnb">
			<h2 class="title">
			<p>Itstandard SeungTo MVC-Board</p>
			Contents List</h2>
			<ul class="list-con">
				<li <% if( com.equals( "/login.jsp" ) ) out.print( "class=\"selected\"" ); %>><a href="login.do">로그인</a></li>
				<li <% if( com.equals( "/list.jsp" ) || com.equals( "/write_view.jsp" )  || com.equals( "/content_view.jsp" ) || com.equals( "/reply_view.jsp" ) ) out.print( "class=\"selected\"" ); %>><a href="list.do">게시판</a></li>
			</ul>
		</nav>