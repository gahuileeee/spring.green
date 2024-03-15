package kr.co.ch09.repository;

import kr.co.ch09.entity.User1;
import kr.co.ch09.entity.User2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface User2repository extends JpaRepository<User2, String> {

}
