package hsu.readme.api.member;

import lombok.Data;


@Data
public class LoginMemberResult {
    private Long id;
    private String name;
    private String major;

    public LoginMemberResult(Long id, String name, String major) {
        this.id = id;
        this.name = name;
        this.major = major;
    }
}
