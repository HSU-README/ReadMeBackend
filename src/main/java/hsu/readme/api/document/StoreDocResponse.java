package hsu.readme.api.document;

import lombok.Data;
import lombok.Getter;

@Getter
public class StoreDocResponse {
    private Long docId;

    public StoreDocResponse(Long docId) {
        this.docId = docId;
    }
}
