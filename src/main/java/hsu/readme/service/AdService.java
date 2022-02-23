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

    //광고 조회
    private List<Adv> findAds(){
        return adRepository.findAll();
    }

    private Adv findOne(Long advId){
        return adRepository.findOne(advId);
    }
}
