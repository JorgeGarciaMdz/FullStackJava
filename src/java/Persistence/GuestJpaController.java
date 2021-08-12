/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistence;

import Entity.Guest;
import Persistence.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 *
 * @author jorge
 */
public class GuestJpaController implements Serializable {

    public GuestJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Guest guest) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(guest);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Guest guest) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            guest = em.merge(guest);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = guest.getId();
                if (findGuest(id) == null) {
                    throw new NonexistentEntityException("The guest with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Guest guest;
            try {
                guest = em.getReference(Guest.class, id);
                guest.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The guest with id " + id + " no longer exists.", enfe);
            }
            em.remove(guest);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Guest> findGuestEntities() {
        return findGuestEntities(true, -1, -1);
    }

    public List<Guest> findGuestEntities(int maxResults, int firstResult) {
        return findGuestEntities(false, maxResults, firstResult);
    }

    private List<Guest> findGuestEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Guest.class));
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

    public Guest findGuest(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Guest.class, id);
        } finally {
            em.close();
        }
    }

    public Guest findGuestByDni(Long dni) {
        EntityManager em = getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Guest> cq = cb.createQuery(Guest.class);
        Root<Guest> root = cq.from(Guest.class);

        Predicate predicate = cb.equal(root.get("dni"), dni);
        cq.select(root).where(predicate);
        Query q = em.createQuery(cq);
        try {
            return (Guest) q.getSingleResult();
        } catch (NoResultException ex) {
            System.out.println("GuestJpaController exception\n\t " + ex.getMessage());
            return null;
        } finally {
            em.close();
        }
    }

    public int getGuestCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Guest> rt = cq.from(Guest.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
