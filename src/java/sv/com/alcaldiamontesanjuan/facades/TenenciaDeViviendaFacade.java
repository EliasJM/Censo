/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.com.alcaldiamontesanjuan.facades;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import sv.com.alcaldiamontesanjuan.entities.TenenciaDeVivienda;

/**
 *
 * @author elias
 */
@Stateless
public class TenenciaDeViviendaFacade extends AbstractFacade<TenenciaDeVivienda> {

    @PersistenceContext(unitName = "projectCensoAlcaldiaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TenenciaDeViviendaFacade() {
        super(TenenciaDeVivienda.class);
    }
    
}
