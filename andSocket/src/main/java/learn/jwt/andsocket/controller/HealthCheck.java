package learn.jwt.andsocket.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log4j2
public class HealthCheck {

    @GetMapping("/ping")
    public ResponseEntity<String> healthCheck(){
        log.info("health Check");
        System.out.println("pingpong = " );
        return new ResponseEntity<>("pong", HttpStatus.BAD_REQUEST);
    }
}
