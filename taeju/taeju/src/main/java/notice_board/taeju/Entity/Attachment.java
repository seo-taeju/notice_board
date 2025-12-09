package notice_board.taeju.Entity;


import jakarta.persistence.*;
import lombok.Data;
import lombok.*;
import lombok.NoArgsConstructor;

/**
 * post에 첨부자료도 올리싶어서 따로 마들었음.
 * 이미지라도 올릴 수 있게 하고싶어서
 * */


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Attachment extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    @Column(name = "original_filename", nullable = false)
    private String originalFilename;

    @Column(name = "store_filename", nullable = false)
    private String storeFilename;

    @Column(name = "file_path", length = 500, nullable = false)
    private String filePath;

    @Builder
    public Attachment(Post post, String originalFilename, String storeFilename, String filePath) {
        this.post = post;
        this.originalFilename = originalFilename;
        this.storeFilename = storeFilename;
        this.filePath = filePath;
    }

    // Post와의 연관관계 편의 메서드 예시
    public void setPost(Post post) {
        this.post = post;
        if (!post.getAttachments().contains(this)) {
            post.getAttachments().add(this);
        }
    }
}
