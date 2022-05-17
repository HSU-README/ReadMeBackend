package hsu.readme.api.document;

import com.fasterxml.jackson.annotation.JsonProperty;
import hsu.readme.api.component.TableContentDto;
import hsu.readme.domain.DocComponent;
import hsu.readme.domain.component.Icon;
import hsu.readme.domain.component.Image;
import hsu.readme.domain.component.Text;
import hsu.readme.domain.component.table.Table;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class DocComponentDto {
    private String type;
    private float x;
    private float y;
    private int zIndex;
    private int height;
    private int width;
    private String imgUrl;
    private String iconUrl;
    private String textContent;
    private int tableCol;
    private int tableRow;
//    private String tableContent;
    @JsonProperty("tableContents")
    private List<TableContentDto> tableContentDtos;

    public DocComponentDto() {}
    public DocComponentDto(DocComponent docComponent) {
        this.type = docComponent.getComponent().getType();
        this.height = docComponent.getComponent().getHeight();
        this.width = docComponent.getComponent().getWidth();
        this.x = docComponent.getComponent().getX();
        this.y = docComponent.getComponent().getY();
        this.zIndex = docComponent.getComponent().getZIndex();
        if(docComponent.getComponent() instanceof Text) {
            this.textContent = ((Text)docComponent.getComponent()).getTextContent();
        } else if (docComponent.getComponent() instanceof Image) {
            this.imgUrl = ((Image)docComponent.getComponent()).getImgUrl();
        } else if (docComponent.getComponent() instanceof Icon) {
            this.iconUrl = ((Icon)docComponent.getComponent()).getIconUrl();
        } else if (docComponent.getComponent() instanceof Table) {
            this.tableCol = ((Table)docComponent.getComponent()).getTableCol();
            this.tableRow = ((Table)docComponent.getComponent()).getTableRow();
//            this.tableContent = ((Table)docComponent.getComponent()).getTableContent();
            /*this.docComponentDtos = document.getDocComponents().stream()
                    .map(DocComponentDto::new)
                    .collect(Collectors.toList());*/
            this.tableContentDtos = ((Table)docComponent.getComponent()).getTableContents().stream()
                    .map(TableContentDto::new)
                    .collect(Collectors.toList());
        }
    }


}
