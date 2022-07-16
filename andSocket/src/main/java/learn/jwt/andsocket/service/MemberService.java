package learn.jwt.andsocket.service;

import learn.jwt.andsocket.model.dto.MemberDTO;
import learn.jwt.andsocket.model.entity.Member;
import learn.jwt.andsocket.model.entity.Role;
import learn.jwt.andsocket.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Log4j2
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public String SignUpApi(MemberDTO.SignUpDTO signUpDTO) throws IllegalStateException{
        if (signUpDTO.getUsername().equals(null)||signUpDTO.getPassword().equals(null)){
            throw new IllegalStateException("아이디 또는 비밀번호가 입력되지 않았습니다.");
        }else if (!signUpDTO.getPassword().matches("(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,20}")){
            return "비밀번호 양식을 지켜주세요";
        }else {
            signUpDTO.setRole(Role.MEMBER);
            memberRepository.save(signUpDTO.toEntity());
            return "회원가입이 정상적으로 동작하였습니다.";
        }
    }

    public List<Member> MemberListApi(){
        return memberRepository.findAll();
    }


    @Transactional
    public String MemberStateChangeApi(MemberDTO.ChangeStateDTO changeStateDTO){
        Optional<Member> findMember = memberRepository.findByUsername(changeStateDTO.getUsername());
        boolean changeState = findMember.get().ChangeMemberState(changeStateDTO);
        if (changeState){
            return "회원 정보 변경이 완료되었습니다";
        }else {
            return "회원 정보 변경중 문제가 발생하였습니다";
        }
    }

    @Transactional
    public String MemberDeleteApi(MemberDTO.DeleteDTO deleteDTO){
        memberRepository.delete(memberRepository.findById(deleteDTO.getId()).get());
        return "회원이 성공적으로 삭제되었습니다";
    }


}
