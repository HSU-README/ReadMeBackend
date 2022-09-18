package hsu.readme.api.recruit_post;

import lombok.Data;

@Data
public class CreateRecruitPostResult {
    private Long id;

    public CreateRecruitPostResult(Long id) {
        this.id = id;
    }
}
