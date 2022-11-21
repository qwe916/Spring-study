package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

//@Service//스프링에 올라올 때 MemberService를 등록
@Transactional//저장하거나 변경할때 항상 필요
public class MemberService {
    private final MemberRepository memberRepository;

    //@Autowired//스프링이 뜰때 자동으로 MemberService에 MemoryMemberRepository를 주입
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /*
    * 회원가입*/
    public Long join(Member member) {
        //같은 이름이 있는 중복 회원
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {//ifPresent = 값이 있다면
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }
    /*
    * 전체 회원 조회
    */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOnd(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
