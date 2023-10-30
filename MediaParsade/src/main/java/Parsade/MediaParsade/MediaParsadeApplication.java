package Parsade.MediaParsade;

import Parsade.MediaParsade.config.MyBatisConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@Slf4j
@Import(MyBatisConfig.class)
@SpringBootApplication
public class MediaParsadeApplication {

	public static void main(String[] args) {
		SpringApplication.run(MediaParsadeApplication.class, args);
	}

}
