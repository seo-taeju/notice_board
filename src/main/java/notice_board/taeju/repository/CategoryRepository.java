package notice_board.taeju.repository;

import notice_board.taeju.entity.Category;
import notice_board.taeju.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    // 카테고리 이름으로 찾기 (예: "자유게시판"이 있는지 확인), 없으면 null반환
    Optional<Category> findByName(String name);
    
}
