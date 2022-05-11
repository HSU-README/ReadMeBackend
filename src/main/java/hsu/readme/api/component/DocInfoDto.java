package hsu.readme.api.component;

import hsu.readme.api.document.DocComponentDto;
import hsu.readme.domain.DocComponent;
import hsu.readme.domain.Document;
import hsu.readme.domain.DocumentStatus;
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
    private DocumentStatus docStatus;
    private int likeCnt;
    private String docUrl;
    private String designer;
    private List<DocComponentDto> docComponentDtos;

    public DocInfoDto(Document document) {
        this.docId = document.getId();
        this.title = document.getTitle();
        this.docDate = document.getDocumentDate();
        this.docStatus = document.getStatus();
        this.likeCnt = document.getLikeCnt();
        this.docUrl = document.getDocUrl();
        this.designer = document.getMember().getName();
        this.docComponentDtos = document.getDocComponents().stream()
                .map(DocComponentDto::new)
                .collect(Collectors.toList());
    }
}
