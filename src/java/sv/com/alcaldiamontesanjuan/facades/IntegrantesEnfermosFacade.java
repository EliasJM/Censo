/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.com.alcaldiamontesanjuan.facades;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import sv.com.alcaldiamontesanjuan.entities.Integrantes;
import sv.com.alcaldiamontesanjuan.entities.IntegrantesEnfermos;

/**
 *
 * @author elias
 */
@Stateless
public class IntegrantesEnfermosFacade extends AbstractFacade<IntegrantesEnfermos> {

    @PersistenceContext(unitName = "projectCensoAlcaldiaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public IntegrantesEnfermosFacade() {
        super(IntegrantesEnfermos.class);
    }
    
    public List<IntegrantesEnfermos> getListarEnfermedadesPorIntegrante(String codigo){
        List<IntegrantesEnfermos> lista = new ArrayList<IntegrantesEnfermos>();         
       Query query=em.createQuery("SELECT ie FROM IntegrantesEnfermos ie WHERE ie.codigoIntegrante.codigo=:codigo");
        query.setParameter("codigo", codigo);
        try {
            lista=query.getResultList();
            return lista;
        } catch (NoResultException e) {
            return lista;
        }
    } 
    
    public IntegrantesEnfermos getVerificarSiYaExiste(String codigoIntegrante, String codigoEnfermedad){
              
       Query query=em.createQuery("SELECT ie FROM IntegrantesEnfermos ie WHERE ie.codigoIntegrante.codigo=:codigo AND ie.discapacidadEnfermedad.codigo=:codigoE");
        query.setParameter("codigoI", codigoIntegrante);
        query.setParameter("codigoE", codigoEnfermedad);
        try {
            return (IntegrantesEnfermos) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    } 
    
}
