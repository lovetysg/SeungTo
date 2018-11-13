package src.com.javalec.ex.frontcontroller;

import java.io.IOException;
import java.lang.ProcessBuilder.Redirect;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import src.com.javalec.ex.command.BCommand;
import src.com.javalec.ex.command.BContentCommand;
import src.com.javalec.ex.command.BDeleteCommand;
import src.com.javalec.ex.command.BListCommand;
import src.com.javalec.ex.command.BModifyCommand;
import src.com.javalec.ex.command.BReplyCommand;
import src.com.javalec.ex.command.BReReplyCommand;
import src.com.javalec.ex.command.BReplyViewCommand;
import src.com.javalec.ex.command.BWriteCommand;

/**
 * Servlet implementation class BoardFrontController
 */
@WebServlet("*.do")
public class BFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BFrontController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		actionDo(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		actionDo(request, response);
	}
	
	private void actionDo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		request.setCharacterEncoding("EUC-KR");
		
		String viewPage = null;
		String trans_type = "type1";
		BCommand command = null;
		
		String uri = request.getRequestURI();
		String conPath = request.getContextPath();
		String com = uri.substring(conPath.length());
		
		switch( com )
		{
			case "/login.do" :
				viewPage = "login.jsp";
			break;
			case "/member.do" :
				viewPage = "member.jsp";
			break;

			case "/write_view.do" :
				viewPage = "write_view.jsp";
			break;
			
			case "/write.do" :
				command = new BWriteCommand();
				command.execute(request, response);
				viewPage = "list.do";
				trans_type = "type2";
			break;

			case "/list.do" :
				command = new BListCommand();
				command.execute(request, response);
				viewPage = "list.jsp";
			break;

			case "/content_view.do" :
				command = new BContentCommand();
				command.execute(request, response);
				
				viewPage = "content_view.jsp";
			break;

			case "/modify_page.do" :
				command = new BContentCommand();
				command.execute(request, response);
				
				viewPage = "content_edit.jsp";
			break;
			
			case "/modify.do" :
				command = new BModifyCommand();
				command.execute(request, response);
				viewPage = "content_view.do";
			break;

			case "/delete.do" :
				command = new BDeleteCommand();
				command.execute(request, response);
				viewPage = "list.do";
				trans_type = "type2";
			break;
			
			case "/reply_edit.do" :
				command = new BReplyViewCommand();
				command.execute(request, response);
				viewPage = "reply_edit.jsp";
			break;

			case "/reply_view.do" :
				command = new BReplyViewCommand();
				command.execute(request, response);
				viewPage = "reply_view.jsp";
			break;

			case "/reply.do" :
				command = new BReplyCommand();
				command.execute(request, response);
				viewPage = "list.do";
				trans_type = "type2";
			break;
			
			case "/s_reply.do" :
				command = new BReReplyCommand();
				command.execute(request, response);
				
				viewPage = "content_view.do";
			break;
			
			case "/s_r_reply.do" :
				command = new BReReplyCommand();
				command.execute(request, response);
				
				viewPage = "reply_view.do";
			break;
		}
		
		switch( trans_type )
		{
			case "type1" :
				RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
				dispatcher.forward(request, response);
			break;
			case "type2" :
				response.sendRedirect(viewPage);
			break;
		}
		
	}

}
