package hsu.readme.service;

import hsu.readme.Repository.MemberRepository;
import hsu.readme.Repository.RecruitPostRepository;
import hsu.readme.domain.Document;
import hsu.readme.domain.Member;
import hsu.readme.domain.RecruitPost;
import hsu.readme.exception.ApiException;
import hsu.readme.exception.ExceptionEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RecruitPostService {

    private final RecruitPostRepository recruitPostRepository;

    @Transactional
    public Long join(RecruitPost recruitPost){
        recruitPostRepository.save(recruitPost);
        return recruitPost.getId();
    }

    // 채용공고 조회
    public RecruitPost findOne(Long memberId){
        RecruitPost recruitPost = recruitPostRepository.findOne(memberId);
        if(recruitPost == null) throw new ApiException(ExceptionEnum.RECRUIT_NOT_EXISTED);
        return recruitPost;
    }

    //채용공고 전체 조회
    public List<RecruitPost> findRecruitPosts(){
        return recruitPostRepository.findAllSortedByPostDate();
    }

    public List<RecruitPost> findRecruitPostsByJob(String jobOpening) {
        return recruitPostRepository.findAllSortedByJob(jobOpening);
    }
}
