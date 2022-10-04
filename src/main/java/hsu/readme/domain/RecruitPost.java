package hsu.readme.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class RecruitPost {

    @Id
    @GeneratedValue
    @Column(name = "recruit_post_id")
    private Long id;

    private String companyName;
    @Column(length = 4000)
    private String content;

    private String skillStack;

    private String jobOpening;

    private String region;

    private String division;

    private String applyLink;

    private String salary;

    @Column(length = 4000)
    private String firebaseUrl;
}
