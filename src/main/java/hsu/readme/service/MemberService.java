package hsu.readme.service;

import hsu.readme.Repository.MemberRepository;
import hsu.readme.api.ResponseMessage;
import hsu.readme.domain.Member;
import hsu.readme.exception.ApiException;
import hsu.readme.exception.ExceptionEnum;
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
            throw new ApiException(ExceptionEnum.DUPLICATE_USER_EMAIL_EXCEPTION);
        }
    }

    private void validateDuplicateMemberByName(String name) {
        List<Member> findMembersByName = memberRepository.findByName(name);
        if(!findMembersByName.isEmpty()) {
            throw new ApiException(ExceptionEnum.DUPLICATE_USER_NAME_EXCEPTION);
        }
    }
}

