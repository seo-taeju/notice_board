package notice_board.taeju.entity;

import jakarta.persistence.*;
import lombok.*;

/**
 * 회원엔티티
 * 서비스 레이어에서 "회원일 경우 user 매핑", "비회원일 경우 anonymous 필드 저장" 로직 분기가 필요
 * */

@Entity
@Getter
@Table(name = "users")//db예약어, 이름 충돌 방지
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "login_id", length = 20, unique = true, nullable = false)
    private String loginId; //길이제한 두기,-식별코드(유니크한 값으로 설정)
    @Column(length = 100, nullable = false)
    private String password; //암호화된 비번이 필요, 길이제한 20
    @Column(length = 20, nullable = false, unique = true)
    private String nickname; //닉네임, 무조건 설정, 나중에 중복확인도 20, 로그인 아이디

    // 나중에 리포지에서 user가 작성한 게시글과 댓글을 볼수있게 하자.

    @Builder
    public User(String loginId, String password, String nickname) {
        this.loginId = loginId;
        this.password = password;
        this.nickname = nickname;
    }
}
