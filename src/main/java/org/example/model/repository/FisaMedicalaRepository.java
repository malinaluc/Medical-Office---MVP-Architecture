package org.example.model.repository;

import org.example.model.entity.FisaMedicala;
import org.example.model.entity.Medic;
import org.example.model.entity.User;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.util.List;

import static org.example.utils.ExtensionFunctions.logDebug;


//operatiile CRUD
public class FisaMedicalaRepository extends AbstractRepository<FisaMedicala> {

    public List<FisaMedicala> allFisaMedicalaByMedicUserId(Integer userId) {
        try (var session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<FisaMedicala> cq = cb.createQuery(FisaMedicala.class);
            Root<FisaMedicala> root = cq.from(FisaMedicala.class);

            // Joining medic table
            Join<FisaMedicala, Medic> medicJoin = root.join("idMedic");

            // Joining user table through medic table
            Join<Medic, User> userJoin = medicJoin.join("idUser");

            // Specify the selection criteria
            cq.select(root).where(cb.equal(userJoin.get("idUser"), userId));

            Query<FisaMedicala> query = session.createQuery(cq);
            return query.getResultList();
        } catch (Exception e) {
            logDebug("---allFisaMedicalaByMedicUserId--- " + e);
            return null;
        }
    }

    public List<FisaMedicala> allFisaMedicalaByUserId(Integer userId) {
        try (var session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<FisaMedicala> cq = cb.createQuery(FisaMedicala.class);
            Root<FisaMedicala> root = cq.from(FisaMedicala.class);

            // Specify the selection criteria
            cq.select(root).where(cb.equal(root.get("idAsistent"), userId));

            Query<FisaMedicala> query = session.createQuery(cq);
            return query.getResultList();
        } catch (Exception e) {
            logDebug("---allFisaMedicalaByUserId--- " + e);
            return null;
        }
    }

    public List<FisaMedicala> allFisaMedicalaByMedicID(Integer userId) {
        try (var session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<FisaMedicala> cq = cb.createQuery(FisaMedicala.class);
            Root<FisaMedicala> root = cq.from(FisaMedicala.class);

            // Specify the selection criteria
            cq.select(root).where(cb.equal(root.get("idMedic"), userId));

            Query<FisaMedicala> query = session.createQuery(cq);
            return query.getResultList();
        } catch (Exception e) {
            logDebug("---allFisaMedicalaByMedicID--- " + e);
            return null;
        }
    }

    public void updateFisa(FisaMedicala fisaMedicala) {
        update(fisaMedicala);
    }

    public List<FisaMedicala> allFisaMedicalaByDiagnostic(String diagnostic) {
        try (var session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<FisaMedicala> cq = cb.createQuery(FisaMedicala.class);
            Root<FisaMedicala> root = cq.from(FisaMedicala.class);

            // Specify the selection criteria
            cq.select(root).where(cb.equal(root.get("diagnostic"), diagnostic));

            Query<FisaMedicala> query = session.createQuery(cq);
            return query.getResultList();
        } catch (Exception e) {
            logDebug("---allFisaMedicalaByDiagnostic--- " + e);
            return null;
        }
    }

    public List<FisaMedicala> allFisaMedicalaByTratament(String tratament) {
        try (var session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<FisaMedicala> cq = cb.createQuery(FisaMedicala.class);
            Root<FisaMedicala> root = cq.from(FisaMedicala.class);

            // Specify the selection criteria
            cq.select(root).where(cb.equal(root.get("tratament"), tratament));

            Query<FisaMedicala> query = session.createQuery(cq);
            return query.getResultList();
        } catch (Exception e) {
            logDebug("---allFisaMedicalaByTratament--- " + e);
            return null;
        }
    }

    public List<FisaMedicala> allFisaMedicalaByVarsta(Integer varsta) {
        try (var session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<FisaMedicala> cq = cb.createQuery(FisaMedicala.class);
            Root<FisaMedicala> root = cq.from(FisaMedicala.class);

            // Specify the selection criteria
            cq.select(root).where(cb.equal(root.get("varstaPacient"), varsta));

            Query<FisaMedicala> query = session.createQuery(cq);
            return query.getResultList();
        } catch (Exception e) {
            logDebug("---allFisaMedicalaByVarstaa-- " + e);
            return null;
        }
    }
}
