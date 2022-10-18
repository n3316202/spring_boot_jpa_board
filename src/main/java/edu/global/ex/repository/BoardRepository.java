package edu.global.ex.repository;

import edu.global.ex.dto.BoardDTO;
import edu.global.ex.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;


public interface BoardRepository extends JpaRepository<Board,Long> {
    //쿼리 메소드 = find + (엔티티이름) + By + 변수이름
    //"select * from mvc_board order by bGroup desc, bStep asc";

    //벌크성 업데이트
    //update mvc_board set bstep = bstep + 1 where bgroup =#{bgroup} and bstep > #{bstep}
    @Modifying(clearAutomatically = true)
    @Query("update Board board set board.bstep = board.bstep + 1 where board.bgroup = :#{#boardDTO.bgroup} and board.bstep > :#{#boardDTO.bstep}")
    int replayShape(@Param("boardDTO")BoardDTO boardDTO);

    Optional<Board> findFirstByOrderByBidDesc();
}
