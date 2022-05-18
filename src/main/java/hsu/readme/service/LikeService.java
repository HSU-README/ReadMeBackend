package hsu.readme.service;

import hsu.readme.Repository.LikeRepository;
import hsu.readme.domain.Document;
import hsu.readme.domain.Like;
import hsu.readme.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LikeService {

    private final LikeRepository likeRepository;

    @Transactional
    public Long join(Like like) {
        likeRepository.save(like);
        return like.getId();
    }

    @Transactional
    public Long delete(Like like) {
        likeRepository.remove(like);
        return like.getId();
    }

    public List<Like> findLikes() {
        return likeRepository.findAll();
    }

    public Like findOne(Long likeId) {
        return likeRepository.findOne(likeId);
    }

    public Like findWithDocAndMember(Member member, Document document) {
        return likeRepository.findLikeInDocWithMember(member.getId(), document.getId());
    }

    public List<Like> findWithDoc(Document document){
        return likeRepository.findLikeWithDoc(document.getId());
    }

    @Transactional
    public Long createLike(Member member, Document document){
        Like like = Like.createLike(member, document);
        likeRepository.save(like);
        return like.getId();
    }
}
