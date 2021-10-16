package com.douzone.mysite.board;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.douzone.mysite.dao.BoardDao;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.mysite.vo.UserVo;
import com.douzone.web.mvc.Action;
import com.douzone.web.util.MvcUtil;

public class WriteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* write.jsp 에서 submit 해서 컨트롤러 거쳐서 왔다. */
		System.out.println("WriteAction start");
		
		/* 업로드 */
		// 파일 사이즈
		int fileSize = 5 * 1024 * 1024;
		//폴더 경로 설정
		String realFolder = "";
		String saveFolder = "/boardUpload";
		ServletContext context = request.getServletContext();	
		realFolder = context.getRealPath(saveFolder);			
		
		System.out.println(realFolder);
		
		// MultipartRequest 디펜던시 받아와라잉
		MultipartRequest multi = null;
		
		
		HttpSession session = request.getSession();
		UserVo authUser = (UserVo) session.getAttribute("authUser"); //session에서 아이디 정보 가져오기
		
		Integer maxGroupNo = new BoardDao().findMaxGroupNo(); // 그룹넘버 가져와야함
		
		System.out.println("maxGroupNo:" + maxGroupNo);

		BoardVo boardVo = new BoardVo();
		boardVo.setTitle(request.getParameter("title"));
		boardVo.setContents(request.getParameter("contents"));
		boardVo.setHit(0);
		boardVo.setDepth(0);				 // 간격
		boardVo.setGroupNo(maxGroupNo + 1);  // 그룹넘버에 1플러스 해서 새로운 그룹 만들기
		boardVo.setOrderNo(1);				 // 그룹내 글 순서
		boardVo.setUserNo(authUser.getNo()); // session에 있는 authUser를 가져와서쓰자.

		new BoardDao().insert(boardVo);		 // 게시글 등록

		System.out.println(authUser.getName()); 
		System.out.println(authUser.getNo());   

		MvcUtil.redirect(request.getContextPath() + "/board?a=board", request, response); // board ㄱㄱ

	}

}
