package hsu.readme.service.MemberServiceValidation;

import hsu.readme.domain.Member;
import hsu.readme.exception.ApiException;
import hsu.readme.exception.ExceptionEnum;
import org.springframework.util.StringUtils;

import java.util.List;

public class MemberValidation {
    //SIGN UP
    public static void emptyCheckMemberUniversity(String university) {
        if (university.length() == 0) throw new ApiException(ExceptionEnum.EMPTY_USER_UNIVERSITY_EXCEPTION);
    }

    public static void emptyCheckMemberMajor(String major) {
        if (major.length() == 0) throw new ApiException(ExceptionEnum.EMPTY_USER_MAJOR_EXCEPTION);
        if (StringUtils.isEmpty(major)) throw new ApiException(ExceptionEnum.EMPTY_USER_MAJOR_EXCEPTION);
    }

    public static void validateDuplicateMemberByEmail(List<Member> findMembersByEmail) {
        if (!findMembersByEmail.isEmpty()) {
            throw new ApiException(ExceptionEnum.DUPLICATE_USER_EMAIL_EXCEPTION);
        }
    }

    public static void validateDuplicateMemberByName(List<Member> findMembersByName) {
        if (!findMembersByName.isEmpty()) {
            throw new ApiException(ExceptionEnum.DUPLICATE_USER_NAME_EXCEPTION);
        }
    }
}
