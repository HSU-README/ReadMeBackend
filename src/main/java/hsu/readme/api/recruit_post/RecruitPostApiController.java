package hsu.readme.api.recruit_post;

import hsu.readme.api.Response;
import hsu.readme.api.document.DocPreviewInfoDto;
import hsu.readme.api.member.*;
import hsu.readme.domain.Document;
import hsu.readme.domain.Member;
import hsu.readme.domain.RecruitPost;
import hsu.readme.service.DocumentService;
import hsu.readme.service.MemberService;
import hsu.readme.service.RecruitPostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static hsu.readme.api.ResponseMessage.*;

@RestController
@RequiredArgsConstructor
public class RecruitPostApiController {

    private final RecruitPostService recruitPostService;

    //채용공고 작성
    @PostMapping("/api/v1/recruit_post/new")
    @ResponseBody
    public Response saveRecruitPostV1(@RequestBody @Valid CreateRecruitPostRequest request){
        RecruitPost recruitPost = new RecruitPost();
        recruitPost.setCompanyName(request.getCompanyName());
        recruitPost.setContent(request.getContent());
        recruitPost.setApplyLink(request.getApplyLink());
        recruitPost.setDivision(request.getDivision());
        recruitPost.setRegion(request.getRegion());
        recruitPost.setJobOpening(request.getJobOpening());
        recruitPost.setSalary(request.getSalary());
        recruitPost.setSkillStack(request.getSkillStack());

        Long savedId = recruitPostService.join(recruitPost);
        return Response.response("S200", RECRUIT_POST_SUCCESS, new MemberResult(savedId));
    }

    //유저의 문서들 불러오기
    @GetMapping("/api/v1/recruit_posts")
    public Response findRecruitPostsV1() {
        List<RecruitPost> recruitPosts = recruitPostService.findRecruitPosts();
        return Response.response("S200", RECRUIT_POST_FIND_SUCCESS, recruitPosts);
    }

    //유저의 문서들 불러오기
    @GetMapping("/api/v1/recruit_posts/{jobOpening}")
    public Response findRecruitPostsV1(@PathVariable String jobOpening) {
        List<RecruitPost> recruitPosts = recruitPostService.findRecruitPostsByJob(jobOpening);
        return Response.response("S200", RECRUIT_POST_FIND_SUCCESS, recruitPosts);
    }

    //문서 삭제
    @PostMapping("/api/v1/doc/delete/{recruitPostId}")
    public Response deleteRecruitPost(@PathVariable Long recruitPostId) {
        RecruitPost recruitPost = recruitPostService.findOne(recruitPostId);
        recruitPostService.deleteRecruitPost(recruitPost);
        return Response.response("S200", DOC_DELETE_SUCCESS, recruitPostId);
    }
}