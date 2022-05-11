package hsu.readme.api.document;

import hsu.readme.domain.component.table.TableContents;
import lombok.Data;
import lombok.Getter;

import java.util.List;

@Getter
public class DocComponentDto {
    private Long componentId;
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
    private List<String> tableContents;

    public DocComponentDto(Long componentId, String type, int height, int width, float x, float y, int zIndex, String imgUrl, String iconUrl, String contents, int tableCol, int tableRow, List<String> tableContents) {
        this.componentId = componentId;
        this.type = type;
        this.height = height;
        this.width = width;
        this.x = x;
        this.y = y;
        this.zIndex = zIndex;
        this.imgUrl = imgUrl;
        this.iconUrl = iconUrl;
        this.contents = contents;
        this.tableCol = tableCol;
        this.tableRow = tableRow;
        this.tableContents = tableContents;
    }
}
