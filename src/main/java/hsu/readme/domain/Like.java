package hsu.readme.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@Setter
@Table(name = "likes")
public class Like {

    @Id
    @GeneratedValue
    @Column(name = "like_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "document_id")
    private Document document;
//
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private void setMember(Member member) {
        this.member = member;
        member.getLikes().add(this);
    }

    private void setDocument(Document document) {
        this.document = document;
        document.getLikes().add(this);
    }

    public static Like createLike(Member member, Document document) {
        Like like = new Like();
        like.setMember(member);
        like.setDocument(document);
        return like;
    }
}
