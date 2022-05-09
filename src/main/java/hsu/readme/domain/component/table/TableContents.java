package hsu.readme.domain.component.table;

import hsu.readme.domain.component.Component;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class TableContents { // table 내용 입력 시 수정해야할 수 있음.

    @Id @GeneratedValue
    @Column(name = "table_contents_id")
    private Long id;

    private String contents;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "component_id")
    private Component component;
}
