package hsu.readme.api.home;

import com.fasterxml.jackson.annotation.JsonProperty;
import hsu.readme.domain.DocumentStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class HomeInfoDocDto {
    private Long id;
    private String title;
    private int likeCnt;
    @JsonProperty("status")
    private DocumentStatus documentStatus;
}