package notice_board.taeju.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import notice_board.taeju.entity.Comment;

import java.time.LocalDateTime;

public class CommentDto {

    //req. 댓글작성
    @Getter
    @NoArgsConstructor
    public static class CreateRequest {
        private Long postId;
        private String content;
        private LocalDateTime createdAt;
        private String anonymousName;
        private String anonymousPassword;

    }
    //req. 댓글 수정
    @Getter
    @NoArgsConstructor
    public static class UpdateRequest{
        private String content;
        private String password; //비밀번호 검증용
    }

    
    //res. 댓글 조회
    @Getter
    public static class Response {
        private final Long id;
        private final String content;
        private final String nickAndAnonymous;
        private final LocalDateTime createdAt;

        public Response(Comment comment) {
            this.id = comment.getId();
            this.content = comment.getContent();
            this.nickAndAnonymous = (comment.getUser() != null) ? comment.getUser().getNickname() : comment.getAnonymousName();
            this.createdAt = comment.getCreatedAt();
        }
    }
}
