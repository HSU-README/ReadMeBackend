package hsu.readme.domain;

import hsu.readme.domain.component.Component;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter @Setter
public class PageComponent {
    @Id @GeneratedValue
    @Column(name = "page_component_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "component_id")
    private Component component;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "document_page_id")
    private DocumentPage documentPage;

    private int count;
}
