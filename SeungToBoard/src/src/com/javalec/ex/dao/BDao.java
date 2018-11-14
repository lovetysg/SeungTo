package src.com.javalec.ex.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import src.com.javalec.ex.dto.BDto;
import src.com.javalec.ex.dto.BreDto;

public class BDao {

	DataSource dataSource;
	
	public BDao() {
		// TODO Auto-generated constructor stub
		
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/Oracle11g");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public void write(String bName, String bTitle, String bContent, String bFile,String bNotice) {
		// TODO Auto-generated method stub
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;sdfgsdfgsdfgsdfg

		try {
			connection = dataSource.getConnection();
			String query = "insert into seungto_board (bId, bName, bTitle, bContent, bHit, bGroup, bStep, bIndent, bFile, bNotice ) values (seungto_board_seq.nextval, ?, ?, ?, 0, seungto_board_seq.currval, 0, 0, ?, ? )";

			preparedStatement = connection.prepareStatement(query);

			preparedStatement.setString(1, bName);
			preparedStatement.setString(2, bTitle);
			preparedStatement.setString(3, bContent);
			preparedStatement.setString(4, bFile);
			preparedStatement.setString(5, bNotice);
			
			
			int rn = preparedStatement.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				if(preparedStatement != null) preparedStatement.close();
				if(connection != null) connection.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		
	}
	
	public void re_reply( String bId, String rContent ) {
		// TODO Auto-generated method stub

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = dataSource.getConnection();
			String query = "insert into reply_list (rId, bId, rContent ) values (reply_list_seq.nextval, ?, ? )";

			preparedStatement = connection.prepareStatement(query);

			preparedStatement.setString(1, bId);
			preparedStatement.setString(2, rContent);
			
			int rn = preparedStatement.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				if(preparedStatement != null) preparedStatement.close();
				if(connection != null) connection.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
	}
	
	public ArrayList<BDto> list( String Page, String smode, String skey ) {
		ArrayList<BDto> dtos = new ArrayList<BDto>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		PreparedStatement preparedStatement_count = null;
		ResultSet resultSet_count = null;

		try {
			connection = dataSource.getConnection();
			String minPages="";
			String maxPages="";
			
			if( Page == null ) 
			{
				Page = "1";
				minPages = "1";
				maxPages = "5";
			}
			else
			{
				maxPages = Integer.toString( ( Integer.parseInt(Page) * 5 ) );
				minPages = Integer.toString( Integer.parseInt(maxPages) -4 );
			}

			
			//String query = "select * from seungto_board where bNotice is null order by bGroup desc, bStep asc";
			String query = "";
			
			if( skey != null && smode != null )
			{
				query = "select bId, bName, bTitle, bContent, bDate, bHit, bGroup, bStep, bIndent,bFile,bNotice, lc, ct from ( SELECT p.*, ROW_NUMBER() OVER(ORDER BY bGroup DESC, bStep asc) AS ct, ROW_NUMBER() OVER(ORDER BY bGroup asc, bStep asc) as lc FROM seungto_board p ) WHERE "+smode+" like '%"+skey+"%' and ct BETWEEN " + minPages +" AND " + maxPages + " and bNotice is Null order by bGroup desc, bStep asc";
			}
			else
			{
				query = "select bId, bName, bTitle, bContent, bDate, bHit, bGroup, bStep, bIndent,bFile,bNotice, lc, ct from ( SELECT p.*, ROW_NUMBER() OVER(ORDER BY bGroup DESC, bStep asc) AS ct, ROW_NUMBER() OVER(ORDER BY bGroup asc, bStep asc) as lc FROM seungto_board p ) WHERE ct BETWEEN " + minPages +" AND " + maxPages + " and bNotice is Null order by bGroup desc, bStep asc";
			}
			
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			
			//역순배열
			//String query_count = "select * from seungto_board where bNotice is null";
			//preparedStatement_count = connection.prepareStatement(query_count);
			//resultSet_count = preparedStatement_count.executeQuery();
			
			//int totalcnt = 0;
			//while (resultSet_count.next()) totalcnt++;
			
			while (resultSet.next()) {
				//int bCnt = totalcnt;
				int bId = resultSet.getInt("bId");
				String bName = resultSet.getString("bName");
				String bTitle = resultSet.getString("bTitle");
				String bContent = resultSet.getString("bContent");
				Timestamp bDate = resultSet.getTimestamp("bDate");
				int bHit = resultSet.getInt("bHit");
				int bGroup = resultSet.getInt("bGroup");
				int bStep = resultSet.getInt("bStep");
				int bIndent = resultSet.getInt("bIndent");
				String bFile = resultSet.getString("bFile");
				String bNotice = resultSet.getString("bNotice");
				int lc = resultSet.getInt("lc");
				int ct = resultSet.getInt("ct");
				
				//BDto dto = new BDto(bId, bName, bTitle, bContent, bDate, bHit, bGroup, bStep, bIndent,bFile,bNotice, bCnt );
				BDto dto = new BDto(bId, bName, bTitle, bContent, bDate, bHit, bGroup, bStep, bIndent,bFile,bNotice,lc,ct);
				dtos.add(dto);
				//totalcnt--;
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				if(resultSet != null) resultSet.close();
				if(preparedStatement != null) preparedStatement.close();
				if(connection != null) connection.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		return dtos;
	}
	


	public String paging( String nowPage )

	{

		// TODO Auto-generated method stub
		int rnowPage=Integer.parseInt(nowPage);
		Connection connection = null;

		PreparedStatement preparedStatement = null;

		ResultSet resultSet = null;

		int maxPage = 1;
		int prepage = rnowPage-5;
		int afterpage = rnowPage+5;

		String code = "";

		

		try

		{

			connection = dataSource.getConnection();

			String query = "select CEIL( count( * )/5 ) as page FROM seungto_board where bNotice is null";

			preparedStatement = connection.prepareStatement(query);

			resultSet = preparedStatement.executeQuery();

			

			resultSet.next();

			maxPage = resultSet.getInt( "page" );

			

			//페이징시 보이는 코드

			code = "";

			code += "<a href='?Page=1' class='no_first'>처음</a>";
			code += "<a href='?Page="+prepage+"' class='no_pre'>이전</a>";

			for( int i=1; i<=maxPage; i++ )

			{

				if( i == rnowPage ) code += "<strong>" + i + "</strong>";

				else code += "<a href='?Page=" + i + "'>" + i + "</a>";

			}

			code += "<a href='?Page="+afterpage+"' class='next'>다음</a>";
			code += "<a href='?Page="+maxPage+"' class='last'>끝("+maxPage+" Page)</a>";
		}

		catch(Exception e)

		{

			e.printStackTrace();

		} finally {
			try {
				if(resultSet != null) resultSet.close();
				if(preparedStatement != null) preparedStatement.close();
				if(connection != null) connection.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}

		

		return code;

	}

	public ArrayList<BDto> noticelist() {
		
		ArrayList<BDto> noticedtos = new ArrayList<BDto>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			connection = dataSource.getConnection();
			
			String query = "select bId, bName, bTitle, bContent, bDate, bHit, bGroup, bStep, bIndent, bFile, bNotice from seungto_board where bNotice is not null order by bGroup desc, bStep asc";
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
									
			while (resultSet.next()) {
				int ct = 0;
				int lc = 0;
				int bId = resultSet.getInt("bId");
				String bName = resultSet.getString("bName");
				String bTitle = resultSet.getString("bTitle");
				String bContent = resultSet.getString("bContent");
				Timestamp bDate = resultSet.getTimestamp("bDate");
				int bHit = resultSet.getInt("bHit");
				int bGroup = resultSet.getInt("bGroup");
				int bStep = resultSet.getInt("bStep");
				int bIndent = resultSet.getInt("bIndent");
				String bFile = resultSet.getString("bFile");
				String bNotice = resultSet.getString("bNotice");

				BDto dto = new BDto(bId, bName, bTitle, bContent, bDate, bHit, bGroup, bStep, bIndent,bFile,bNotice, lc,ct );
				noticedtos.add(dto);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				if(resultSet != null) resultSet.close();
				if(preparedStatement != null) preparedStatement.close();
				if(connection != null) connection.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}

		return noticedtos;
	}
	

	public ArrayList<BreDto> replylist( String bId ) {
		
		ArrayList<BreDto> replydtos = new ArrayList<BreDto>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			connection = dataSource.getConnection();
			
			String query = "select * from reply_list where bId="+bId;
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
									
			while (resultSet.next()) {
				String rContent = resultSet.getString("rContent");
				Timestamp rDate = resultSet.getTimestamp("rDate");

				BreDto redto = new BreDto(bId, rContent, rDate);
				replydtos.add(redto);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				if(resultSet != null) resultSet.close();
				if(preparedStatement != null) preparedStatement.close();
				if(connection != null) connection.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}

		return replydtos;
	}
	
	public BDto contentView(String strID) {
		// TODO Auto-generated method stub
		
		upHit(strID);
		
		BDto dto = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			
			connection = dataSource.getConnection();
			
			String query = "select * from seungto_board where bId = ?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, Integer.parseInt(strID));
			resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next()) {
				int bId = resultSet.getInt("bId");
				String bName = resultSet.getString("bName");
				String bTitle = resultSet.getString("bTitle");
				String bContent = resultSet.getString("bContent");
				Timestamp bDate = resultSet.getTimestamp("bDate");
				int bHit = resultSet.getInt("bHit");
				int bGroup = resultSet.getInt("bGroup");
				int bStep = resultSet.getInt("bStep");
				int bIndent = resultSet.getInt("bIndent");
				int lc = 0;
				int ct = 0;
				String bFile = resultSet.getString("bFile");
				String bNotice = resultSet.getString("bNotice");
				
				dto = new BDto(bId, bName, bTitle, bContent, bDate, bHit, bGroup, bStep, bIndent, bFile, bNotice, lc,ct );
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				if(resultSet != null) resultSet.close();
				if(preparedStatement != null) preparedStatement.close();
				if(connection != null) connection.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		return dto;
	}
	
	public void modify(String bId, String bName, String bTitle, String bContent,String bNotice) {
		// TODO Auto-generated method stub
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			connection = dataSource.getConnection();
			
			String query = "update seungto_board set bName = ?, bTitle = ?, bContent = ?, bNotice = ? where bId = ?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, bName);
			preparedStatement.setString(2, bTitle);
			preparedStatement.setString(3, bContent);
			preparedStatement.setString(4, bNotice);
			preparedStatement.setInt(5, Integer.parseInt(bId));
			int rn = preparedStatement.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				if(preparedStatement != null) preparedStatement.close();
				if(connection != null) connection.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
	}
	
	public void delete(String bId) {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			
			connection = dataSource.getConnection();
			String query = "delete from seungto_board where bId = ?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, Integer.parseInt(bId));
			int rn = preparedStatement.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				if(preparedStatement != null) preparedStatement.close();
				if(connection != null) connection.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
	}
	
	public BDto reply_view(String str) {
		// TODO Auto-generated method stub
		BDto dto = null;
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			
			connection = dataSource.getConnection();
			String query = "select * from seungto_board where bId = ?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, Integer.parseInt(str));
			resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next()) {
				int bId = resultSet.getInt("bId");
				String bName = resultSet.getString("bName");
				String bTitle = resultSet.getString("bTitle");
				String bContent = resultSet.getString("bContent");
				Timestamp bDate = resultSet.getTimestamp("bDate");
				int bHit = resultSet.getInt("bHit");
				int bGroup = resultSet.getInt("bGroup");
				int bStep = resultSet.getInt("bStep");
				int bIndent = resultSet.getInt("bIndent");
				int lc = 0;
				int ct = 0;
				String bFile = resultSet.getString("bFile");
				String bNotice = resultSet.getString("bNotice");
				
				dto = new BDto(bId, bName, bTitle, bContent, bDate, bHit, bGroup, bStep, bIndent,bFile, bNotice,lc,ct);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				if(preparedStatement != null) preparedStatement.close();
				if(connection != null) connection.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		
		return dto;
	}
	
	public void reply(String bId, String bName, String bTitle, String bContent, String bGroup, String bStep, String bIndent) {
		// TODO Auto-generated method stub
		
		replyShape(bGroup, bStep);
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			connection = dataSource.getConnection();
			String query = "insert into seungto_board (bId, bName, bTitle, bContent, bGroup, bStep, bIndent) values (seungto_board_seq.nextval, ?, ?, ?, ?, ?, ?)";
			preparedStatement = connection.prepareStatement(query);
			
			preparedStatement.setString(1, bName);
			preparedStatement.setString(2, bTitle);
			preparedStatement.setString(3, bContent);
			preparedStatement.setInt(4, Integer.parseInt(bGroup));
			preparedStatement.setInt(5, Integer.parseInt(bStep) + 1);
			preparedStatement.setInt(6, Integer.parseInt(bIndent) + 1);
			
			int rn = preparedStatement.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				if(preparedStatement != null) preparedStatement.close();
				if(connection != null) connection.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		
	}
	
	private void replyShape( String strGroup, String strStep) {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			connection = dataSource.getConnection();
			String query = "update seungto_board set bStep = bStep + 1 where bGroup = ? and bStep > ?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, Integer.parseInt(strGroup));
			preparedStatement.setInt(2, Integer.parseInt(strStep));
			
			int rn = preparedStatement.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				if(preparedStatement != null) preparedStatement.close();
				if(connection != null) connection.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
	}
	
	private void upHit( String bId) {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			connection = dataSource.getConnection();
			String query = "update seungto_board set bHit = bHit + 1 where bId = ?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, bId);
			
			int rn = preparedStatement.executeUpdate();
					
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				if(preparedStatement != null) preparedStatement.close();
				if(connection != null) connection.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
	}
	
}
