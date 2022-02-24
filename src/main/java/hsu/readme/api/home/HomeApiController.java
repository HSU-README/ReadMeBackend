package hsu.readme.api.home;

import hsu.readme.api.Response;
import hsu.readme.domain.Adv;
import hsu.readme.domain.Document;
import hsu.readme.domain.DocumentStatus;
import hsu.readme.service.AdService;
import hsu.readme.service.DocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static hsu.readme.api.ResponseMessage.HOME_INFO_SUCCESS;

@RestController
@RequiredArgsConstructor
public class HomeApiController {

    private final DocumentService documentService;
    private final AdService adService;

    @GetMapping("/api/v1/home")
    @ResponseBody
    public Response homeInfoV1(){
        makeDocuments();
        makeAds();

        List<Adv> ads = adService.findAds();
        List<Document> findDocuments = documentService.findTopDocuments(5);
        List<HomeInfoDocDto> topDocuments = findDocuments.stream()
                .map(d -> new HomeInfoDocDto(d.getId(), d.getTitle(), d.getLikeCnt(), d.getStatus()))
                .collect(Collectors.toList());
        return Response.response("S200", HOME_INFO_SUCCESS, new HomeInfoResult(ads, topDocuments));
    }

    private void makeDocuments() {
        for(int i=0; i<2; i++) {
            Document document = new Document();
            document.setTitle("test"+i);
            document.setLikeCnt(i);
            if (i % 2 == 0) {
                document.setStatus(DocumentStatus.WRITE);
            } else {
                document.setStatus(DocumentStatus.FINISH);
            }
            documentService.join(document);
        }
    }

    private void makeAds() {
        for(int i=0; i<5; i++) {
            Adv ad = new Adv();
            ad.setImgUrl("test"+i);
            adService.join(ad);
        }
    }
}
