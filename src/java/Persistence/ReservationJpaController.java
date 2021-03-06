/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistence;

import Entity.Person_;
import Entity.Reservation;
import Entity.Reservation_;
import Persistence.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Date;
import javax.persistence.criteria.JoinType;

/**
 *
 * @author jorge
 */
public class ReservationJpaController implements Serializable {

    public ReservationJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Reservation reservation) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(reservation);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Reservation reservation) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            reservation = em.merge(reservation);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = reservation.getId();
                if (findReservation(id) == null) {
                    throw new NonexistentEntityException("The reservation with id " + id + " no longer exists.");
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
            Reservation reservation;
            try {
                reservation = em.getReference(Reservation.class, id);
                reservation.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The reservation with id " + id + " no longer exists.", enfe);
            }
            em.remove(reservation);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Reservation> findReservationEntities() {
        return findReservationEntities(true, -1, -1);
    }

    public List<Reservation> findReservationEntities(int maxResults, int firstResult) {
        return findReservationEntities(false, maxResults, firstResult);
    }

    private List<Reservation> findReservationEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Reservation.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public Reservation findReservation(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Reservation.class, id);
        } finally {
            em.close();
        }
    }

    public List<Reservation> findByRoomId(Long id) {
        EntityManager em = getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Reservation> cq = cb.createQuery(Reservation.class);
        Root<Reservation> root = cq.from(Reservation.class);
        root.fetch("room", JoinType.LEFT);
        int year = new Date().getYear();
        int month = new Date().getMonth();

        Predicate[] predicates = new Predicate[3];
        predicates[0] = cb.equal(root.get(Reservation_.room).get("id"), id);
        predicates[1] = cb.greaterThanOrEqualTo(root.get("date_in"), new Date(year, month, 0));
        predicates[2] = cb.lessThanOrEqualTo(root.get("date_out"), new Date(year, month, 30));
        try {
            cq.select(root).where(predicates).orderBy(cb.asc(root.get("date_in")));
            return em.createQuery(cq).getResultList();
        } finally {
            em.close();
        }
    }
    
    public List<Reservation> findByDate(String date_from, String date_to){
        EntityManager em = getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Reservation> cq = cb.createQuery(Reservation.class);
        Root<Reservation> root = cq.from(Reservation.class);
        
        Predicate[] predicates = new Predicate[2];
        predicates[0] = cb.greaterThanOrEqualTo(root.get("date_in"), date_from);
        predicates[1] = cb.lessThanOrEqualTo(root.get("date_out"), date_to);
        
        try {
            cq.select(root).where(predicates).orderBy(cb.asc(root.get("date_in")));
            return em.createQuery(cq).getResultList();
        } finally {
            em.close();
        }
    }
    
    public List<Reservation> findByDateAndEmployee( String date_from, String date_to, String id_employee){
        EntityManager em = getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Reservation> cq = cb.createQuery(Reservation.class);
        Root<Reservation> root = cq.from(Reservation.class);
        root.fetch("employee", JoinType.LEFT);
        
        Predicate[] predicates = new Predicate[3];
        predicates[0] = cb.greaterThanOrEqualTo(root.get("date_in"), date_from);
        predicates[1] = cb.lessThanOrEqualTo(root.get("date_out"), date_to);
        predicates[2] = cb.equal(root.get(Reservation_.employee).get("id"), id_employee);
        
        try{
            cq.select(root).where(predicates).orderBy(cb.asc(root.get("date_in")));
            return em.createQuery(cq).getResultList();
        } finally {
            em.close();
        }
    }

    public int getReservationCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Reservation> rt = cq.from(Reservation.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
