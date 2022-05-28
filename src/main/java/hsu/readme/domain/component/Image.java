package hsu.readme.domain.component;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Lob;

@Entity
@Getter
@Setter
@DiscriminatorValue("image")
public class Image extends Component{

    @Lob
    @Column(length = 3000)
    private String imgUrl;
}
