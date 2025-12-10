package notice_board.taeju.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import notice_board.taeju.entity.Category;

//권한을 가진 클라이언트만 만들수있음.
public class CategoryDto {

    //req. 카테고리 작성
    @Getter
    @NoArgsConstructor
    public static class CreateRequest {
        private String title;

    }
    //req. 카테고리 수정
    @Getter
    @NoArgsConstructor
    public static class UpdateRequest{
        private String title;
        private String password; //비밀번호 검증용
    }

    //응답
    @Getter
    public static class Response {
        private final Long id;
        private final String title;

        public Response(Category category) {
            this.id = category.getId();
            this.title = category.getName();
        }
    }

}
