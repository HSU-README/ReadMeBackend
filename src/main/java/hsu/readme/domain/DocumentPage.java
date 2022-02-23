package hsu.readme.domain;

import hsu.readme.domain.component.Component;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter @Setter
public class DocumentPage {

    @Id @GeneratedValue
    @Column(name = "document_view_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "document_id")
    private Document document;

    @OneToMany(mappedBy = "documentPage")
    private List<PageComponent> pageComponents = new ArrayList<>();

    //순서
    private int pageOrder;

    //페이지 생성시간
    private LocalDateTime pageDate;
}
