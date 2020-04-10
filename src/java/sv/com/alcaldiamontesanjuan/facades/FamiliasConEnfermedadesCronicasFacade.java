package sv.com.alcaldiamontesanjuan.facades;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import sv.com.alcaldiamontesanjuan.entities.FamiliasConEnfermedadesCronicas;
import sv.com.alcaldiamontesanjuan.entities.ProductosAgricolas;
import sv.com.alcaldiamontesanjuan.utils.ListaModelo;

@Stateless
public class FamiliasConEnfermedadesCronicasFacade extends AbstractFacade<FamiliasConEnfermedadesCronicas> {

    @PersistenceContext(unitName = "projectCensoAlcaldiaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FamiliasConEnfermedadesCronicasFacade() {
        super(FamiliasConEnfermedadesCronicas.class);
    }

    public FamiliasConEnfermedadesCronicas getVerificarExistencias(String codigoEnfermedad, String codigoEntrevistado) {
        Query query = em.createQuery("SELECT f FROM FamiliasConEnfermedadesCronicas f WHERE f.codigoEnfermedadCronica.codigo=:codigoEnf AND  f.codigoEntrevistado.id=:codigoEnt");
        query.setParameter("codigoEnf", codigoEnfermedad);
        query.setParameter("codigoEnt", codigoEntrevistado);
        try {
            return (FamiliasConEnfermedadesCronicas) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
    public List<FamiliasConEnfermedadesCronicas> listarEnfermedadesDeIntegrante(String codigoEntrevistado) {
        Query query = em.createQuery("SELECT f FROM FamiliasConEnfermedadesCronicas f WHERE  f.codigoEntrevistado.id=:codigoEnt");
        query.setParameter("codigoEnt", codigoEntrevistado);
        try {
            return query.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    

    public List<Object[]> getlistarEnfermedadesGBy() {
        //Query query = em.createQuery("SELECT e.nombre as nombre, COUNT(f.codigoEnfermedadCronica.codigo) as cantidad FROM FamiliasConEnfermedadesCronicas f, EnfermedadesCronicas e WHERE f.codigoEnfermedadCronica.codigo=e.codigo GROUP BY e.nombre");
        Query query = em.createQuery("SELECT e.nombre as nombre, COUNT(f.codigoEnfermedadCronica.codigo) as cantidad FROM FamiliasConEnfermedadesCronicas f, EnfermedadesCronicas e WHERE f.codigoEnfermedadCronica.codigo=e.codigo GROUP BY e.nombre", ListaModelo.class);
        try {
            //lista=query.getResultList();
            List<Object[]> objs = query.getResultList();

            return objs;
        } catch (NoResultException e) {
            return null;
        }
    }

}
