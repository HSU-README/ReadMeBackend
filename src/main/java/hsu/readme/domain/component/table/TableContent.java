package hsu.readme.domain.component.table;

import hsu.readme.api.component.TableContentDto;
import hsu.readme.domain.component.Component;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter @Setter
public class TableContent {
    @Id
    @GeneratedValue
    @Column(name = "table_content_id")
    private Long id;

    private int trow;
    private int tcolumn;

    @Lob
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "component_id")
    private Component component;

    public void setComponent(Component component) {
        this.component = component;
        ((Table)component).getTableContents().add(this);
    }

    public static TableContent createTableContent(Component component, TableContentDto tcDto) {
        TableContent tableContent = new TableContent();
        tableContent.setTrow(tcDto.getRow());
        tableContent.setTcolumn(tcDto.getColumn());
        tableContent.setContent(tcDto.getContent());
        tableContent.setComponent(component);
        return tableContent;
    }
}
