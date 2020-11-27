package com.multicampus.controller.board;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.multicampus.biz.board.BoardService;
import com.multicampus.biz.board.BoardVO;

@Controller
@SessionAttributes("board")
public class BoardController {	
	
	@Autowired
	private BoardService boardService; // 인터페이스 사용
		
	@RequestMapping(value="/insertBoard.do", method=RequestMethod.GET)
	public String insertBoardView() throws Exception {
		return "insertBoard";
	}

	@RequestMapping(value="/insertBoard.do", method=RequestMethod.POST)
	public String insertBoard(BoardVO vo) throws Exception {
		boardService.insertBoard(vo);
		return "forward:getBoardList.do";
	}
		
	@RequestMapping("/updateBoard.do")
	public String updateBoard(@ModelAttribute("board") BoardVO vo) throws Exception {
		boardService.updateBoard(vo);
		return "forward:getBoardList.do";
	}
	
	@RequestMapping("/deleteBoard.do")
	public String deleteBoard(BoardVO vo) throws Exception {
		boardService.deleteBoard(vo);
		return "forward:getBoardList.do";
	}
	
	@ModelAttribute("conditionList")
	public Map<String, String> searchConditionList() {
		Map<String, String> conditionList = new HashMap<String, String>();
		conditionList.put("제목", "TITLE");
		conditionList.put("내용", "CONTENT");
		return conditionList;
	}
			
	@RequestMapping("/getBoard.do")
	public String getBoard(BoardVO vo, Model model) throws Exception {
		model.addAttribute("board", boardService.getBoard(vo));
		return "getBoard"; 
	}
	
	@RequestMapping("/getBoardList.do")
	public String getBoardList(BoardVO vo, Model model) throws Exception {
		// Null Check
		if(vo.getSearchCondition() == null) vo.setSearchCondition("TITLE");
		if(vo.getSearchKeyword() == null) vo.setSearchKeyword("");
		// Model에 등록한 데이터는 자동으로 request 내장 객체에 저장된다.
		model.addAttribute("boardList", boardService.getBoardList(vo)); // Model 정보 저장
		return "getBoardList"; // View 이름은 리턴한다. 
	}

}













