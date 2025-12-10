package notice_board.taeju;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing // 필수 설정
@SpringBootApplication
public class TaejuApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaejuApplication.class, args);
	}

}
