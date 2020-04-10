package sv.com.alcaldiamontesanjuan.facades;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import sv.com.alcaldiamontesanjuan.entities.EnfermedadesCronicas;
import sv.com.alcaldiamontesanjuan.entities.Entrevistado;
@Stateless
public class EntrevistadoFacade extends AbstractFacade<Entrevistado> {

    @PersistenceContext(unitName = "projectCensoAlcaldiaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EntrevistadoFacade() {
        super(Entrevistado.class);
    }

    public Entrevistado getEntrevistadoPorFicha(String codigo) {
        Entrevistado n = new Entrevistado();
        Query query = em.createQuery("SELECT e FROM Entrevistado e WHERE e.codigoEntrevista=:codigo");
        query.setParameter("codigo", codigo);
        try {
            return (Entrevistado) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
    public List<Entrevistado> getEntrevistadoPorUsuarioFindAll(String codigo) {
        Entrevistado n = new Entrevistado();
        Query query = em.createQuery("SELECT e FROM Entrevistado e WHERE e.codigoUsuario.codigo=:codigo");
        query.setParameter("codigo", codigo);
        try {
            return query.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    //metodos para reportes
    public List<Object[]> getContarProfesionOficio() {
        Query query = em.createQuery("SELECT p.nombre,COUNT(e.codigoProfesionOficio.codigo) FROM Entrevistado e, ProfesionOficio p WHERE e.codigoProfesionOficio.codigo=p.codigo GROUP BY p.nombre");
        try {
            List<Object[]> objs=query.getResultList();
            return objs;
        } catch (NoResultException e) {
            return null;
        }
    }    
    public  List<Object[]> getContarNivelesEducacion(){
        Query query = em.createQuery("SELECT n.nombre,COUNT(e.codigoNivelesEducacion.codigo) FROM Entrevistado e, NivelesDeEducacion n WHERE e.codigoNivelesEducacion.codigo=n.codigo GROUP BY n.nombre");
        try {
            List<Object[]> objs=query.getResultList();
            return objs;
        } catch (NoResultException e) {
            return null;
        }        
    }
    public List<Object[]> getContarPoseeDiscapacidad(){
        Query query = em.createQuery("SELECT a.nombre,COUNT(e.poseeDiscapacidadFisica.codigo) FROM Entrevistado e, Aplica a WHERE e.poseeDiscapacidadFisica.codigo=a.codigo GROUP BY a.nombre");
        try {
            List<Object[]> objs=query.getResultList();
            return objs;
        } catch (NoResultException e) {
            return null;
        }                
    }
    public List<Object[]> getContarRecibeInsumosAgricolas(){
        Query query = em.createQuery("SELECT a.nombre,COUNT(e.recibeInsumoAgricolas.codigo) FROM Entrevistado e, Aplica a WHERE e.recibeInsumoAgricolas.codigo=a.codigo GROUP BY a.nombre");
        try {
            List<Object[]> objs=query.getResultList();
            return objs;
        } catch (NoResultException e) {
            return null;
        }
    }
    public List<Object[]> getContarFuenteDeIngreso(){
        Query query = em.createQuery("SELECT f.nombre,COUNT(e.fuenteDeIngreso.codigo) FROM Entrevistado e, FuentesDeIngreso f WHERE e.fuenteDeIngreso.codigo=f.codigo GROUP BY f.nombre");
        try {
            List<Object[]> objs=query.getResultList();
            return objs;
        } catch (NoResultException e) {
            return null;
        }
    }
}
