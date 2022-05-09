package hsu.readme.domain.component;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
@DiscriminatorValue("CImage")
public class Image extends Component{
    private String imgUrl;
}
