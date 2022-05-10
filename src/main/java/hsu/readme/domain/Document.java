package hsu.readme.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@Setter
public class Document {

    @Id
    @GeneratedValue
    @Column(name = "document_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "document")
    private List<DocComponent> docComponents = new ArrayList<>();

    private String title;

    private int likeCnt;

    private LocalDateTime documentDate;

    private String docUrl;

    @OneToMany(mappedBy = "document", cascade = CascadeType.ALL)
    private List<Tag> tags = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private DocumentVisibility visibility;

    @Enumerated(EnumType.STRING)
    private DocumentStatus status;

    //== 연관관계 메서드 == //
    public void setMember(Member member) {
        this.member = member;
        member.getDocuments().add(this);
    }

    public void addDocComponent(DocComponent docComponent) {
        docComponents.add(docComponent);
        docComponent.setDocument(this);
    }

    //== 생성 메서드 ==//
    public static Document createDocument(Member member, DocComponent... docComponents) {
        Document document = new Document();
        document.setMember(member);
        for (DocComponent docComp : docComponents) {
            document.addDocComponent(docComp);
        }
        document.setDocumentDate(LocalDateTime.now());
        return document;
    }

    /*
    //== 저장 메서드 ==//
    public static Document storeDocument(DocComponent... docComponents) {
        for (DocComponent docComp : docComponents) {
            document.addDocComponent(docComp);
        }
    }*/
}
