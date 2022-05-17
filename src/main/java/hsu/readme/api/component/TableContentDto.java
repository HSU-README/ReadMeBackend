package hsu.readme.api.component;

import hsu.readme.domain.component.table.TableContent;
import lombok.Getter;

@Getter
public class TableContentDto {
    private int row;
    private int column;
    private String content;

    public TableContentDto() { }

    public TableContentDto(TableContent tableContent) {
        this.row = tableContent.getTrow();
        this.column = tableContent.getTcolumn();
        this.content = tableContent.getContent();
    }
}