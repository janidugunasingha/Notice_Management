package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Notice {
private Connection connect(){ 
		
		Connection con = null; 
		
		try{ 
				Class.forName("com.mysql.jdbc.Driver"); 

				//Provide the correct details: DBServer/DBName, username, password 
				//DB Connection
				con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/notice", "root", ""); 
		} 
		catch (Exception e) {
			e.printStackTrace();
			} 
		
		return con; 
} 

//Insert Function
public String insertNotice(String adminname, String adminid, String header, String content){ 

	String output = ""; 
	
	try
	{ 
		Connection con = connect(); 
		
		if (con == null) 
		{
			return "Error while connecting to the database for inserting."; 
			
		} 
		// create a prepared statement
		
		String query = " insert into notices (`noticeID`,`adminName`,`adminID`,`adminHeader`,`adminContent`)"+" values (?, ?, ?, ?, ?)"; 
		PreparedStatement preparedStmt = con.prepareStatement(query); 
		// binding values
		preparedStmt.setInt(1, 0); 
		preparedStmt.setString(2, adminname); 
		preparedStmt.setString(3, adminid); 
		preparedStmt.setString(4, header); 
		preparedStmt.setString(5, content); 
		// execute the statement

		preparedStmt.execute(); 
		con.close(); 
		
		String newNotices = readNotices(); 
		output = "{\"status\":\"success\",\"data\":\""+newNotices+"\"}";
	} 
	
	catch (Exception e) 
	{ 
		output = "{\"status\":\"error\", \"data\":\"Error while inserting the Notice.\"}"; 
		System.err.println(e.getMessage()); 
	} 
	return output; 
} 


//Read Function
public String readNotices(){ 

	String output = ""; 
	
	try{ 
			Connection con = connect(); 
			if (con == null){
				
				return "Error while connecting to the database for reading."; 
				
				} 
			// Prepare the html table to be displayed
			output = "<table border=\"1\" class=\"table\"><tr><th>adminName</th>" +
					"<th>adminID</th><th>adminHeader</th>" + 
					"<th>adminContent</th>" +
					"<th>Update</th><th>Remove</th></tr>"; 

			String query = "select * from notices"; 
			Statement stmt = con.createStatement(); 
			ResultSet rs = stmt.executeQuery(query); 
			// iterate through the rows in the result set
			while (rs.next()) 
			{ 
					String noticeID = Integer.toString(rs.getInt("noticeID")); 
					String adminName = rs.getString("adminName"); 
					String adminID = rs.getString("adminID"); 
					String adminHeader = rs.getString("adminHeader"); 
					String adminContent = rs.getString("adminContent"); 
					// Add into the html table
					output += "<tr><td><input id='hidNoticeIDUpdate' name='hidNoticeIDUpdate' type='hidden' value='"+noticeID+"'>"+adminName+"</td>"; 
					output += "<td>" + adminID + "</td>"; 
					output += "<td>" + adminHeader + "</td>"; 
					output += "<td>" + adminContent + "</td>"; 
					// buttons
					 output += "<td><input name='btnUpdate' type='button' value='Update' "
							 + "class='btnUpdate btn btn-secondary' data-noticeid='" + noticeID + "'></td>"
							 + "<td><input name='btnRemove' type='button' value='Remove' "
							 + "class='btnRemove btn btn-danger' data-noticeid='" + noticeID + "'></td></tr>"; 
			} 
				con.close(); 
				// Complete the html table
				output += "</table>"; 
		} 
		catch (Exception e){ 
					output = "Error while reading the notice."; 
					System.err.println(e.getMessage()); 
		} 
		return output; 
		} 


//Update Function
public String updateNotice(String ID, String adminname, String adminid, String header, String content){ 

	String output = ""; 
	
	try{ 
			Connection con = connect(); 
			if (con == null){
				return "Error while connecting to the database for updating.";
				} 
			// create a prepared statement
			String query = "UPDATE notices SET adminName=?,adminID=?,adminHeader=?,adminContent=? WHERE noticeID=?"; 
			PreparedStatement preparedStmt = con.prepareStatement(query); 
			// binding values
			preparedStmt.setString(1, adminname); 
			preparedStmt.setString(2, adminid); 
			preparedStmt.setString(3, header); 
			preparedStmt.setString(4, content); 
			preparedStmt.setInt(5, Integer.parseInt(ID)); 
			// execute the statement
			preparedStmt.execute(); 
			con.close(); 
			
			String newNotices = readNotices(); 
			output = "{\"status\":\"success\",\"data\":\""+newNotices+"\"}";
			
	} 
	
	catch (Exception e){ 
		
		output = "{\"status\":\"error\",\"data\":\"Error while updating the Notice.\"}"; 
		System.err.println(e.getMessage()); 
		
	} 
	
	return output; 
} 


//Delete Function
public String deleteNotice(String noticeID){ 

	String output = ""; 
	
	try{ 
		Connection con = connect(); 
		
		if (con == null){
			return "Error while connecting to the database for deleting."; 
			} 
		// create a prepared statement
		String query = "delete from notices where noticeID=?"; 
		PreparedStatement preparedStmt = con.prepareStatement(query); 
		// binding values
		preparedStmt.setInt(1, Integer.parseInt(noticeID)); 
		// execute the statement
		preparedStmt.execute(); 
		con.close(); 
		
		String newNotices = readNotices(); 
		 output = "{\"status\":\"success\",\"data\":\""+newNotices+"\"}"; 
	} 
	
	catch (Exception e){ 
		output = "{\"status\":\"error\",\"data\":\"Error while deleting the Notice.\"}";
		System.err.println(e.getMessage()); 
	} 
	return output; 
} 



}
