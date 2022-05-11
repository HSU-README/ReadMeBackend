package hsu.readme.api.component;

import hsu.readme.api.Response;
import hsu.readme.api.document.DocComponentDto;
import hsu.readme.api.document.DocInfoDto;
import hsu.readme.api.document.StoreDocRequest;
import hsu.readme.api.document.StoreDocResponse;
import hsu.readme.domain.DocComponent;
import hsu.readme.domain.Document;
import hsu.readme.domain.Member;
import hsu.readme.domain.component.Component;
import hsu.readme.domain.component.Icon;
import hsu.readme.domain.component.Image;
import hsu.readme.domain.component.Text;
import hsu.readme.domain.component.table.Table;
import hsu.readme.service.ComponentService;
import hsu.readme.service.DocumentService;
import hsu.readme.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import java.util.ArrayList;
import java.util.List;

import static hsu.readme.api.ResponseMessage.DOC_CREATE_SUCCESS;
import static hsu.readme.api.ResponseMessage.DOC_INFO_SUCCESS;

@RestController
@RequiredArgsConstructor
public class ComponentApiController {

    private final MemberService memberService;
    private final DocumentService documentService;
    private final ComponentService componentService;



    @PostMapping("/api/v1/doc/edit/{docId}")
    public Response storeDocComponent(@PathVariable Long docId, @RequestBody @Valid StoreDocRequest request) {

        Document document = documentService.findOne(docId);
        documentService.deleteDocument(document);

        List<Long> componentIds = new ArrayList<>();
        for (DocComponentDto dto : request.getDocComponentDtos()) {
            String type = dto.getType();

            long componentId = -1;
            switch (type) {
                case "text":
                    componentId = setTextComponent(dto);
                    break;
                case "image":
                    componentId = setImageComponent(dto);
                    break;
                case "icon":
                    componentId = setIconComponent(dto);
                    break;
                case "table":
                    componentId = setTableComponent(dto);
                    break;
            }
            componentIds.add(componentId);
        }

        documentService.makeDocument(docId, request.getMemberId(), componentIds);

        return Response.response("S200", DOC_CREATE_SUCCESS, new StoreDocResponse(docId));
    }

    private void setComponent(Component component, DocComponentDto dto) {
        component.setX(dto.getX());
        component.setY(dto.getY());
        component.setZIndex(dto.getZIndex());
        component.setWidth(dto.getWidth());
        component.setHeight(dto.getHeight());
    }

    private Long setTextComponent(DocComponentDto dto) {
        Text text = new Text();
        setComponent(text, dto);
        text.setContents(dto.getContents());
        componentService.saveComponent(text);

        return text.getId();
    }

    private Long setImageComponent(DocComponentDto dto) {
        Image image = new Image();

        setComponent(image, dto);
        image.setImgUrl(dto.getImgUrl());
        componentService.saveComponent(image);
        return image.getId();
    }

    private Long setTableComponent(DocComponentDto dto) {
        Table table = new Table();
        setComponent(table, dto);
        table.setTableCol(dto.getTableCol());
        table.setTableRow(dto.getTableRow());
        //table.setContents(dto.getTableContents());
        componentService.saveComponent(table);
        return table.getId();
    }

    private Long setIconComponent(DocComponentDto dto) {
        Icon icon = new Icon();
        setComponent(icon, dto);
        icon.setIconUrl(dto.getIconUrl());
        componentService.saveComponent(icon);
        return icon.getId();
    }
}
