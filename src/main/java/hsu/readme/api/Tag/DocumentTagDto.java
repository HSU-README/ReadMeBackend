package hsu.readme.api.Tag;

import hsu.readme.domain.Tag;
import lombok.Getter;

@Getter
public class DocumentTagDto {
    private String name;

    public DocumentTagDto(Tag tag){
        this.name = tag.getName();
    }
}
