package hsu.readme.domain.component;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
@DiscriminatorValue("icon")
public class Icon extends Component{
    private String iconUrl;
}