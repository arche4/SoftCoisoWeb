/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softcoisoweb.controller;

import com.softcoisoweb.controller.exceptions.NonexistentEntityException;
import com.softcoisoweb.controller.exceptions.PreexistingEntityException;
import com.softcoisoweb.model.Calificacion;
import com.softcoisoweb.model.CasoPersona;
import com.softcoisoweb.model.Diagnostico;
import com.softcoisoweb.model.Medicamentos;
import com.softcoisoweb.model.ProcesoCalificacion;
import com.softcoisoweb.model.ProcesoReclamacion;
import java.io.Serializable;
import java.util.List;
import javax.persistence.CacheStoreMode;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author manue
 */
public class CasoPersonaJpaController implements Serializable {

    public CasoPersonaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(CasoPersona casoPersona) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(casoPersona);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCasoPersona(casoPersona.getIdCaso()) != null) {
                throw new PreexistingEntityException("CasoPersona " + casoPersona + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(CasoPersona casoPersona) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            casoPersona = em.merge(casoPersona);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = casoPersona.getIdCaso();
                if (findCasoPersona(id) == null) {
                    throw new NonexistentEntityException("The casoPersona with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CasoPersona casoPersona;
            try {
                casoPersona = em.getReference(CasoPersona.class, id);
                casoPersona.getIdCaso();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The casoPersona with id " + id + " no longer exists.", enfe);
            }
            em.remove(casoPersona);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<CasoPersona> casosxpersona(String cedula) {
        EntityManager em = null;
        List<CasoPersona> listCasoxPer = null;
        try {
            String QuerySelect = "select * from caso_persona where persona_cedula  =  '" + cedula + "'";
            em = getEntityManager();
            em.getTransaction().begin();
            listCasoxPer = em.createNativeQuery(QuerySelect, CasoPersona.class).getResultList();
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return listCasoxPer;
    }

    public List<CasoPersona> findCasoPersonaEntities() {
        return findCasoPersonaEntities(true, -1, -1);
    }

    public List<CasoPersona> findCasoPersonaEntities(int maxResults, int firstResult) {
        return findCasoPersonaEntities(false, maxResults, firstResult);
    }

    private List<CasoPersona> findCasoPersonaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CasoPersona.class));
            Query q = em.createQuery(cq);
            q.setHint("javax.persistence.cache.storeMode", CacheStoreMode.REFRESH);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public CasoPersona findCasoPersona(String id) {
        EntityManager em = getEntityManager();

        try {
            em.setProperty("javax.persistence.cache.storeMode", CacheStoreMode.BYPASS);
            return em.find(CasoPersona.class, id);
        } finally {
            em.close();
        }
    }

    public int getCasoPersonaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CasoPersona> rt = cq.from(CasoPersona.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public void asignarUsuario(String casoId, String usuarioAsignado) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            String Query = "UPDATE caso_persona SET asignado = '" + usuarioAsignado + "' WHERE id_caso = '" + casoId + "'";
            em = getEntityManager();
            em.getTransaction().begin();
            em.createNativeQuery(Query).executeUpdate();
            em.getTransaction().commit();
        } catch (Exception e) {
             throw new NonexistentEntityException("Se presento inconvenientes al  cambiar el responsable del caso " + casoId + ", El eror es: ", e);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
    public List<ProcesoCalificacion> casoXproceso(String casoId) {
        EntityManager em = null;
        List<ProcesoCalificacion> listCasoxProceso= null;
        try {
            String QuerySelect = "select * from proceso_calificacion where caso_persona_id_Caso = '" + casoId + "'";
            em = getEntityManager();
            em.getTransaction().begin();
            listCasoxProceso = em.createNativeQuery(QuerySelect, ProcesoCalificacion.class).getResultList();
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return listCasoxProceso;
    }
    
    public List<Calificacion> casoXcalificacion(String casoId) {
        EntityManager em = null;
        List<Calificacion> listCasoxCalificacion= null;
        try {
            String QuerySelect = "select * from calificacion where caso_persona_id_Caso = '" + casoId + "'";
            em = getEntityManager();
            em.getTransaction().begin();
            listCasoxCalificacion = em.createNativeQuery(QuerySelect, Calificacion.class).getResultList();
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return listCasoxCalificacion;
    }
    
    public List<ProcesoReclamacion> casoXreclamacion(String casoId) {
        EntityManager em = null;
        List<ProcesoReclamacion> listCasoxReclamacion= null;
        try {
            String QuerySelect = "select * from proceso_reclamacion where caso_persona_id_caso = '" + casoId + "'";
            em = getEntityManager();
            em.getTransaction().begin();
            listCasoxReclamacion = em.createNativeQuery(QuerySelect, ProcesoReclamacion.class).getResultList();
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return listCasoxReclamacion;
    }
    
    public List<Medicamentos> casoXmedicamentos(String casoId) {
        EntityManager em = null;
        List<Medicamentos> listCasoxMedicamento= null;
        try {
            String QuerySelect = "select * from medicamentos_caso where caso_persona_id_Caso = '" + casoId + "'";
            em = getEntityManager();
            em.getTransaction().begin();
            listCasoxMedicamento = em.createNativeQuery(QuerySelect, Medicamentos.class).getResultList();
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return listCasoxMedicamento;
    }
    
    public List<Diagnostico> casoXdiagnostico(String casoId) {
        EntityManager em = null;
        List<Diagnostico> listCasoxDiagnostico= null;
        try {
            String QuerySelect = "select * from diagnostico where id_caso  = '" + casoId + "'";
            em = getEntityManager();
            em.getTransaction().begin();
            listCasoxDiagnostico = em.createNativeQuery(QuerySelect, Diagnostico.class).getResultList();
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return listCasoxDiagnostico;
    }
    
    public void eliminarSeguimiento(String casoId) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            String Query = "DELETE FROM seguimiento_caso where codigo_caso = '" + casoId + "'";
            em = getEntityManager();
            em.getTransaction().begin();
            em.createNativeQuery(Query).executeUpdate();
            em.getTransaction().commit();
        } catch (Exception e) {
             throw new NonexistentEntityException("Se presento inconvenientes al eliminar el seguimiento del  caso " + casoId + ", El eror es: ", e);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
    public void eliminarComentarios(String casoId) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            String Query = "DELETE FROM caso_comentario where codigo_caso = '" + casoId + "'";
            em = getEntityManager();
            em.getTransaction().begin();
            em.createNativeQuery(Query).executeUpdate();
            em.getTransaction().commit();
        } catch (Exception e) {
             throw new NonexistentEntityException("Se presento inconvenientes al eliminar comentarios al caso " + casoId + ", El eror es: ", e);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
    public void eliminarArchivos(String casoId) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            String Query = "DELETE FROM caso_archivo where codigo_caso = '" + casoId + "'";
            em = getEntityManager();
            em.getTransaction().begin();
            em.createNativeQuery(Query).executeUpdate();
            em.getTransaction().commit();
        } catch (Exception e) {
             throw new NonexistentEntityException("Se presento inconvenientes al eliminar archivos al caso" + casoId + ", El eror es: ", e);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
    public void eliminarHistoricoEstado(String casoId) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            String Query = "DELETE FROM cambio_estado_historico where casoid = '" + casoId + "'";
            em = getEntityManager();
            em.getTransaction().begin();
            em.createNativeQuery(Query).executeUpdate();
            em.getTransaction().commit();
        } catch (Exception e) {
             throw new NonexistentEntityException("Se presento inconvenientes al eliminar archivos al caso" + casoId + ", El eror es: ", e);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
    public void cambiarCasoAsociado(String id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            String Query = "UPDATE persona SET caso_asociado = 'No' WHERE cedula = '" + id + "'";
            em = getEntityManager();
            em.getTransaction().begin();
            em.createNativeQuery(Query).executeUpdate();
            em.getTransaction().commit();
        } catch (Exception e) {
             throw new NonexistentEntityException("Se presento inconvenientes al actualizar  el caso asociado con la cedula " + id + "El eror es: ", e);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
}
