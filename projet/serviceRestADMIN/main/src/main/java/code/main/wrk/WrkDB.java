package code.main.wrk;

import code.main.beans.Entreprise;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
@Transactional
public class WrkDB {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Entreprise> getAllEntreprises() {
        return entityManager.createQuery("SELECT e FROM Entreprise e", Entreprise.class).getResultList();
    }
}
