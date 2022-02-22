package hsu.readme.api.member;

import lombok.Data;
import lombok.Getter;

import javax.validation.constraints.NotEmpty;

@Data
public class LoginMemberRequest {
    @NotEmpty
    private String email;
    @NotEmpty
    private String password;
}
