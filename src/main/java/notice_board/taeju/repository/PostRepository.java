package notice_board.taeju.repository;

import notice_board.taeju.entity.Category;
import notice_board.taeju.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface PostRepository extends JpaRepository<Post, Integer>{

    //전체 게시글 조회(메인 피드용)
    //페이징 처리하기
    @Override
    Page<Post> findAll(Pageable pageable);

    //특정 카테고리 글만 조회
    Page<Post> findByCategory(Category category, Pageable pageable);
    
    //제목 검색(옵션)
    Page<Post> findByTitleContaining(String keyword, Pageable pageable);
}
