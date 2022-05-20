package hsu.readme.api.document;

import lombok.Getter;

@Getter
public class LikeRequestDto {

    private Long memberId;

    public LikeRequestDto() { }

    public LikeRequestDto(Long memberId) {
        this.memberId = memberId;
    }
}
