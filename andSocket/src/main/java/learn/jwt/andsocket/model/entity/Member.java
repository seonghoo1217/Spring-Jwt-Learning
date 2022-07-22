package learn.jwt.andsocket.model.entity;

import learn.jwt.andsocket.model.dto.MemberDTO;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "memberId")
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "authority")
    @Enumerated(EnumType.STRING)
    private Role role;

    @Builder
    public Member(String username, String password,Role role) {
        this.username = username;
        this.password = password;
        this.role=role;
    }

    public boolean ChangeMemberState(MemberDTO.ChangeStateDTO changeStateDTO){
        if (changeStateDTO.getUsername()==null&&changeStateDTO.getPassword()==null){
            return false;
        }else {
            this.username=changeStateDTO.getUsername();
            this.password=changeStateDTO.getPassword();
            return true;
        }
    }
}
