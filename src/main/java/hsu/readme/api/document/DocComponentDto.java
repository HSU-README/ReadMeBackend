package hsu.readme.api.document;

import hsu.readme.domain.DocComponent;
import hsu.readme.domain.Document;
import hsu.readme.domain.component.Component;
import hsu.readme.domain.component.Icon;
import hsu.readme.domain.component.Image;
import hsu.readme.domain.component.Text;
import hsu.readme.domain.component.table.Table;
import hsu.readme.domain.component.table.TableContents;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Getter
public class DocComponentDto {
    private String type;
    private int height;
    private int width;
    private float x;
    private float y;
    private int zIndex;
    private String imgUrl;
    private String iconUrl;
    private String contents;
    private int tableCol;
    private int tableRow;
    private List<TableContentsDto> tableContents;

    public DocComponentDto() {}
    public DocComponentDto(DocComponent docComponent) {
        this.type = docComponent.getComponent().getType();
        this.height = docComponent.getComponent().getHeight();
        this.width = docComponent.getComponent().getWidth();
        this.x = docComponent.getComponent().getX();
        this.y = docComponent.getComponent().getY();
        this.zIndex = docComponent.getComponent().getZIndex();
        if(docComponent.getComponent() instanceof Text) {
            this.contents = ((Text)docComponent.getComponent()).getContents();
        } else if (docComponent.getComponent() instanceof Image) {
            this.imgUrl = ((Image)docComponent.getComponent()).getImgUrl();
        } else if (docComponent.getComponent() instanceof Icon) {
            this.iconUrl = ((Icon)docComponent.getComponent()).getIconUrl();
        } else if (docComponent.getComponent() instanceof Table) {
            this.tableCol = ((Table)docComponent.getComponent()).getTableCol();
            this.tableRow = ((Table)docComponent.getComponent()).getTableRow();
            this.tableContents = ((Table)docComponent.getComponent()).getContents()
                    .stream().map(TableContentsDto::new).collect(Collectors.toList());
        }



    }

    @Data
    class TableContentsDto{
        private String contents;

        public TableContentsDto(TableContents contents) {
            this.contents = contents.getContents();
        }
    }
}
