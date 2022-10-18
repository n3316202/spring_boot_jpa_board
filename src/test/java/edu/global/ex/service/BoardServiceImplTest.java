package edu.global.ex.service;

import edu.global.ex.dto.BoardDTO;
import edu.global.ex.entity.Board;
import edu.global.ex.repository.BoardRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.TestPropertySource;

import javax.transaction.Transactional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
class BoardServiceImplTest {

    @Autowired
    private BoardService boardService;

    @Transactional
    @Test
    @DisplayName("Board 서비스 조회 테스트")
    public void findAllBoardTest(){

        List<BoardDTO> dtoList = boardService.getList();

        System.out.println(dtoList.size());

        for(BoardDTO board : dtoList){
            System.out.println(board);
        }
    }
}