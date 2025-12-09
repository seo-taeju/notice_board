package notice_board.taeju.Entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


/**
 * 게시글엔티티, @id, title, content, viewCount
 *
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 40, nullable = false)
    private String title;

    @Lob // Large Object (TEXT, LONGTEXT 등)
    @Column(columnDefinition = "LONGTEXT", nullable = false)
    private String content;

    @Column(name = "view_count")
    private Long viewCount = 0L; // Default 0

    @Column(name = "like_count")
    private Integer likeCount = 0; // Default 0

    // 비회원 작성 가능 (익명 필드 존재) -> User는 Nullable
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "anonymous_name", length = 20)
    private String anonymousName;

    @Column(name = "anonymous_password", length = 100)
    private String anonymousPassword;

    // 게시글 삭제 시 댓글도 삭제 (OneToMany)
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    // 게시글 삭제 시 첨부파일 정보도 삭제
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Attachment> attachments = new ArrayList<>();

    @Builder
    public Post(String title, String content, User user, String anonymousName, String anonymousPassword) {
        this.title = title;
        this.content = content;
        this.user = user;
        this.anonymousName = anonymousName;
        this.anonymousPassword = anonymousPassword;
    }

    // 비즈니스 로직 (조회수 증가 등)
    public void increaseViewCount() {
        this.viewCount++;
    }
}