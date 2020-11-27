package com.multicampus.controller.board;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.multicampus.biz.board.BoardService;
import com.multicampus.biz.board.BoardVO;

@Controller
@SessionAttributes("board")
public class BoardController {	
	
	@Autowired
	private BoardService boardService; // �������̽� ���	
	
	@RequestMapping("/dataTrasform.do")
	@ResponseBody
	public List<BoardVO> dataTrasform(BoardVO vo) throws Exception {
		vo.setSearchCondition("TITLE");
		vo.setSearchKeyword("");
		return boardService.getBoardList(vo);
	}
		
	@RequestMapping(value="/insertBoard.do", method=RequestMethod.GET)
	public String insertBoardView() throws Exception {
		return "insertBoard";
	}
	
	@RequestMapping(value="/insertBoard.do", method=RequestMethod.POST)
	public String insertBoard(BoardVO vo) throws Exception {
		// ���� ���ε� ó��
		MultipartFile uploadFile = vo.getUploadFile();
		if(!uploadFile.isEmpty()) // ���ε� ������ �ϳ��� �ִٸ�...
			uploadFile.transferTo(new File("C:/DEV/" + uploadFile.getOriginalFilename()));
		
		// �� ��� ��� ó��
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
		conditionList.put("����", "TITLE");
		conditionList.put("����", "CONTENT");
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
		// Model�� ����� �����ʹ� �ڵ����� request ���� ��ü�� ����ȴ�.
		model.addAttribute("boardList", boardService.getBoardList(vo)); // Model ���� ����
		return "getBoardList"; // View �̸��� �����Ѵ�. 
	}

}













