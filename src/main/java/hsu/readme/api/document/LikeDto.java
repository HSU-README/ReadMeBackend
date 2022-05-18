package hsu.readme.api.document;

import hsu.readme.domain.Like;
import lombok.Getter;

@Getter
public class LikeDto {
    private Long id;
    private Long memberId;
    private Long docId;

    public LikeDto() { }

    public LikeDto(Like like) {
        this.id = like.getId();
        this.memberId = like.getMember().getId();
        this.docId = like.getDocument().getId();
    }
}
