
package com.mycompany.biblioteca.musical.persistencia;

import com.mycompany.biblioteca.musical.logica.Banda;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.mycompany.biblioteca.musical.logica.Disco;
import com.mycompany.biblioteca.musical.persistencia.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class BandaJpaController implements Serializable {

    public BandaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;
    
    public BandaJpaController(){
        emf = Persistence.createEntityManagerFactory("biblioMusicalPU");
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Banda banda) {
        if (banda.getDiscografia() == null) {
            banda.setDiscografia(new ArrayList<Disco>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Disco> attachedDiscografia = new ArrayList<Disco>();
            for (Disco discografiaDiscoToAttach : banda.getDiscografia()) {
                discografiaDiscoToAttach = em.getReference(discografiaDiscoToAttach.getClass(), discografiaDiscoToAttach.getId());
                attachedDiscografia.add(discografiaDiscoToAttach);
            }
            banda.setDiscografia(attachedDiscografia);
            em.persist(banda);
            for (Disco discografiaDisco : banda.getDiscografia()) {
                Banda oldAutorOfDiscografiaDisco = discografiaDisco.getAutor();
                discografiaDisco.setAutor(banda);
                discografiaDisco = em.merge(discografiaDisco);
                if (oldAutorOfDiscografiaDisco != null) {
                    oldAutorOfDiscografiaDisco.getDiscografia().remove(discografiaDisco);
                    oldAutorOfDiscografiaDisco = em.merge(oldAutorOfDiscografiaDisco);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Banda banda) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Banda persistentBanda = em.find(Banda.class, banda.getId());
            List<Disco> discografiaOld = persistentBanda.getDiscografia();
            List<Disco> discografiaNew = banda.getDiscografia();
            List<Disco> attachedDiscografiaNew = new ArrayList<Disco>();
            for (Disco discografiaNewDiscoToAttach : discografiaNew) {
                discografiaNewDiscoToAttach = em.getReference(discografiaNewDiscoToAttach.getClass(), discografiaNewDiscoToAttach.getId());
                attachedDiscografiaNew.add(discografiaNewDiscoToAttach);
            }
            discografiaNew = attachedDiscografiaNew;
            banda.setDiscografia(discografiaNew);
            banda = em.merge(banda);
            for (Disco discografiaOldDisco : discografiaOld) {
                if (!discografiaNew.contains(discografiaOldDisco)) {
                    discografiaOldDisco.setAutor(null);
                    discografiaOldDisco = em.merge(discografiaOldDisco);
                }
            }
            for (Disco discografiaNewDisco : discografiaNew) {
                if (!discografiaOld.contains(discografiaNewDisco)) {
                    Banda oldAutorOfDiscografiaNewDisco = discografiaNewDisco.getAutor();
                    discografiaNewDisco.setAutor(banda);
                    discografiaNewDisco = em.merge(discografiaNewDisco);
                    if (oldAutorOfDiscografiaNewDisco != null && !oldAutorOfDiscografiaNewDisco.equals(banda)) {
                        oldAutorOfDiscografiaNewDisco.getDiscografia().remove(discografiaNewDisco);
                        oldAutorOfDiscografiaNewDisco = em.merge(oldAutorOfDiscografiaNewDisco);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = banda.getId();
                if (findBanda(id) == null) {
                    throw new NonexistentEntityException("The banda with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(int id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Banda banda;
            try {
                banda = em.getReference(Banda.class, id);
                banda.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The banda with id " + id + " no longer exists.", enfe);
            }
            List<Disco> discografia = banda.getDiscografia();
            for (Disco discografiaDisco : discografia) {
                discografiaDisco.setAutor(null);
                discografiaDisco = em.merge(discografiaDisco);
            }
            em.remove(banda);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Banda> findBandaEntities() {
        return findBandaEntities(true, -1, -1);
    }

    public List<Banda> findBandaEntities(int maxResults, int firstResult) {
        return findBandaEntities(false, maxResults, firstResult);
    }

    private List<Banda> findBandaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Banda.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Banda findBanda(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Banda.class, id);
        } finally {
            em.close();
        }
    }

    public int getBandaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Banda> rt = cq.from(Banda.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
