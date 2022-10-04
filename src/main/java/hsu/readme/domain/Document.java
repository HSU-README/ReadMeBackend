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

    @OneToMany(mappedBy = "document", cascade = CascadeType.ALL)
    private List<DocComponent> docComponents = new ArrayList<>();

    private String title;

    @OneToMany(mappedBy = "document", cascade = CascadeType.ALL)
    private List<Like> likes;

    private int likeCnt;

    private LocalDateTime documentDate;

    @Lob
    @Column(length = 4000)
    private String docUrl;

    private String docMajor;

    @OneToMany(mappedBy = "document", cascade = CascadeType.ALL)
    private List<Tag> tags = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private DocumentVisibility visibility;

    @Enumerated(EnumType.STRING)
    private DocumentStatus status;

    // == 연관관계 메서드 == //
    public void setMember(Member member, Long docId) {
        this.member = member;
        member.getDocuments().add(this);
    }

    public void addDocComponent(DocComponent docComponent) {
        docComponents.add(docComponent);
        docComponent.setDocument(this);
    }

    public void addTag(Tag tag) {
        tags.add(tag);
        tag.setDocument(this);
    }

    public void removeMember() {
        this.member.getDocuments().remove(this);
    }

    // == 생성 메서드 ==//
    public static Document createDocument(Member member, String title, String docUrl, String visibility, String major,
            List<Tag> tags, List<DocComponent> docComponents) {
        Document document = new Document();
        document.setMember(member);
        document.setTitle(title);
        document.setDocUrl(docUrl);
        document.setDocMajor(major);

        for (Tag tag : tags) {
            document.addTag(tag);
        }
        for (DocComponent docComp : docComponents) {
            document.addDocComponent(docComp);
        }

        if (visibility.equals("public")) {
            document.setVisibility(DocumentVisibility.PUBLIC);
        } else {
            document.setVisibility(DocumentVisibility.PRIVATE);
        }
        document.setDocumentDate(LocalDateTime.now());
        return document;
    }
}
