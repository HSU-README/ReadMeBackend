package hsu.readme.service;

import hsu.readme.Repository.AdRepository;
import hsu.readme.domain.Adv;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AdService {

    private final AdRepository adRepository;

    @Transactional
    public Long join(Adv ad){
        adRepository.save(ad);
        return ad.getId();
    }

    //광고 조회
    public List<Adv> findAds(){
        return adRepository.findAll();
    }

    public Adv findOne(Long advId){
        return adRepository.findOne(advId);
    }
}
