package hsu.readme.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Member{

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;
    private String email;
    private String password;

    private String profileUrl;

    private String university;
    private String major;

    private String interests;

    @OneToMany(mappedBy = "member")
    private List<Document> documents = new ArrayList<>();
}
