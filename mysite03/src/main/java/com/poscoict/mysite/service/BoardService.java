package com.poscoict.mysite.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poscoict.mysite.repository.BoardRepository;
import com.poscoict.mysite.vo.BoardVo;

@Service
public class BoardService {
	@Autowired
	private BoardRepository boardRepository;

	// 새글, 댓글 달기
	public boolean addContents(BoardVo vo) {
		System.out.println("[addContents] : "+ vo.toString());
		if (vo.getGroupNo() != null) {
			System.out.println("답글 : " + vo.toString());
			return increaseGroupOrderNO(vo);
		} else {
			return boardRepository.insert(vo) == 1;
		}
	}

	public BoardVo getComment(Long no) {
		BoardVo vo = boardRepository.findByNO(no);
		System.out.println("[getComment] "+ vo);
		return vo;
	}

	// 게시물 글보기
	public BoardVo getContents(Long no) {
		BoardVo vo = boardRepository.view(no);
		boardRepository.setHit(no);
		return vo;
	}
	//	사이즈
	public long getTotalCount() {
		return boardRepository.getTotalCount("");
	}

	// 글 수정 하기 전,
	public BoardVo getContents(Long no, Long userNo) {
		return boardRepository.updateSerach(no, userNo);
	}

	// 글 수정
	public Boolean updateContents(BoardVo vo) {
		return boardRepository.update(vo) == 1;
	}

	// 글 삭제
	public Boolean deleteContents(Long no, Long userNo) {
		BoardVo vo = new BoardVo();
		vo.setNo(no);
		vo.setUserNo(userNo);
		return boardRepository.deletePost(vo)==1;

	}

	// 리스트 가지고 오기, 리스트 찾기결과, 페이징 할 것도
	public Map<String, Object> getContentsList(int currentPage, String keyworld, String keyworld2) {
		Map<String, Object> map = new HashMap<>();
		int pageCount = 5; // 한 페이지에 페이징 몇 개?
		int listCount = 5; // 한 페이지에 리스트 몇 개?
		int startPage = 1; // 시작 페이징
		int nextPage = -1; // -1이면 next가 없다.

		int count = 0; // 리스트 개수
		int totalPage = 1; // 총 페이지
		int endPage = 0; // 끝나는 페이징

		// 키워드 검색 여부
		String input;
		String input2; // 키워드 검색시 '삭제되었습니다.'안 보이게
		String limit = "";
		List<BoardVo> list = null;
		if ("".equals(keyworld) && "".equals(keyworld2)) { // 키워드 검색 아니 경우
			input = "";
			input2 = "";
			count = boardRepository.getTotalCount("");
			System.out.println("리스트 검색 아닌ㄴ 경우 :  " + count);
			map.put("serach", 0);
		} else { // 키워드 검색인 경우
			if (!"".equals(keyworld)) {
				input = keyworld;
				currentPage = 1;
			} else {
				input = keyworld2;
				System.out.println("키워드ㅌ");
			}
			input2 = " and reg_date != \'0000-00-00 00:00:00\'";
			list = boardRepository.findAll("", input, input2);
			count = list.size();
			System.out.println("count : " + count);
			map.put("serach", 1);
		}

		int endcount = listCount;
		totalPage = ((int) Math.ceil(count / (double) listCount));
		endPage = ((int) Math.ceil(totalPage / (double) pageCount));
		if (count <= listCount)
			limit = "";
		else {
			if (currentPage == totalPage) {
				if (count % listCount != 0)
					endcount = count % listCount;
			}
			limit = " LIMIT " + (currentPage - 1) * listCount + " , " + endcount;
		}
		list = boardRepository.findAll(limit, input, input2);
		System.out.println("list : \n" + list.toString());
		
		if (totalPage <= pageCount) {
			endPage = totalPage;
			startPage = 1;
		} else {
			endPage = ((int) Math.ceil(currentPage / (double) pageCount)) * pageCount;
			startPage = (endPage - pageCount) + 1;
		}
		nextPage = endPage + 1;

		if (endPage > totalPage) {
			endPage = totalPage;
		}

		int prePage = startPage - 1; // 이전 페이징
		int startnum = count - (currentPage - 1) * listCount;
		map.put("pageCount", pageCount);
		map.put("listCount", listCount);
		map.put("currentPage", currentPage);
		map.put("totalPage", totalPage);
		map.put("startPage", startPage);
		map.put("endPage", endPage);
		map.put("prePage", prePage);
		map.put("nextPage", nextPage);
		map.put("totalList", count);
		map.put("startnum", startnum);

		map.put("list", list);
		map.put("kwd", input);
		return map;
	}

	// 댓글 달기
	private boolean increaseGroupOrderNO(BoardVo vo) {
		return boardRepository.comment(vo)==1;
	}

}
