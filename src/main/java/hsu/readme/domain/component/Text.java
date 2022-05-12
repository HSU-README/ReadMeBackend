package hsu.readme.domain.component;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Lob;

@Entity
@Getter
@Setter
@DiscriminatorValue("text")
public class Text extends Component{
    @Lob
    private String textContent;
}
