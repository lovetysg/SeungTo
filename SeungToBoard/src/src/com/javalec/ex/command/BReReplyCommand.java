package src.com.javalec.ex.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import src.com.javalec.ex.dao.BDao;

public class BReReplyCommand implements BCommand {
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub

		String bId = request.getParameter("bId");
		String rContent = request.getParameter("rContent");
		
		BDao dao = new BDao();
		dao.re_reply(bId, rContent);
	}
}
