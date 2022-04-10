package hsu.readme.api.member;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
class GetMemberResult {
    private String name;
    private String profileUrl;
    private String university;
    private String major;
    private String interests;
}
