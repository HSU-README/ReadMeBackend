package hsu.readme.service;

import hsu.readme.Repository.MemberRepository;
import hsu.readme.domain.Member;
import hsu.readme.exception.ApiException;
import hsu.readme.exception.ExceptionEnum;
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
        validateDuplicateMemberByEmail(member.getEmail());
        validateDuplicateMemberByName(member.getName());
        emptyCheckMemberUniversity(member.getUniversity());
        emptyCheckMemberMajor(member.getMajor());
    }

    private void emptyCheckMemberUniversity(String university) {
        if (university.length() == 0) throw new ApiException(ExceptionEnum.EMPTY_USER_UNIVERSITY_EXCEPTION);
    }

    private void emptyCheckMemberMajor(String major) {
        if (major.length() == 0) throw new ApiException(ExceptionEnum.EMPTY_USER_MAJOR_EXCEPTION);
        if (StringUtils.isEmpty(major)) throw new ApiException(ExceptionEnum.EMPTY_USER_MAJOR_EXCEPTION);
    }

    private void validateDuplicateMemberByEmail(String email) {
        List<Member> findMembersByEmail = memberRepository.findByEmail(email);
        if (!findMembersByEmail.isEmpty()) {
            throw new ApiException(ExceptionEnum.DUPLICATE_USER_EMAIL_EXCEPTION);
        }
    }

    private void validateDuplicateMemberByName(String name) {
        List<Member> findMembersByName = memberRepository.findByName(name);
        if (!findMembersByName.isEmpty()) {
            throw new ApiException(ExceptionEnum.DUPLICATE_USER_NAME_EXCEPTION);
        }
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
}

