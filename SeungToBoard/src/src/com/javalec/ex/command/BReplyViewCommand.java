package src.com.javalec.ex.command;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import src.com.javalec.ex.dao.BDao;
import src.com.javalec.ex.dto.BDto;
import src.com.javalec.ex.dto.BreDto;

public class BReplyViewCommand implements BCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		String bId = request.getParameter("bId");
		BDao dao = new BDao();
		BDto dto = dao.reply_view(bId);
		
		request.setAttribute("reply_view", dto);

		BDao bredao = new BDao();
		ArrayList<BreDto> replydtos = bredao.replylist(request.getParameter("bId"));
		request.setAttribute("replylist", replydtos);
	}

}
