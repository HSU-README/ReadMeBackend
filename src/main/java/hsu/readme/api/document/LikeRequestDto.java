package hsu.readme.api.document;

import lombok.Getter;

@Getter
public class LikeRequestDto {

    private Long docId;
    private Long memberId;

    public LikeRequestDto() { }

    public LikeRequestDto(Long docId, Long memberId) {
        this.docId = docId;
        this.memberId = memberId;
    }
}
