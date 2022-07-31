package learn.jwt.andsocket.controller;

import learn.jwt.andsocket.model.dto.MemberDTO;
import learn.jwt.andsocket.model.entity.Member;
import learn.jwt.andsocket.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/members")
    public ResponseEntity<List<Member>> memberList(){
        List<Member> members = memberService.MemberListApi();
        return new ResponseEntity<>(members,HttpStatus.OK);
    }

    @PostMapping("/member")
    public ResponseEntity<String> signup(@RequestBody MemberDTO.SignUpDTO signUpDTO){
        log.info("회원가입");
        log.info("아이디 ={}",signUpDTO.getUsername());
        log.info("signUpDto ={}",signUpDTO);
        String signUpAPiState = memberService.SignUpApi(signUpDTO);
        return new ResponseEntity<>(signUpAPiState, HttpStatus.OK);
    }

    @PutMapping("/member")
    public ResponseEntity<String> changeMemberState(MemberDTO.ChangeStateDTO changeStateDTO){
        String changeMemberAPiState = memberService.MemberStateChangeApi(changeStateDTO);
        return new ResponseEntity<>(changeMemberAPiState,HttpStatus.OK);
    }

    @DeleteMapping("/member")
    public ResponseEntity<String> deleteMember(MemberDTO.DeleteDTO deleteDTO){
        String deleteApiState = memberService.MemberDeleteApi(deleteDTO);
        return new ResponseEntity<>(deleteApiState,HttpStatus.OK);
    }

}
