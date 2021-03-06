package hsu.readme.domain.component.table;

import hsu.readme.domain.component.Component;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@DiscriminatorValue("table")
public class Table extends Component {
    private int tableCol;
    private int tableRow;

//    @Lob
//    private String tableContent;

    @OneToMany(mappedBy = "component", cascade = CascadeType.ALL)
    private List<TableContent> tableContents = new ArrayList<>();
}
