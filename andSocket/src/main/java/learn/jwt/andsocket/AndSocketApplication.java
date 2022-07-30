package learn.jwt.andsocket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class AndSocketApplication {

    static {
        System.setProperty("spring.config.location", "classpath:/application.yml,classpath:/application-jwt.yml");
    }

    public static void main(String[] args) {
        SpringApplication.run(AndSocketApplication.class, args);
    }

}
