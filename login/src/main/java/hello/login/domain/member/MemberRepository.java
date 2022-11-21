package hello.login.domain.member;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.*;

@Slf4j
@Repository
public class MemberRepository {

    private static Map<Long, Member> store = new HashMap<>();//static 사용
    private long sequnece = 0L;

    public Member save(Member member) {
        member.setId(++sequnece);
        log.info("save : member={}", member);
        store.put(member.getId(), member);
        return member;
    }

    public Member findById(Long id) {
        return store.get(id);
    }

    public Optional<Member> findByLoginId(String loginId) {//회원이 없을 수 있으니 Optional을 사용한다.
        /*List<Member> all = findAll();
        for (Member m:all
             ) {
            if (m.getLoginId().equals(loginId)) {
                return Optional.of(m);//만약 회원가입이 되어 있으면 m반환
            }
        }
        return Optional.empty();//없으면 empty 반환*/

        return findAll().stream()
                .filter(m -> m.getLoginId().equals(loginId))
                .findFirst();//위의 내용과 동이라지만 stream을 이용한다.
    }
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }
}
