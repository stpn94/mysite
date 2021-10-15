package com.douzone.web.util;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MvcUtil {

	public static void forward(String path, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/" + path + ".jsp");
		rd.forward(request, response);

	}

	public static void redirect(String url, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect(url);

	}

}
