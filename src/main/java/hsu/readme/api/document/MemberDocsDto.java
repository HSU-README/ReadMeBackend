package hsu.readme.api.document;

import hsu.readme.domain.Document;
import lombok.Getter;

@Getter
public class MemberDocsDto {
    private Long docId;
    private String title;

    public MemberDocsDto(Document document) {
        this.docId = document.getId();
        this.title = document.getTitle();
    }
}
