package hsu.readme.api.recruit_post;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class CreateRecruitPostRequest {
    @NotEmpty
    private String companyName;
    @NotEmpty
    private String content;
    @NotEmpty
    private String skillStack;
    @NotNull
    private String jobOpening;
    @NotNull
    private String region;
    @NotEmpty
    private String division;
    @NotEmpty
    private String applyLink;
    @NotEmpty
    private String salary;
    @NotEmpty
    private String firebaseUrl;
}
