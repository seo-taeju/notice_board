package notice_board.taeju.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import notice_board.taeju.entity.Post;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class PostDto {

    //req, 게시글 작성
    @Getter
    @NoArgsConstructor
    public static class CreateRequest{
        private String title;
        private String content;
        private Long categoryId; // 카테고리 id만 전달
        private String anonymousName;
        private String anonymousPassword;
    }

    //req. 게시글 수정
    @Getter
    @NoArgsConstructor
    public static class UpdateRequest{
        private String title;
        private String content;
        private Long categoryId; // 카테고리 변경시
        private String password; //비밀번호 검증용
    }

    //res, 피드 응답(목록+상세겸용)
    @Getter
    public static class FeedResponse{
        private final Long id;
        private final String title;
        private final String content;
        private final String categoryName;
        private final String nickAndAnonymous;
        private final Long viewcount;
        private final Integer likeCount;
        private final LocalDateTime createdAt;
        private final List<CommentDto.Response> comments;

        public FeedResponse(Post post) {
            this.id = post.getId();
            this.title = post.getTitle();
            this.content = post.getContent();
            this.categoryName = post.getCategory().getName();

            this.viewcount = post.getViewCount();
            this.likeCount = post.getLikeCount();
            this.createdAt = post.getCreatedAt();

            // 작성자 (회원/익명) 분기 처리
            this.nickAndAnonymous = (post.getUser() != null) ? post.getUser().getNickname() : post.getAnonymousName();
            
            //댓글들을 리스트로 변환
            this.comments = post.getComments().stream()
                    .map(CommentDto.Response:: new)
                    .collect(Collectors.toList());
        }
    }

}
