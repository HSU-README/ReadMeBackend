package hsu.readme.api.document;

import hsu.readme.api.Tag.DocumentTagDto;
import hsu.readme.domain.Document;
import hsu.readme.domain.DocumentVisibility;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class DocPreviewInfoDto {
    private Long docId;
    private String title;
    private LocalDateTime docDate;
    private DocumentVisibility visibility;
    private List<DocumentTagDto> tags;
    private List<LikeDto> likes;
    private int likeCnt;
    private String docUrl;
    private String designer;

    public DocPreviewInfoDto(Document document) {
        this.docId = document.getId();
        this.title = document.getTitle();
        this.docDate = document.getDocumentDate();
        this.visibility = document.getVisibility();
        this.likes = document.getLikes().stream().map(LikeDto::new).collect(Collectors.toList());
        this.likeCnt = document.getLikes().size();
        this.tags = document.getTags().stream().map(DocumentTagDto::new).collect(Collectors.toList());
        this.docUrl = document.getDocUrl();
        this.designer = document.getMember().getName();
    }
}
