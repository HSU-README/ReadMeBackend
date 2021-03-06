package hsu.readme.domain;

import hsu.readme.domain.component.Component;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.print.Doc;

import static javax.persistence.FetchType.*;

@Entity
@Getter @Setter
public class DocComponent {

    @Id @GeneratedValue
    @Column(name = "doc_component_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "document_id")
    private Document document;

    @ManyToOne(fetch = LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "component_id")
    private Component component;

    //==생성 메서드==//
    public static DocComponent createDocComponent(Component component) {
        DocComponent docComponent = new DocComponent();
        docComponent.setComponent(component);

        return docComponent;
    }
}
