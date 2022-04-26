package hsu.readme.api.member;

import lombok.Data;


@Data
public class LoginMemberResult {
    private Long id;
    private String name;

    public LoginMemberResult(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
