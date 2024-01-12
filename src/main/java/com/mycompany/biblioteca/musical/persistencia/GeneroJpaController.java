package com.mycompany.biblioteca.musical.persistencia;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.mycompany.biblioteca.musical.logica.Disco;
import com.mycompany.biblioteca.musical.logica.Genero;
import com.mycompany.biblioteca.musical.persistencia.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class GeneroJpaController implements Serializable {

    public GeneroJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;
    
    public GeneroJpaController() {
        emf = Persistence.createEntityManagerFactory("biblioMusicalPU");
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Genero genero) {
        if (genero.getListaDiscos() == null) {
            genero.setListaDiscos(new ArrayList<Disco>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Disco> attachedListaDiscos = new ArrayList<Disco>();
            for (Disco listaDiscosDiscoToAttach : genero.getListaDiscos()) {
                listaDiscosDiscoToAttach = em.getReference(listaDiscosDiscoToAttach.getClass(), listaDiscosDiscoToAttach.getId());
                attachedListaDiscos.add(listaDiscosDiscoToAttach);
            }
            genero.setListaDiscos(attachedListaDiscos);
            em.persist(genero);
            for (Disco listaDiscosDisco : genero.getListaDiscos()) {
                Genero oldGeneroOfListaDiscosDisco = listaDiscosDisco.getGenero();
                listaDiscosDisco.setGenero(genero);
                listaDiscosDisco = em.merge(listaDiscosDisco);
                if (oldGeneroOfListaDiscosDisco != null) {
                    oldGeneroOfListaDiscosDisco.getListaDiscos().remove(listaDiscosDisco);
                    oldGeneroOfListaDiscosDisco = em.merge(oldGeneroOfListaDiscosDisco);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Genero genero) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Genero persistentGenero = em.find(Genero.class, genero.getId());
            List<Disco> listaDiscosOld = persistentGenero.getListaDiscos();
            List<Disco> listaDiscosNew = genero.getListaDiscos();
            List<Disco> attachedListaDiscosNew = new ArrayList<Disco>();
            for (Disco listaDiscosNewDiscoToAttach : listaDiscosNew) {
                listaDiscosNewDiscoToAttach = em.getReference(listaDiscosNewDiscoToAttach.getClass(), listaDiscosNewDiscoToAttach.getId());
                attachedListaDiscosNew.add(listaDiscosNewDiscoToAttach);
            }
            listaDiscosNew = attachedListaDiscosNew;
            genero.setListaDiscos(listaDiscosNew);
            genero = em.merge(genero);
            for (Disco listaDiscosOldDisco : listaDiscosOld) {
                if (!listaDiscosNew.contains(listaDiscosOldDisco)) {
                    listaDiscosOldDisco.setGenero(null);
                    listaDiscosOldDisco = em.merge(listaDiscosOldDisco);
                }
            }
            for (Disco listaDiscosNewDisco : listaDiscosNew) {
                if (!listaDiscosOld.contains(listaDiscosNewDisco)) {
                    Genero oldGeneroOfListaDiscosNewDisco = listaDiscosNewDisco.getGenero();
                    listaDiscosNewDisco.setGenero(genero);
                    listaDiscosNewDisco = em.merge(listaDiscosNewDisco);
                    if (oldGeneroOfListaDiscosNewDisco != null && !oldGeneroOfListaDiscosNewDisco.equals(genero)) {
                        oldGeneroOfListaDiscosNewDisco.getListaDiscos().remove(listaDiscosNewDisco);
                        oldGeneroOfListaDiscosNewDisco = em.merge(oldGeneroOfListaDiscosNewDisco);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = genero.getId();
                if (findGenero(id) == null) {
                    throw new NonexistentEntityException("The genero with id " + id + " no longer exists.");
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
            Genero genero;
            try {
                genero = em.getReference(Genero.class, id);
                genero.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The genero with id " + id + " no longer exists.", enfe);
            }
            List<Disco> listaDiscos = genero.getListaDiscos();
            for (Disco listaDiscosDisco : listaDiscos) {
                listaDiscosDisco.setGenero(null);
                listaDiscosDisco = em.merge(listaDiscosDisco);
            }
            em.remove(genero);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Genero> findGeneroEntities() {
        return findGeneroEntities(true, -1, -1);
    }

    public List<Genero> findGeneroEntities(int maxResults, int firstResult) {
        return findGeneroEntities(false, maxResults, firstResult);
    }

    private List<Genero> findGeneroEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Genero.class));
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

    public Genero findGenero(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Genero.class, id);
        } finally {
            em.close();
        }
    }

    public int getGeneroCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Genero> rt = cq.from(Genero.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
