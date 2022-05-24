package hsu.readme.api.document;

import com.fasterxml.jackson.annotation.JsonProperty;
import hsu.readme.api.Tag.DocumentTagDto;
import lombok.Data;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class StoreDocRequest {
    private Long docId;
    private Long memberId;
    private String title;
    private String docUrl;
    private String visibility;
    private String major;
    private List<String> tags;

    @JsonProperty("components")
    private List<DocComponentDto> docComponentDtos;

    public StoreDocRequest() {}

    public StoreDocRequest(Long docId, Long memberId, String title, String docUrl, String visibility, String major, List<String> tags, List<DocComponentDto> docComponentDtos) {
        this.docId = docId;
        this.memberId = memberId;
        this.title = title;
        this.docUrl = docUrl;
        this.visibility = visibility;
        this.major = major;
        this.tags = tags;
        this.docComponentDtos = docComponentDtos;
    }
}