package notice_board.taeju.service;

import lombok.RequiredArgsConstructor;
import notice_board.taeju.repository.CategoryRepository;
import notice_board.taeju.repository.PostRepository;
import notice_board.taeju.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly=true)
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    //편의 매서드( 엔티티 생성)

    //게시물 작성

    //게시물 수정

    //게시물

}
