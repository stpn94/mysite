package com.douzone.mysite.board;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.web.mvc.Action;

public class FileDownAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// File 다운로드 엑션

		String fileName = request.getParameter("downFile");

		String savePath = "boardUpload";
		ServletContext context = request.getServletContext();

		String sDownloadPath = context.getRealPath(savePath);
		System.out.println("sDownloadPath = " + sDownloadPath);
		String sFilePath = sDownloadPath + "\\" + fileName;

		byte b[] = new byte[4096];

		String sMimeType = request.getServletContext().getMimeType(sFilePath);

		if (sMimeType == null)
			sMimeType = "application/octet-stream";

		response.setContentType(sMimeType);
		String agent = request.getHeader("User-Agent");
		boolean ieBrowser = (agent.indexOf("MSIE") > -1) || (agent.indexOf("Trident") > -1);
		try {
			if (ieBrowser) {
				fileName = URLEncoder.encode(fileName, "UTF-8").replaceAll("\\+", "%20");
			} else {
				fileName = new String(fileName.getBytes("UTF-8"), "iso-8859-1");
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		response.setHeader("Content-Disposition", "attachment; filename=" + fileName);

		try (FileInputStream in = new FileInputStream(sFilePath);
				ServletOutputStream out2 = response.getOutputStream();) {
			int numRead;

			while ((numRead = in.read(b, 0, b.length)) != -1) {
				out2.write(b, 0, numRead);
			}
			out2.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}