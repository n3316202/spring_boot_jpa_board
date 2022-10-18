package edu.global.ex.repository;

import edu.global.ex.dto.BoardDTO;
import edu.global.ex.entity.Board;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.TestPropertySource;

import javax.transaction.Transactional;
import java.util.List;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
class BoardRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;

    @Transactional
    @Test
    @DisplayName("Board 조회 테스트")
    public void findAllBoardTest(){

        long count = boardRepository.count();

        System.out.println(count);

        //"select * from mvc_board order by bGroup desc, bStep asc";
        List<Board> boards = boardRepository.findAll(
                Sort.by(Sort.Direction.DESC, "bgroup")
                        .and(Sort.by(Sort.Direction.ASC, "bstep"))
        );

        for(Board board : boards){
            System.out.println(board);
        }
    }

    @Transactional
    @Test
    @DisplayName("Board insert 테스트")
    public void boardInsertTest(){


    }
}