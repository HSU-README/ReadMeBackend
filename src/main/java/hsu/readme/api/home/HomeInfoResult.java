package hsu.readme.api.home;

import com.fasterxml.jackson.annotation.JsonProperty;
import hsu.readme.domain.Adv;
import lombok.Getter;

import java.util.List;

@Getter
public class HomeInfoResult {
    private List<Adv> ads;
    private List<HomeInfoDocDto> documents; //임시 5개만

    public HomeInfoResult(List<Adv> ads, List<HomeInfoDocDto> documents) {
        this.ads = ads;
        this.documents = documents;
    }
}



