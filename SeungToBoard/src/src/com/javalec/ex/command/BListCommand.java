package src.com.javalec.ex.command;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import src.com.javalec.ex.dao.BDao;
import src.com.javalec.ex.dto.BDto;

public class BListCommand implements BCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		BDao noticedao = new BDao();
		ArrayList<BDto> noticedtos = noticedao.noticelist();
		request.setAttribute("noticelist", noticedtos);
		
		BDao dao = new BDao();
		ArrayList<BDto> dtos = dao.list( request.getParameter("Page"),request.getParameter("smode"),request.getParameter("skey") );
		request.setAttribute("list", dtos);

		String now_page = "1";

		if( request.getParameter( "Page" ) != null ) now_page =  request.getParameter( "Page" );

		BDao pdao = new BDao();

		request.setAttribute( "paging_mode", pdao.paging( now_page ) );

	}
}