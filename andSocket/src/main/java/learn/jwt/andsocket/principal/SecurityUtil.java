package learn.jwt.andsocket.principal;

import learn.jwt.andsocket.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import sideproject.junior.gamego.repository.MemberRepository;


@RequiredArgsConstructor
@Service
public class SecurityUtil {

    private final MemberRepository memberRepository;

    public String returnLoginMemberInfo(){
        UserDetails loginMember = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return loginMember.getUsername();
    }


    public Long getMemberId(){
        return memberRepository.findByUsername(returnLoginMemberInfo()).get().getId();
    }
}
