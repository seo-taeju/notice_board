package notice_board.taeju.dto;

import jakarta.persistence.Column;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import notice_board.taeju.Entity.User;

/**
 * 회원가입/조회
 * */
public class UserDto {
    //회원가입 요청(req)
    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class SigupRequest {
        //클라이언트가 json형식으로 보냄
        private String loginId;
        private String password;
        private String nickname;

        //클라이언트가 보낸 password를 암호화 해서 빌더하기
        //그래서 매개변수를 암호화된 비밀번호만 받아서 넣는거임
        //이부분은 서비스단에서 만들거임.
        public User toEntity(String encodedPassword) {
           return User.builder()
                   .loginId(this.loginId)
                   .password(encodedPassword) //비밀번호는 암호화해서 db의 저장해야된다.
                   .nickname(this.nickname)
                   .build();
        }
    }

    //회원 정보 응답(res)
    //마이페이지 정보 같은것. 패스워드는 필요없음.
    @Getter
    public static class Response{
        private final Long id;
        private final String loginId;
        private final String nickname;

        public Response(User user){
            this.id = user.getId();
            this.loginId = user.getLoginId();
            this.nickname = user.getNickname();
        }
    }
}
