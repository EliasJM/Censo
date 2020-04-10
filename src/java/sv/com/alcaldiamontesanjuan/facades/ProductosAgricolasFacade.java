package sv.com.alcaldiamontesanjuan.facades;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import sv.com.alcaldiamontesanjuan.entities.SeCultivanProductosAgricolas;
import sv.com.alcaldiamontesanjuan.entities.ProductosAgricolas;

@Stateless
public class ProductosAgricolasFacade extends AbstractFacade<ProductosAgricolas> {

    @PersistenceContext(unitName = "projectCensoAlcaldiaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProductosAgricolasFacade() {
        super(ProductosAgricolas.class);
    }
    public List<ProductosAgricolas> getListarProductosAgricolasPorEntrevistado(String codigo){
        List<ProductosAgricolas> lista= new ArrayList<ProductosAgricolas>();        
        Query query=em.createQuery("SELECT p FROM ProductosAgricolas p, SeCultivanProductosAgricolas s WHERE p.codigo = s.codigoPoducto.codigo AND s.codigoEntrevistado.id=:codigo");
        query.setParameter("codigo", codigo);
        try {
            lista=query.getResultList();
            return lista;
        } catch (NoResultException e) {
            return lista;
        }
    }
    
}
