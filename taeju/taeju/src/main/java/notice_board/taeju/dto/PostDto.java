package notice_board.taeju.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import notice_board.taeju.Entity.Comment;
import notice_board.taeju.Entity.Post;
import notice_board.taeju.Entity.User;

import java.time.LocalDateTime;
import java.util.List;

public class PostDto {

    //게시글 작성 요청
    @Getter
    @NoArgsConstructor
    public static class CreateRequest {

        private String title;
        private String content;
        private String anonymousName;
        private String anonymousPassword;

        //dto는 엔티티에 접근할수있지만, 엔티티는 dto를 모른다.
        //회원일 경우 user객체를 주입받아서 빌드, 익명이면 null
        public Post toEntity(User user) {
            return Post.builder()
                    .title(title)
                    .content(content)
                    .anonymousName(anonymousName)
                    .anonymousPassword(anonymousPassword)
                    .user(user) //회원이면 user객체를, 비회원이면 null
                    .build();
        }
    }
    //게시글 수정 요청,(제목,내용만 수정 가능하게)
    @Getter
    @NoArgsConstructor
    public static class UpdateRequest {
        private String title;
        private String content;
        private String password; //비회원 확인용, 회원은 비밀번호를 입력하지 않게 하면 됨.
    }

    //게시글 단건 조회 응답, 상세보기(댓글,작성자id,익명인지 회원인지도,생성시간도,)
    @Getter
    public static class FeedResponse {
        private final Long id;
        private final String title;
        private final String content; //목록에서도 게시글이 보여야됨
        private final String nicknameAndAnonymous; //닉네임이 올수도, 익명이 올수도있어서
        private final Long viewCount;
        private final Integer likeCount;
        private final LocalDateTime createdAt;
        private final List<CommentDto.Response> comments; //댓글 목록이 한번에 보이게

        //엔티티를 DTO로 변환
        public FeedResponse(Post post) {
            this.id = post.getId();
            this.title = post.getTitle();
            this.content = post.getContent();
            this.viewCount = post.getViewCount();
            this.likeCount = post.getLikeCount();
            this.createdAt = post.getCreatedAt();

            //작성자 처리 로직(회원인지, 익명인지)
            if (post.getUser() != null) {
                this.nicknameAndAnonymous = post.getUser().getNickname();
            }else  {
                this.nicknameAndAnonymous = post.getAnonymousName();
            }
            
            //댓글 리스트 변환하기
            //한꺼번에 많으 데이터는 성능을 느리게 하기 때문에 이것을 해결해줘야됨.
            this.comments

        }
    }
    //게시글 목록 조회 응답

}
