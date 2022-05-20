package hsu.readme.service;

import hsu.readme.Repository.DocComponentRepository;
import hsu.readme.domain.DocComponent;
import hsu.readme.domain.Like;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DocComponentService {

    private final DocComponentRepository docComponentRepository;

    public DocComponent findOne(Long id) {
        return docComponentRepository.findOne(id);
    }

    @Transactional
    public Long delete(DocComponent docComponent) {
        docComponentRepository.remove(docComponent);
        return docComponent.getId();
    }
}
