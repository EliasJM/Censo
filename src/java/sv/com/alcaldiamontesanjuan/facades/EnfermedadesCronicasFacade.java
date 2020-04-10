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
import sv.com.alcaldiamontesanjuan.entities.EnfermedadesCronicas;
import sv.com.alcaldiamontesanjuan.entities.FamiliasConEnfermedadesCronicas;
import sv.com.alcaldiamontesanjuan.entities.IntegrantesEnfermos;

/**
 *
 * @author elias
 */
@Stateless
public class EnfermedadesCronicasFacade extends AbstractFacade<EnfermedadesCronicas> {

    @PersistenceContext(unitName = "projectCensoAlcaldiaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EnfermedadesCronicasFacade() {
        super(EnfermedadesCronicas.class);
    }

    public List<EnfermedadesCronicas> getListarEnfermedadesPorIntegrante(String codigo) {
        List<EnfermedadesCronicas> lista = new ArrayList<EnfermedadesCronicas>();
        Query query = em.createQuery("SELECT e FROM EnfermedadesCronicas e, IntegrantesEnfermos i WHERE e.codigo=i.discapacidadEnfermedad.codigo AND i.codigoIntegrante.codigo=:codigo");
        query.setParameter("codigo", codigo);
        try {
            lista = query.getResultList();
            return lista;
        } catch (NoResultException e) {
            return lista;
        }

    }

    public List<EnfermedadesCronicas> getListarEnfermedadesPorEntrevistado(String codigo) {
        List<EnfermedadesCronicas> lista = new ArrayList<EnfermedadesCronicas>();
        Query query = em.createQuery("SELECT e FROM EnfermedadesCronicas e, FamiliasConEnfermedadesCronicas f WHERE e.codigo=f.codigoEnfermedadCronica.codigo AND f.codigoEntrevistado.id=:codigo");
        query.setParameter("codigo", codigo);
        try {
            lista = query.getResultList();
            return lista;
        } catch (NoResultException e) {
            return lista;
        }

    }


}
