package test.apply.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import test.apply.domain.Application;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ApplicationRepository {

    private final EntityManager em;

    public void save(Application application) {
        em.persist(application);
    }

    public Application findOne(Long id) {
        return em.find(Application.class, id);
    }

    public void deleteApplication(Application application) {
        em.remove(application);
    }

    public List<Application> findAllApplication() {

        return em.createQuery("select a from Application a", Application.class).getResultList();

    }
}
