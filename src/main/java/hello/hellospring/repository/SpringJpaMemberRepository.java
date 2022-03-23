package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringJpaMemberRepository extends JpaRepository<Member, Long>,MemberRepository {

    @Override
    Optional<Member> findByName(String name);//규칙에 맞게 메소드 이름을 다시 작성하면 자동으로 sql문을 짜준다.
}
