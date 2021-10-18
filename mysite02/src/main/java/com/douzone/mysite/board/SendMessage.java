package com.douzone.mysite.board;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

public class SendMessage {
	public static void sendMessage(HttpServletResponse response, String msg) {
		try {
			response.setContentType("text/html; charset=UTF-8"); 
			PrintWriter out=response.getWriter();
			out.println("<script>");
			out.println("alert('"+ msg +"');");
			out.println("history.back();");
			out.println("</script>");
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
}