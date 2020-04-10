/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.com.alcaldiamontesanjuan.facades;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import sv.com.alcaldiamontesanjuan.entities.NivelesDeEducacion;

/**
 *
 * @author elias
 */
@Stateless
public class NivelesDeEducacionFacade extends AbstractFacade<NivelesDeEducacion> {

    @PersistenceContext(unitName = "projectCensoAlcaldiaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public NivelesDeEducacionFacade() {
        super(NivelesDeEducacion.class);
    }
    
}
