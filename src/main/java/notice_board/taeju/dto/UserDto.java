package notice_board.taeju.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import notice_board.taeju.entity.User;

/**
 * 회원가입/조회
 * */
public class UserDto {
    //회원가입 요청(req)
    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class SigupRequest {
        //클라이언트가 json형식으로 보냄
        private String password;
        private String nickname;

       //서비스에서 핼퍼메서드 사용해서 toEntity 제작하기
    }
    
    //req. 회원 수정
    @Getter
    @NoArgsConstructor
    public static class UpdateRequest{
        private String loginId;
        private String nickname;
        private String password;
    }

    //회원 정보 응답(res)
    //마이페이지 정보 같은것. 패스워드는 필요없음.
    @Getter
    public static class Response{
        private final Long id; //랜덤값으로 보내주기
        private final String loginId;
        private final String nickname;

        public Response(User user){
            this.id = user.getId();
            this.loginId = user.getLoginId();
            this.nickname = user.getNickname();
        }
    }
}
