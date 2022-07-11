package learn.jwt.andsocket.repository;

import learn.jwt.andsocket.model.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member,Long> {
}
