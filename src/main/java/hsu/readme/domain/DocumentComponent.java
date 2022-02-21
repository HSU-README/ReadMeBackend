package hsu.readme.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@Setter
public class DocumentComponent {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "item_id")
    private Component component;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "document_id")
    private Document document;

    private int count;
}