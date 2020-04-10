/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.com.alcaldiamontesanjuan.facades;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import sv.com.alcaldiamontesanjuan.entities.Canton;
import sv.com.alcaldiamontesanjuan.entities.Caserio;
import sv.com.alcaldiamontesanjuan.entities.Entrevistado;

/**
 *
 * @author elias
 */
@Stateless
public class CaserioFacade extends AbstractFacade<Caserio> {

    @PersistenceContext(unitName = "projectCensoAlcaldiaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CaserioFacade() {
        super(Caserio.class);
    }
    public List<Caserio> consultarPorCanton(String codigo){        
        List<Caserio> lista=new ArrayList<Caserio>();
        Query query=em.createQuery("SELECT e FROM Caserio e WHERE e.codigoCanton.codigo=:codigo");
        query.setParameter("codigo", codigo);
        try {
            lista=query.getResultList();
            return lista;
        } catch (NoResultException e) {
            return lista;
        }         
    }
    
}
