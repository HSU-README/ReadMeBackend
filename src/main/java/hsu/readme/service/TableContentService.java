package hsu.readme.service;

import hsu.readme.Repository.TableContentRepository;
import hsu.readme.domain.component.table.TableContent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TableContentService {

    private final TableContentRepository tableContentRepository;

    @Transactional
    public void saveTableContent(TableContent tableContent) {
        tableContentRepository.save(tableContent);
    }

    public List<TableContent> findTableContents() { return tableContentRepository.findAll(); }

    public TableContent findOne(Long tableContentId) {return tableContentRepository.findOne(tableContentId); }
}
