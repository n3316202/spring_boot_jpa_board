package edu.global.ex.controller;

import edu.global.ex.dto.BoardDTO;
import edu.global.ex.dto.PageRequestDTO;
import edu.global.ex.service.BoardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private BoardService boardService;


/*    @GetMapping("/list")
    public String list(Model model){
        log.info("list() ..");
        model.addAttribute("boardList",boardService.getList());
        return "list";
    }*/

    @GetMapping("/list")
    public String list(@ModelAttribute PageRequestDTO pageRequestDTO , Model model){
        // 실제로 model에 추가되는 데이터 : PageResultDTO
        // => Model을 이용해 GuestbookServiecImpl에서 반환하는 PageResultDTO를 result 라는 이름으로 전달
        model.addAttribute("result", boardService.getList(pageRequestDTO));
        return "list";
    }

    @PostMapping("/write")
    public String write(BoardDTO boardDTO) {
        log.info("write() ..");
        log.info("BoardDTO .." + boardDTO);

        boardService.register(boardDTO);

        return "redirect:list";
    }

    @GetMapping("/content_view")
    public String content_view(BoardDTO boardDTO,Model model) {
        log.info("content_view() ..");

        model.addAttribute("content_view",boardService.read(boardDTO.getBid()));

        return "content_view";
    }

    //http://localhost:8282/board/reply_view?bid=7
    @GetMapping("/reply_view")
    public String reply_view(BoardDTO boardDTO,Model model) {
        log.info("reply_view() ..");

        model.addAttribute("reply_view", boardService.read(boardDTO.getBid()));

        return "reply_view";
    }

    @PostMapping("/modify")
    public String modify(BoardDTO boardDTO) {
        log.info("modify() ..");

        Long id = boardService.modify(boardDTO);

        log.info("modify() .. id number::" + id);

        return "redirect:list";
    }

    @GetMapping("/write_view")
    public String write_view() {
        log.info("write_view() ..");
        return "write_view";// write_view.html로 가시오
    }

    //http://localhost:8282/reply
    //post방식으로 보냈으므로 PostMapping으로 받는다.
    @PostMapping("/reply")
    public String reply(BoardDTO boardDTO) {
        log.info("reply() ..");

        boardService.registerReply(boardDTO);

        return "redirect:list"; //로직 수행한 후 list로 redirect해준다.
    }

}
