package hsu.readme.api.member;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class CreateMemberRequest {
    @NotEmpty
    private String email;
    @NotEmpty
    private String password;
    @NotEmpty
    private String name;
    @NotNull
    private String university;
    @NotNull
    private String major;
}
