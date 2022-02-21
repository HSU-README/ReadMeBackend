package hsu.readme.api.member.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class CreateMemberRequest {
    @NotEmpty
    private String email;
    @NotEmpty
    private String password;
    @NotEmpty
    private String name;
}
