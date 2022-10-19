package edu.global.ex.service;

import edu.global.ex.dto.BoardDTO;
import edu.global.ex.dto.PageRequestDTO;
import edu.global.ex.dto.PageResultDTO;
import edu.global.ex.entity.Board;
import edu.global.ex.repository.BoardRepository;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;


@Service
@Slf4j
public class BoardServiceImpl implements BoardService {

    @Autowired
    private BoardRepository boardRepository;

    @Override
    public List<BoardDTO> getList() {

        List<BoardDTO> dtoList = new ArrayList<BoardDTO>();

        log.info("getList() ..");

        //"select * from mvc_board order by bGroup desc, bStep asc";
        List<Board> boards = boardRepository.findAll(
                Sort.by(Sort.Direction.DESC, "bgroup")
                        .and(Sort.by(Sort.Direction.ASC, "bstep"))
        );

        for (Board board : boards) {
            dtoList.add(entityToDto(board));
        }
        return  dtoList;
    }

    @Transactional
    @Override
    public Long registerReply(BoardDTO boardDTO) {
        log.info("registerReply() ..");
        //  update mvc_board set bstep = bstep + 1 where bgroup =#{bgroup} and bstep > #{bstep}
        boardRepository.replayShape(boardDTO);//답글의 위치를 먼저 잡아준 후

        boardDTO.setBid(null);
        boardDTO.setBhit(0L);
        boardDTO.setModdate(null);
        boardDTO.setRegdate(null);

        boardDTO.setBstep(boardDTO.getBstep()+1);
        boardDTO.setBindent(boardDTO.getBindent()+1);
        boardDTO.setBgroup(boardDTO.getBgroup());

        Board board = dtoToEntity(boardDTO);

        return boardRepository.save(board).getBid();

    }

    @Override
    public Long register(BoardDTO boardDTO) {


        Board board = dtoToEntity(boardDTO);
        boardRepository.save(board);

        board.setBgroup(board.getBid());
        boardRepository.save(board);

        return board.getBid();

    }

    @Override
    public BoardDTO read(long bid) {

        return entityToDto(boardRepository.findById(bid).orElse(null));
    }

    @Override
    public Long modify(BoardDTO boardDTO) {
        return boardRepository.save(dtoToEntity(boardDTO)).getBid();
    }

    @Override
    public PageResultDTO<BoardDTO, Board> getList(PageRequestDTO requestDTO) {

        Sort sort = Sort.by(Sort.Direction.DESC, "bgroup")
                .and(Sort.by(Sort.Direction.ASC, "bstep"));

        Pageable pageable = requestDTO.getPageable(sort);

        Page<Board> result = boardRepository.findAll(pageable);


        Function<Board,BoardDTO> fn = (entity -> entityToDto(entity));

        // JPA의 처리결과인 Page<Entity>와 Function을 전달해 엔티티 객체들을 DTO의 리스트로 변환하고 화면에 페이지 처리와 필요한 값들을 생성한다
        return new PageResultDTO<>(result,fn);
    }
}
