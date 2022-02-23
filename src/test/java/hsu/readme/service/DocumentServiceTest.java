package hsu.readme.service;

import hsu.readme.domain.Document;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class DocumentServiceTest {

    @Autowired
    DocumentService documentService;

    @Test
    @Rollback(value = false)
    public void 문서작성() throws Exception{
        //given
        Document document = new Document();
        document.setTitle("hello");
//        document.setLikeCnt(2);

        //when
        documentService.join(document);

        //then

    }
}