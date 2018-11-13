package src.com.javalec.ex.command;



import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;



import src.com.javalec.ex.dao.BDao;



public class BPaging implements BCommand {



	@Override

	public void execute(HttpServletRequest request, HttpServletResponse response)

	{

		//선언

		//int page_num = 1;

		String now_page = "1";

		

		//예외처리

		if( request.getParameter( "Page" ) != null ) now_page =  request.getParameter( "Page" );

		

		BDao dao = new BDao();

		//pagenum = dao.paging();

		

		//request.setAttribute( "maxPage", pagenum );

		//if( request.getParameter( "Page" ) == null ) request.setAttribute( "Page", 1 );

		request.setAttribute( "paging_mode", dao.paging( now_page ) );

	}



}

