package hsu.readme.api.component;

import hsu.readme.api.Response;
import hsu.readme.api.document.DocComponentDto;
import hsu.readme.api.document.StoreDocRequest;
import hsu.readme.api.document.StoreDocResponse;
import hsu.readme.domain.*;
import hsu.readme.domain.component.Component;
import hsu.readme.domain.component.Icon;
import hsu.readme.domain.component.Image;
import hsu.readme.domain.component.Text;
import hsu.readme.domain.component.table.Table;
import hsu.readme.domain.component.table.TableContent;
import hsu.readme.exception.ApiException;
import hsu.readme.exception.ExceptionEnum;
import hsu.readme.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

import static hsu.readme.api.ResponseMessage.*;

@RestController
@RequiredArgsConstructor
public class ComponentApiController {

    private final MemberService memberService;
    private final DocumentService documentService;
    private final ComponentService componentService;
    private final TableContentService tableContentService;
    private final DocComponentService docComponentService;
    private final LikeService likeService;
    private final TagService tagService;

    @GetMapping("/api/v1/doc/{id}")
    public Response docInfoV1(@PathVariable Long id) {
        Document document = documentService.findDocumentInfo(id);
        DocInfoDto docInfoDto = new DocInfoDto(document);
        return Response.response("S200", DOC_INFO_SUCCESS, docInfoDto);
    }

    @PostMapping("/api/v1/doc/edit")
    public Response storeDocComponent(@RequestBody @Valid StoreDocRequest request) {

        //유저가 존재하는지 찾기.
        try{
            Member findMember = memberService.findOne(request.getMemberId());
        }catch (Exception e) {
            throw new ApiException(ExceptionEnum.USER_NOT_EXIST_EXCEPTION);
        }

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

        List<Long> tagIds = new ArrayList<>();
        for(String name : request.getTags()) {
            Tag tag = new Tag();
            tag.setName(name);
            tagService.join(tag);
            tagIds.add(tag.getId());
        }

        if(request.getDocId() == null) {
            Long docSavedId = documentService.makeDocument(request.getMemberId(), request.getTitle(), request.getDocUrl(), request.getVisibility(),tagIds, componentIds);
            return Response.response("S200", DOC_CREATE_SUCCESS, new StoreDocResponse(docSavedId));
        } else {
            Document document = documentService.findOne(request.getDocId());
            int size = document.getDocComponents().size();
            for(int i=0; i<size; i++) {
                DocComponent docComponent = docComponentService.findOne(document.getDocComponents().get(0).getId());
                document.getDocComponents().remove(docComponent);
                docComponentService.delete(docComponent);
            }

            size = document.getTags().size();
            for(int i=0; i<size; i++) {
                Tag findTag = tagService.findOne(document.getTags().get(0).getId());
                document.getTags().remove(findTag);
                tagService.deleteTag(findTag);
            }

            Long docEditedId = documentService.editDocument(request.getDocId(), request.getTitle(), request.getDocUrl(), request.getVisibility(), tagIds, componentIds);

            return Response.response("S200", DOC_EDIT_SUCCESS, new StoreDocResponse(docEditedId));
        }
        /*try {
            Document document = documentService.findOne(request.getDocId());
            documentService.deleteDocument(document);
        } catch (Exception e){

        }
*/

    }

    private void setComponent(Component component, DocComponentDto dto) {
        component.setType(dto.getType());
        component.setX(dto.getX());
        component.setY(dto.getY());
        component.setZIndex(dto.getZIndex());
        component.setWidth(dto.getWidth());
        component.setHeight(dto.getHeight());
    }

    private Long setTextComponent(DocComponentDto dto) {
        Text text = new Text();
        setComponent(text, dto);
        text.setTextContent(dto.getTextContent());
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
        componentService.saveComponent(table);
        Component findTable = componentService.findOne(table.getId());

        for(TableContentDto tcDto : dto.getTableContentDtos()){
            TableContent tableContent = TableContent.createTableContent(findTable, tcDto);
            tableContentService.saveTableContent(tableContent);
        }

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
