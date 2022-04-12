package hsu.readme.api.home;

import com.fasterxml.jackson.annotation.JsonProperty;
import hsu.readme.domain.Document;
import hsu.readme.domain.DocumentStatus;
import hsu.readme.domain.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class HomeInfoDocDto {
    private Long id;
    private String title;
    private int likeCnt;
    @JsonProperty("status")
    private DocumentStatus documentStatus;
    private List<DocumentTagDto> tags;

    public HomeInfoDocDto(Document document){
        this.id = document.getId();
        this.title = document.getTitle();
        this.likeCnt = document.getLikeCnt();
        this.documentStatus = document.getStatus();
        this.tags = document.getTags().stream()
                .map(DocumentTagDto::new)
                .collect(Collectors.toList());
    }

    @Data
    static class DocumentTagDto{
        private String name;

        DocumentTagDto(Tag tag){
            this.name = tag.getName();
        }
    }
}

