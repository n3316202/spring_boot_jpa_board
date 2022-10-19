package edu.global.ex.service;

import edu.global.ex.dto.BoardDTO;
import edu.global.ex.dto.PageRequestDTO;
import edu.global.ex.dto.PageResultDTO;
import edu.global.ex.entity.Board;

import java.util.List;


public interface BoardService  {
    List<BoardDTO> getList();//리스트 메소드
    Long registerReply(BoardDTO board); //댓글쓰기
    Long register(BoardDTO board); //글쓰기
    BoardDTO read(long bid);//글보기 메소드
    Long modify(BoardDTO board); //글수정
    //paging 포함
    public PageResultDTO<BoardDTO, Board> getList(PageRequestDTO requestDTO);

/*

    int remove(BoardVO board); //글삭제

    //paging
    int getTotal();
    List<BoardVO> getList(Criteria criteria);//리스트 메소드*/

    // service 에서는 파라미터를 DTO타입으로 받기 때문에 JPA로 처리하기 위해서는 Entity 타입의 객체로 변환해야 하는 작업이 반드시 필요하다
    // java 8 버전부터 인터페이스의 실제 내용을 가지는 코드는 default라는 키워드로 생성할 수 있다 -> 실제 코드를 인터페이스에 선언할 수 있다
    // => 추상클래스를 생략하는것이 가능해 졌다

    default Board dtoToEntity(BoardDTO dto){

        Board entity = Board.builder()
                .bcontent(dto.getBcontent())
                .bhit(dto.getBhit())
                .bstep(dto.getBstep())
                .bindent(dto.getBindent())
                .bgroup(dto.getBgroup())
                .bwriter(dto.getBwriter())
                .btitle(dto.getBtitle())
                .bid(dto.getBid())
                .build();

        return entity;
    }

    default BoardDTO entityToDto(Board entity){

        BoardDTO dto = BoardDTO.builder()
                .bcontent(entity.getBcontent())
                .bindent(entity.getBindent())
                .btitle(entity.getBtitle())
                .bhit(entity.getBhit())
                .bstep(entity.getBstep())
                .bgroup(entity.getBgroup())
                .bwriter(entity.getBwriter())
                .regdate(entity.getRegDate())
                .moddate(entity.getModDate())
                .bid(entity.getBid())
                .build();

        return dto;
    }


}
