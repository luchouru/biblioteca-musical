package com.mycompany.biblioteca.musical.persistencia;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.mycompany.biblioteca.musical.logica.Banda;
import com.mycompany.biblioteca.musical.logica.Pais;
import com.mycompany.biblioteca.musical.persistencia.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class PaisJpaController implements Serializable {

    public PaisJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;
    
    public PaisJpaController() {
        emf = Persistence.createEntityManagerFactory("biblioMusicalPU");
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Pais pais) {
        if (pais.getListaBandas() == null) {
            pais.setListaBandas(new ArrayList<Banda>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Banda> attachedListaBandas = new ArrayList<Banda>();
            for (Banda listaBandasBandaToAttach : pais.getListaBandas()) {
                listaBandasBandaToAttach = em.getReference(listaBandasBandaToAttach.getClass(), listaBandasBandaToAttach.getId());
                attachedListaBandas.add(listaBandasBandaToAttach);
            }
            pais.setListaBandas(attachedListaBandas);
            em.persist(pais);
            for (Banda listaBandasBanda : pais.getListaBandas()) {
                Pais oldNacionalidadOfListaBandasBanda = listaBandasBanda.getNacionalidad();
                listaBandasBanda.setNacionalidad(pais);
                listaBandasBanda = em.merge(listaBandasBanda);
                if (oldNacionalidadOfListaBandasBanda != null) {
                    oldNacionalidadOfListaBandasBanda.getListaBandas().remove(listaBandasBanda);
                    oldNacionalidadOfListaBandasBanda = em.merge(oldNacionalidadOfListaBandasBanda);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Pais pais) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Pais persistentPais = em.find(Pais.class, pais.getId());
            List<Banda> listaBandasOld = persistentPais.getListaBandas();
            List<Banda> listaBandasNew = pais.getListaBandas();
            List<Banda> attachedListaBandasNew = new ArrayList<Banda>();
            for (Banda listaBandasNewBandaToAttach : listaBandasNew) {
                listaBandasNewBandaToAttach = em.getReference(listaBandasNewBandaToAttach.getClass(), listaBandasNewBandaToAttach.getId());
                attachedListaBandasNew.add(listaBandasNewBandaToAttach);
            }
            listaBandasNew = attachedListaBandasNew;
            pais.setListaBandas(listaBandasNew);
            pais = em.merge(pais);
            for (Banda listaBandasOldBanda : listaBandasOld) {
                if (!listaBandasNew.contains(listaBandasOldBanda)) {
                    listaBandasOldBanda.setNacionalidad(null);
                    listaBandasOldBanda = em.merge(listaBandasOldBanda);
                }
            }
            for (Banda listaBandasNewBanda : listaBandasNew) {
                if (!listaBandasOld.contains(listaBandasNewBanda)) {
                    Pais oldNacionalidadOfListaBandasNewBanda = listaBandasNewBanda.getNacionalidad();
                    listaBandasNewBanda.setNacionalidad(pais);
                    listaBandasNewBanda = em.merge(listaBandasNewBanda);
                    if (oldNacionalidadOfListaBandasNewBanda != null && !oldNacionalidadOfListaBandasNewBanda.equals(pais)) {
                        oldNacionalidadOfListaBandasNewBanda.getListaBandas().remove(listaBandasNewBanda);
                        oldNacionalidadOfListaBandasNewBanda = em.merge(oldNacionalidadOfListaBandasNewBanda);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = pais.getId();
                if (findPais(id) == null) {
                    throw new NonexistentEntityException("The pais with id " + id + " no longer exists.");
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
            Pais pais;
            try {
                pais = em.getReference(Pais.class, id);
                pais.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The pais with id " + id + " no longer exists.", enfe);
            }
            List<Banda> listaBandas = pais.getListaBandas();
            for (Banda listaBandasBanda : listaBandas) {
                listaBandasBanda.setNacionalidad(null);
                listaBandasBanda = em.merge(listaBandasBanda);
            }
            em.remove(pais);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Pais> findPaisEntities() {
        return findPaisEntities(true, -1, -1);
    }

    public List<Pais> findPaisEntities(int maxResults, int firstResult) {
        return findPaisEntities(false, maxResults, firstResult);
    }

    private List<Pais> findPaisEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Pais.class));
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

    public Pais findPais(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Pais.class, id);
        } finally {
            em.close();
        }
    }

    public int getPaisCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Pais> rt = cq.from(Pais.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
