package hsu.readme.api.member;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class PutMemberRequest {
    @NotEmpty
    private String name;
    private String profileUrl;
    private String university;
    private String major;
    private String interests;
    private String memberType;
}
