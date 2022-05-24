package hsu.readme.api.home;

import lombok.Getter;

@Getter
public class HomeMajorRequest {
    private Long memberId;

    public HomeMajorRequest() { }

    public HomeMajorRequest(Long memberId) {
        this.memberId = memberId;
    }
}
