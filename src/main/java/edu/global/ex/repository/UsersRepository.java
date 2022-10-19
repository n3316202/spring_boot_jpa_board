package edu.global.ex.repository;

import edu.global.ex.dto.BoardDTO;
import edu.global.ex.entity.Board;
import edu.global.ex.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;


public interface UsersRepository extends JpaRepository<Users,Long> {
    //쿼리 메소드 = find + (엔티티이름) + By + 변수이름
    Users findByEmail(String email);
}

