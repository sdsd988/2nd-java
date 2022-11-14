package test.apply.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import test.apply.domain.lecture.Lecture;

import javax.persistence.EntityManager;


@Repository
@RequiredArgsConstructor
public class LectureRepository {

    private final EntityManager em;

    public void save(Lecture lecture) {em.persist(lecture);}

    public Lecture findOne(Long id){return em.find(Lecture.class,id);}
}
