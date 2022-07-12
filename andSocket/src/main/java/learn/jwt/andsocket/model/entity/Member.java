package learn.jwt.andsocket.model.entity;

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
    private Role role;

    @Builder
    public Member(String username, String password,Role role) {
        this.username = username;
        this.password = password;
        this.role=role;
    }
}
