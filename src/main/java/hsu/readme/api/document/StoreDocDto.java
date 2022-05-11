package hsu.readme.api.document;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class StoreDocDto {
    private float x;
    private float y;
    private int zIndex;
    private float width;
    private float height;
    private String imgUrl;
    private int tableRow;
    private int tableCol;
    private String textContent;
    private String tableContent;

    public StoreDocDto(float x, float y, int zIndex, float width, float height, String imgUrl, int tableRow, int tableCol, String textContent, String tableContent) {
        this.x = x;
        this.y = y;
        this.zIndex = zIndex;
        this.width = width;
        this.height = height;
        this.imgUrl = imgUrl;
        this.tableRow = tableRow;
        this.tableCol = tableCol;
        this.textContent = textContent;
        this.tableContent = tableContent;
    }
}
