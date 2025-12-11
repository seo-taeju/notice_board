package notice_board.taeju.entity;

import jakarta.persistence.*;
import lombok.*;
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

    // [관계 설정 1] 작성자 (회원 or null)
    // 비회원 작성 가능 (익명 필드 존재) -> User는 Nullable
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    // [관계 설정 2] 카테고리 (필수)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    // 익명 작성자용 필드
    @Column(name = "anonymous_name", length = 20)
    private String anonymousName;


    @Column(name = "anonymous_password", length = 100)
    private String anonymousPassword;

    // 게시글 삭제 시 댓글도 삭제 (OneToMany)
    /**고민좀 해보기, 이건 나중에 별도로 처리하는 것을 만들어야 될듯.*/
    // 게시글 삭제 시 첨부파일 정보도 삭제

    @Builder
    public Post(String title, String content, User user, Category category, String anonymousName, String anonymousPassword) {
        this.title = title;
        this.content = content;
        this.user = user;
        this.category = category;
        this.anonymousName = anonymousName;
        this.anonymousPassword = anonymousPassword;
    }

    // 비즈니스 로직 (조회수 증가 등)
    public void increaseViewCount() {
        this.viewCount++;
    }

}