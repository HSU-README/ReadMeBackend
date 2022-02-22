package hsu.readme.api.member;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;


@Data
public class CreateMemberResult {
    private Long id;

    public CreateMemberResult(Long id) {
        this.id = id;
    }
}
