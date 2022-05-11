package hsu.readme.api.document;

import hsu.readme.api.Tag.DocumentTagDto;
import hsu.readme.domain.Document;
import hsu.readme.domain.Tag;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class DocInfoDto_legacy {
    private Long id;
    private String title;
    private String docUrl;
    private String designer;
    private List<String> tags;

    public DocInfoDto_legacy(Document document){
        this.id = document.getId();
        this.title = document.getTitle();
        this.docUrl = "https://testUrl.com";
        this.designer = document.getMember().getName();
        this.tags = document.getTags().stream()
                .map(Tag::getName)
                .collect(Collectors.toList());
    }
}
