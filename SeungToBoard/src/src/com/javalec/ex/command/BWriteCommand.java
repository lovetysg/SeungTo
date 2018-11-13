package src.com.javalec.ex.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import src.com.javalec.ex.dao.BDao;
import java.io.File;
import java.io.IOException;
import java.util.Enumeration;

public class BWriteCommand implements BCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		//String savePath = request.getServletContext().getRealPath("/upload");
		String savePath = "C:\\Users\\itstandard\\Desktop\\SeungToBoard\\WebContent\\upload";
		
		
		int sizeLimit = 1024*1024*15;
		MultipartRequest multi=null;
		
		try {
			multi = new MultipartRequest(request, savePath, sizeLimit, "utf-8", new DefaultFileRenamePolicy());

			String bName = multi.getParameter("bName");
			String bTitle = multi.getParameter("bTitle");
			String bContent = multi.getParameter("bContent");
			String bFile = multi.getFilesystemName("bFile");
			String bNotice = multi.getParameter("bNotice");

			BDao dao = new BDao();
			dao.write(bName, bTitle, bContent,bFile,bNotice);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
