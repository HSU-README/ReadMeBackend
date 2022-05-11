package hsu.readme.api.document;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;

import java.util.List;

@Getter
public class StoreDocRequest {

    private Long docId;
    private Long memberId;
    @JsonProperty("components")
    private List<DocComponentDto> docComponentDtos;

    public StoreDocRequest() {}
    public StoreDocRequest(Long docId, Long memberId, List<DocComponentDto> docComponentDtos) {
        this.docId = docId;
        this.memberId = memberId;
        this.docComponentDtos = docComponentDtos;
    }
}
