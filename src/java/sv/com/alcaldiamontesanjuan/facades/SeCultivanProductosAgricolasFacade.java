/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.com.alcaldiamontesanjuan.facades;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import sv.com.alcaldiamontesanjuan.entities.FamiliasConEnfermedadesCronicas;
import sv.com.alcaldiamontesanjuan.entities.SeCultivanProductosAgricolas;

/**
 *
 * @author elias
 */
@Stateless
public class SeCultivanProductosAgricolasFacade extends AbstractFacade<SeCultivanProductosAgricolas> {

    @PersistenceContext(unitName = "projectCensoAlcaldiaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SeCultivanProductosAgricolasFacade() {
        super(SeCultivanProductosAgricolas.class);
    }
    
    public SeCultivanProductosAgricolas getVerificarExistencias(String codigoProducto,String codigoEntrevistado){
        Query query=em.createQuery("SELECT s FROM SeCultivanProductosAgricolas s WHERE s.codigoPoducto.codigo=:codigoP AND  s.codigoEntrevistado.id=:codigoEnt");
        query.setParameter("codigoP", codigoProducto );
        query.setParameter("codigoEnt", codigoEntrevistado );
        try {            
            return (SeCultivanProductosAgricolas) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }        
    }
    public List<SeCultivanProductosAgricolas> listarProductosAgricolas(String codigoEntrevistado) {
        Query query = em.createQuery("SELECT s FROM SeCultivanProductosAgricolas s WHERE  s.codigoEntrevistado.id=:codigoEnt");
        query.setParameter("codigoEnt", codigoEntrevistado);
        try {
            return query.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }
    
    
    
    
}
