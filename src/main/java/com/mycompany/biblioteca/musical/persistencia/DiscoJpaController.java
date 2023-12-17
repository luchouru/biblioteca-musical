
package com.mycompany.biblioteca.musical.persistencia;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.mycompany.biblioteca.musical.logica.Banda;
import com.mycompany.biblioteca.musical.logica.Disco;
import com.mycompany.biblioteca.musical.persistencia.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class DiscoJpaController implements Serializable {

    public DiscoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;
    
    public DiscoJpaController(){
        emf = Persistence.createEntityManagerFactory("biblioMusicalPU");
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Disco disco) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Banda autor = disco.getAutor();
            if (autor != null) {
                autor = em.getReference(autor.getClass(), autor.getId());
                disco.setAutor(autor);
            }
            em.persist(disco);
            if (autor != null) {
                autor.getDiscografia().add(disco);
                autor = em.merge(autor);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Disco disco) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Disco persistentDisco = em.find(Disco.class, disco.getId());
            Banda autorOld = persistentDisco.getAutor();
            Banda autorNew = disco.getAutor();
            if (autorNew != null) {
                autorNew = em.getReference(autorNew.getClass(), autorNew.getId());
                disco.setAutor(autorNew);
            }
            disco = em.merge(disco);
            if (autorOld != null && !autorOld.equals(autorNew)) {
                autorOld.getDiscografia().remove(disco);
                autorOld = em.merge(autorOld);
            }
            if (autorNew != null && !autorNew.equals(autorOld)) {
                autorNew.getDiscografia().add(disco);
                autorNew = em.merge(autorNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = disco.getId();
                if (findDisco(id) == null) {
                    throw new NonexistentEntityException("The disco with id " + id + " no longer exists.");
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
            Disco disco;
            try {
                disco = em.getReference(Disco.class, id);
                disco.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The disco with id " + id + " no longer exists.", enfe);
            }
            Banda autor = disco.getAutor();
            if (autor != null) {
                autor.getDiscografia().remove(disco);
                autor = em.merge(autor);
            }
            em.remove(disco);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Disco> findDiscoEntities() {
        return findDiscoEntities(true, -1, -1);
    }

    public List<Disco> findDiscoEntities(int maxResults, int firstResult) {
        return findDiscoEntities(false, maxResults, firstResult);
    }

    private List<Disco> findDiscoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Disco.class));
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

    public Disco findDisco(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Disco.class, id);
        } finally {
            em.close();
        }
    }

    public int getDiscoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Disco> rt = cq.from(Disco.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
