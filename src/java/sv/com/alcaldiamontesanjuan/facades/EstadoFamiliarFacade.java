/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.com.alcaldiamontesanjuan.facades;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import sv.com.alcaldiamontesanjuan.entities.EstadoFamiliar;

/**
 *
 * @author elias
 */
@Stateless
public class EstadoFamiliarFacade extends AbstractFacade<EstadoFamiliar> {

    @PersistenceContext(unitName = "projectCensoAlcaldiaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EstadoFamiliarFacade() {
        super(EstadoFamiliar.class);
    }
    
}
