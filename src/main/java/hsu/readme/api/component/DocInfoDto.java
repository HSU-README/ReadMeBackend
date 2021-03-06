package hsu.readme.api.component;

import com.fasterxml.jackson.annotation.JsonProperty;
import hsu.readme.api.Tag.DocumentTagDto;
import hsu.readme.api.document.DocComponentDto;
import hsu.readme.api.document.LikeDto;
import hsu.readme.domain.*;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class DocInfoDto {
    private Long docId;
    private String title;
    private LocalDateTime docDate;
    private DocumentVisibility visibility;
    private List<DocumentTagDto> tags;
    private List<LikeDto> likes;
    private int likeCnt;
    private String docUrl;
    private String designer;
    private String designerUrl;
    @JsonProperty("components")
    private List<DocComponentDto> docComponentDtos;

    public DocInfoDto(Document document) {
        this.docId = document.getId();
        this.title = document.getTitle();
        this.docDate = document.getDocumentDate();
        this.visibility = document.getVisibility();
        this.likes = document.getLikes().stream().map(LikeDto::new).collect(Collectors.toList());
        this.likeCnt = likes.size();
        this.tags = document.getTags().stream().map(DocumentTagDto::new).collect(Collectors.toList());
        this.docUrl = document.getDocUrl();
        this.designer = document.getMember().getName();
        this.designerUrl = document.getMember().getProfileUrl();
        this.docComponentDtos = document.getDocComponents().stream()
                .map(DocComponentDto::new)
                .collect(Collectors.toList());
    }
}
