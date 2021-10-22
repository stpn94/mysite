package com.douzone.mysite.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.mysite.repository.BoardRepository;
import com.douzone.mysite.vo.BoardVo;

@Service
public class BoardService {
	private static final int LIST_SIZE = 5; //리스팅되는 게시물의 수
	private static final int PAGE_SIZE = 5; //페이지 리스트의 페이지 수
	
	@Autowired
	private BoardRepository boardRepository;
	
	public boolean addContents( BoardVo boardVo ) {
		if( boardVo.getGroupNo() != null ) {
			increaseGroupOrderNo( boardVo );
		}
		return boardRepository.insert( boardVo ) == 1;
	}
	
	public BoardVo getContents( Long no ) {
		BoardVo boardVo = boardRepository.findByNo( no );
		
		if( boardVo != null ) {
			boardRepository.updateHit( no );
		}
		
		return boardVo;
	}

	public BoardVo getContents( Long no, Long userNo ) {
		BoardVo boardVo = boardRepository.findByNoAndUserNo( no, userNo );
		return boardVo;
	}
	
	public boolean modifyContents( BoardVo boardVo ) {
		int count = boardRepository.update( boardVo );
		return count == 1;
	}
	
	public boolean deleteContents( Long boardNo, Long userNo ) {
		int count = boardRepository.delete( boardNo, userNo );
		return count == 1;
	}
	
	public Map<String, Object> getContentsList( int currentPage, String keyword ){
		
		//1. 페이징을 위한 기본 데이터 계산
		int totalCount = boardRepository.getTotalCount( keyword ); 				// 전체 글 수
		int pageCount = (int)Math.ceil( (double)totalCount / LIST_SIZE );       // 전체 페이지수
		int blockCount = (int)Math.ceil( (double)pageCount / PAGE_SIZE );		// 몇 블럭 까지 있는 지 ex) 10page = 2block
		
		int currentBlock = (int)Math.ceil( (double)currentPage / PAGE_SIZE );	// 현재 사용 중 블럭.
		
		//2. 파라미터 page 값  검증													// 버그 방지
		if( currentPage > pageCount ) {											// 현재 페이지가 전체 페이지 수 보다 크면	
			currentPage = pageCount;											// 현재 페이지는 전체 페이지수(제일 높은 페이지)로 간다.
			currentBlock = (int)Math.ceil( (double)currentPage / PAGE_SIZE );	// 그리고 다시 현재 사용 중 블럭 구하기.
		}		
		
		if( currentPage < 1 ) {													// 만약 현재 페이지가 1보다 작으면
			currentPage = 1;													// 현재 페이지는 1로 설정되고
			currentBlock = 1;													// 1블럭을 사용한다.
		}
																							/* EX>>>>  "[5]" '(6)' 7 8 9 '(10)' "[11]" 
																							 * /// '(6)' = beginPage   '(10)' = endPage    
																							 * /// "[5]" = prevPage    "[11]" = nextPage */
		//3. view에서 페이지 리스트를 렌더링 하기위한 데이터 값 계산
		int beginPage = currentBlock == 0 ? 1 : (currentBlock - 1) * PAGE_SIZE + 1;			// 시작 페이지는 = 현재 블럭이 0 이면 1 이고 아니면 현재블럭에 1을 빼고 * 한 블럭의 페이지수 하고 +1 해준다. 그러면 5개씩 블럭이 설정 된다. 
		int prevPage = ( currentBlock > 1 ) ? ( currentBlock - 1 ) * PAGE_SIZE : 0;			// 이전 페이지 = 현재 블럭이 1보다 크면 현재블럭 -1에 한 블럭의 페이지수를 곱한다. 아니면 0이다.
																							
		int nextPage = ( currentBlock < blockCount ) ? currentBlock * PAGE_SIZE + 1 : 0;	// 다음 페이지 = 현재 블럭이 총 블럭수 보다 작으면 현재 블럭에 한 블럭의 페이지수를 곱하고 1을 더한다. 아니면 0이다.
		int endPage = ( nextPage > 0 ) ? ( beginPage - 1 ) + LIST_SIZE : pageCount;			// 끝 페이지 = 다음 블럭의 가장 작은 페이지가 0보다 크면 시작페이지에 1을 빼고 리스팅되는 게시물의 수 를 더한다. 하니면 총 페이지수(마지막 페이지)이다.
		
		//4. 리스트 가져오기
		List<BoardVo> list = boardRepository.findAllByPageAndKeword( keyword, currentPage, LIST_SIZE );		// 리스트 불러오기
		
		//5. 리스트 정보를 맵에 저장
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put( "list", list );
		map.put( "totalCount", totalCount );
		map.put( "listSize", LIST_SIZE );
		map.put( "currentPage", currentPage );
		map.put( "beginPage", beginPage );
		map.put( "endPage", endPage );
		map.put( "prevPage", prevPage );
		map.put( "nextPage", nextPage );
		map.put( "keyword", keyword );

		return map;
	}
	
	public boolean increaseGroupOrderNo( BoardVo boardVo ) {
		return boardRepository.updateOrderNo( boardVo.getGroupNo(), boardVo.getOrderNo() ) > 0;
	}
}
