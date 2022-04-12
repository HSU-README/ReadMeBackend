package hsu.readme.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter @Setter
public class Adv {

    @Id @GeneratedValue
    @Column(name = "ad_id")
    private Long id;

    private String imgUrl;
}
