package notice_board.taeju.service;

import lombok.RequiredArgsConstructor;
import notice_board.taeju.dto.UserDto;
import notice_board.taeju.entity.User;
import notice_board.taeju.repository.CategoryRepository;
import notice_board.taeju.repository.UserRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly=true)
public class UserService {

    private final UserRepository userRepository;
    //private final PasswordEncoder passwordEncoder; //스프링 시큐리티 설정 필요

    // 재시도 최대 횟수 설정 (무한 루프 방지)
    private static final int MAX_RETRY_COUNT = 5;

    //회원가입
    @Transactional //쓰기가능
    public Long signup(UserDto.SigupRequest dto){
        //중복 검사
        if (userRepository.existsByNickname(dto.getNickname())){
            throw new IllegalArgumentException("이미 사용중인 닉네임입니다.");
        }
 /**
        비밀번호 암호화
        String encodedPassword = passwordEncoder.encode(dto.getPassword());
        스프링 시큐리티 만들면 그떄 추가하자.
*/
        //저장하기 dto -> entity 로 변환
        User user = User.builder()
                .nickname(dto.getNickname())
                .loginId(UineLoginId())
                .password(dto.getPassword()) //
                .build();

        return userRepository.save(user).getId();
    }

    //내부 머서드, 유니크코드 생성 및 중복 체크 및 재시도
    private String UineLoginId(){
        String temp = "";
        int retryCount = 0;
        do  {
            if(retryCount >= MAX_RETRY_COUNT){
                throw new IllegalStateException("식별자 생성 중 충돌이 반복되어서 실패 되었습니다.");
            }
            temp = RandomStringUtils.randomAlphanumeric(10);
                retryCount++;

            temp += UUID.randomUUID().toString();
            //db에 존재하면 투루, 없으면 false로 탈출
        } while (userRepository.existsByLoginId(temp));
        return temp;
    }

}
