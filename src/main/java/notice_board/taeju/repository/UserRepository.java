package notice_board.taeju.repository;

import notice_board.taeju.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    //로그인 시 아이디로 회원찾기
    Optional<User> findByLoginId(String loginId);
    
    //회원가입시 아이디 중복 체크
    Boolean existsByLoginId(String loginId);

    boolean existsByNickname(String nickname);

}
