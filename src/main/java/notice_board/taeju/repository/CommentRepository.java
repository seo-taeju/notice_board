package notice_board.taeju.repository;

import notice_board.taeju.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository

public interface CommentRepository extends JpaRepository<Comment, Integer> {

    //댓글 전부 한방에 삭제하는 기능
    @Modifying(clearAutomatically = true)//영속석 컨텍스트 초기화(데이터 불일치 방지)
    @Query("delete from Comment c where c.post.id = :postId")
    void deleteAllByPostId(@Param("postId") Integer postId);

}
