package notice_board.taeju.entity;


import jakarta.persistence.*;
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

    // [관계 설정] Post -> Attachment 단방향 매핑
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    @Column(name = "original_filename", nullable = false)
    private String originalFilename;

    @Column(name = "store_filename", nullable = false)
    private String storeFilename;

    // 이미지 접근 경로. (예: /images/{storeFilename})
    @Column(name = "file_path", length = 500, nullable = false)
    private String filePath;

    @Builder
    public Attachment(Post post, String originalFilename, String storeFilename, String filePath) {
        this.post = post;
        this.originalFilename = originalFilename;
        this.storeFilename = storeFilename;
        this.filePath = filePath;
    }

}
