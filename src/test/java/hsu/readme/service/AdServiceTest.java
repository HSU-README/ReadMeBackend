package hsu.readme.service;

import hsu.readme.domain.Adv;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class AdServiceTest {

    @Autowired
    AdService adService;

    @Test
    public void 광고삽입() throws Exception{
        //given
        Adv ad = new Adv();
        ad.setImgUrl("test");

        //when
        adService.join(ad);

        //then

    }
}