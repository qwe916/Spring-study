package hello.servlet.domain.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberRepositoryTest {
    //new 사용 안됨(싱글톤을 위해)
    MemberRepository memberRepository = MemberRepository.getInstance();

    @AfterEach
    void afterEach() {
        memberRepository.clearStore();

    }

    @Test
    void save() {
        //given
        Member member = new Member("hello", 20);
        //when
        Member saveId = memberRepository.save(member);
        //then
        Member findId = memberRepository.findById(saveId.getId());
        assertThat(findId).isEqualTo(saveId);
    }

    @Test
    void findAll() {
        //given
        Member member1 = new Member("hello1", 20);
        Member member2 = new Member("hello2", 30);
        memberRepository.save(member1);
        memberRepository.save(member2);
        //when
        List<Member> allMembers = memberRepository.findAll();
        //then
        assertThat(allMembers.size()).isEqualTo(2);
        assertThat(allMembers).contains(member1, member2);
    }
}