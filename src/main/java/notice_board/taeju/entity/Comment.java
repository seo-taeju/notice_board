package notice_board.taeju.entity;


import jakarta.persistence.*;
import lombok.*;
import lombok.NoArgsConstructor;

/**
 * 댓글엔티티, id, content
 * 댓글을 작성할 수 도있고, 아닐 수 도있고
 * */

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 5000, nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id") // Nullable (익명 댓글 가능)
    private User user;

    @Column(name = "anonymous_name", length = 20)
    private String anonymousName;

    @Column(name = "anonymous_password", length = 100)
    private String anonymousPassword;

    @Builder
    public Comment(String content, Post post, User user, String anonymousName, String anonymousPassword) {
        this.content = content;
        this.post = post;
        this.user = user;
        this.anonymousName = anonymousName;
        this.anonymousPassword = anonymousPassword;
    }
}
