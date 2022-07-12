package learn.jwt.andsocket.controller;

import learn.jwt.andsocket.model.dto.MemberDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/api")
public class MemberController {

    @PostMapping("/member")
    public ResponseEntity<String> signup(MemberDTO.SignUpDTO signUpDTO){
        
    }
}
