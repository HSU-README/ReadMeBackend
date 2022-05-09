package hsu.readme.api.document;

import lombok.Data;

@Data
public class CreateDocResponse {

    private Long documentId;

    public CreateDocResponse(Long documentId) {
        this.documentId = documentId;
    }
}
