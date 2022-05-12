package hsu.readme.domain.component.table;

import hsu.readme.domain.component.Component;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Lob;

@Entity
@Getter @Setter
@DiscriminatorValue("table")
public class Table extends Component {
    private int tableCol;
    private int tableRow;

    @Lob
    private String tableContent;
}
