package hsu.readme.service;

import hsu.readme.Repository.MemberRepository;
import hsu.readme.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService{

    private final MemberRepository memberRepository;


    /*
    * 회원 가입
    */
    @Transactional
    public Long join(Member member){
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        //이메일과 이름에 대해 중복체크
        validateDuplicateMemberByEmail(member.getEmail());
        validateDuplicateMemberByName(member.getName());
    }

    private void validateDuplicateMemberByEmail(String email) {
        List<Member> findMembersByEmail = memberRepository.findByEmail(email);
        if(!findMembersByEmail.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    private void validateDuplicateMemberByName(String name) {
        List<Member> findMembersByName = memberRepository.findByName(name);
        if(!findMembersByName.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 이름입니다.");
        }
    }
}

