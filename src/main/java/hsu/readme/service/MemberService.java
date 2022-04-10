package hsu.readme.service;

import hsu.readme.Repository.MemberRepository;
import hsu.readme.domain.Member;
import hsu.readme.exception.ApiException;
import hsu.readme.exception.ExceptionEnum;
import hsu.readme.service.MemberServiceValidation.MemberValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;


    /*
     * 회원 가입
     */
    @Transactional
    public Long join(Member member) {
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        //이메일과 이름에 대해 중복체크
        MemberValidation.validateDuplicateMemberByEmail(memberRepository.findByName(member.getEmail()));
        MemberValidation.validateDuplicateMemberByName(memberRepository.findByName(member.getName()));
        MemberValidation.emptyCheckMemberUniversity(member.getUniversity());
        MemberValidation.emptyCheckMemberMajor(member.getMajor());
    }

    /*
     * 로그인
     */
    public Long login(String email, String pw) {
        List<Member> findMembers = memberRepository.findByEmail(email);
        if (findMembers.isEmpty())
            throw new ApiException(ExceptionEnum.LOGIN_EMAIL_EXCEPTION);
        if (!findMembers.get(0).getPassword().equals(pw))
            throw new ApiException(ExceptionEnum.LOGIN_PASSWORD_EXCEPTION);
        return findMembers.get(0).getId();
    }

    public Member findOne(Long memberId){
        Member member = memberRepository.findOne(memberId);
        if(member == null) throw new ApiException(ExceptionEnum.USER_NOT_EXIST_EXCEPTION);
        return member;
    }
}

