package hsu.readme.api.member;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;


@Data
public class MemberResult {
    private Long id;

    public MemberResult(Long id) {
        this.id = id;
    }
}
