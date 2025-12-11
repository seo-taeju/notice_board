package notice_board.taeju.repository;

import notice_board.taeju.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    //로그인 시 아이디로 회원찾기
    Optional<User> findByLoginId(String loginId);

    //회원가입시 아이디 중복 체크
    Boolean existsByLoginId(String loginId);

    //회원가입시 아이디 닉네임 체크
    boolean existsByNickname(String nickname);

}
