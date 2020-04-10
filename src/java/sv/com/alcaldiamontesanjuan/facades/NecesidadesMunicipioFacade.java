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
import sv.com.alcaldiamontesanjuan.entities.NecesidadesMunicipio;
import sv.com.alcaldiamontesanjuan.entities.ProductosAgricolas;

/**
 *
 * @author elias
 */
@Stateless
public class NecesidadesMunicipioFacade extends AbstractFacade<NecesidadesMunicipio> {

    @PersistenceContext(unitName = "projectCensoAlcaldiaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public NecesidadesMunicipioFacade() {
        super(NecesidadesMunicipio.class);
    }
    public List<NecesidadesMunicipio> listarNecesidadesPorFamilia(String codigo){
        List<NecesidadesMunicipio> lista= new ArrayList<NecesidadesMunicipio>();        
        Query query=em.createQuery("SELECT n FROM NecesidadesMunicipio n WHERE n.codigoEntrevistado.id=:codigo");
        query.setParameter("codigo", codigo);
        try {
            lista=query.getResultList();
            return lista;
        } catch (NoResultException e) {
            return lista;
        }    }
    
}
