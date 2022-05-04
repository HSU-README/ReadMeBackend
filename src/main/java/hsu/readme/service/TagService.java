package hsu.readme.service;

import hsu.readme.Repository.TagRepository;
import hsu.readme.domain.Tag;
import hsu.readme.exception.ApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TagService {

    private final TagRepository tagRepository;

    /**
    *  태그 입력
    */

    @Transactional
    public Long join(Tag tag){
        tagRepository.save(tag);
        return tag.getId();
    }

    public Tag findOne(Long tagId){
        Tag tag = tagRepository.findOne(tagId);
        return tag;
    }
}
