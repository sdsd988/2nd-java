package test.apply.domain.lecture;

import lombok.Getter;
import lombok.Setter;
import test.apply.domain.Application;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Lecture {
    @Id
    @GeneratedValue
    @Column(name = "lecture_id")
    private Long id;

    private String lectureName;

    private LocalDate RecruitmentStart;

    private LocalDate RecruitmentEnd;

    private LocalDate LectureStart;

    private LocalDate LectureEnd;

    @OneToMany(mappedBy = "lecture")
    private List<Application> applications = new ArrayList<>();

    private String description;
}
