package hsu.readme.domain.component;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Getter
@Setter
@DiscriminatorColumn(name = "ctype")
public class Component {

    @Id
    @GeneratedValue
    @Column(name = "component_id")
    private Long id;

    private String type;
    private int height;
    private int width;
    private float x;
    private float y;
    private int zIndex;
}
