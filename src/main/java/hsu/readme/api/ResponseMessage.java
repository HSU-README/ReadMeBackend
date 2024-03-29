package hsu.readme.api;

public class ResponseMessage {
    //MemberApiController
    public static final String LOGIN_SUCCESS = "로그인 성공";
    public static final String CREATED_USER = "회원 가입 성공";
    public static final String MEMBER_INFO_SUCCESS = "회원 정보 조회 성공";
    public static final String PUT_MEMBER_INFO_SUCCESS = "회원 정보 수정 성공";

    public static final String READ_USER = "회원 정보 조회 성공";
    public static final String NOT_FOUND_USER = "회원을 찾을 수 없습니다.";

    public static final String UPDATE_USER = "회원 정보 수정 성공";
    public static final String DELETE_USER = "회원 탈퇴 성공";
    public static final String INTERNAL_SERVER_ERROR = "서버 내부 에러";
    public static final String DB_ERROR = "데이터베이스 에러";

    //HomeApiController
    public static final String HOME_INFO_SUCCESS = "홈화면 조회 성공";

    //DocApiController
    public static final String DOC_INFO_SUCCESS = "문서 정보 조회 성공";
    public static final String DOC_CREATE_SUCCESS = "문서 생성 성공";
    public static final String DOC_EDIT_SUCCESS = "문서 수정 성공";
    public static final String DOC_DELETE_SUCCESS = "문서 삭제 성공";
    public static final String DOC_SEARCH_SUCCESS = "문서 탐색 성공";
    public static final String DOC_LIKE_SUCCESS = "문서 좋아요 성공";

    public static final String DOC_LIKE_EXISTED = "문서 좋아요 이미 존재";

    public static final String DOC_UNLIKE_SUCCESS = "문서 좋아요 취소 성공";
    public static final String DOC_LIKE_NOT_EXIST = "문서 좋아요가 존재하지 않습니다.";

    public static final String RECRUIT_POST_SUCCESS = "채용 공고 작성 성공";
    public static final String RECRUIT_POST_FIND_SUCCESS = "채용 전체 공고 호출 성공";

}
