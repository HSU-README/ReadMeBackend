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

    @Enumerated(EnumType.STRING)
    private University university;

    @Enumerated(EnumType.STRING)
    private Major major;

    @Enumerated(EnumType.STRING)
    private Interest interest;

    @OneToMany(mappedBy = "member")
    private List<Document> documents = new ArrayList<>();
}
