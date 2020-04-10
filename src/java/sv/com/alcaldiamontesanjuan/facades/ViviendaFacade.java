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
import sv.com.alcaldiamontesanjuan.entities.ProductosAgricolas;
import sv.com.alcaldiamontesanjuan.entities.Vivienda;
import sv.com.alcaldiamontesanjuan.entities.Aplica;

/**
 *
 * @author elias
 */
@Stateless
public class ViviendaFacade extends AbstractFacade<Vivienda> {

    @PersistenceContext(unitName = "projectCensoAlcaldiaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ViviendaFacade() {
        super(Vivienda.class);
    }

    public Vivienda getListarViviendaPorEntrevistado(String codigo) {
        Vivienda vivienda = new Vivienda();
        Query query = em.createQuery("SELECT v FROM Vivienda v WHERE v.codigoEntrevistado.id=:codigo");
        query.setParameter("codigo", codigo);
        try {
            return (Vivienda) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    //INICIA METODOS PARA LA GENERACIÓN DE GRAFICOS
    public List<Object[]> getContarPoseeEscrituras() {
        Query query = em.createQuery("SELECT a.nombre,COUNT(v.poseeEscrituras.codigo) FROM Vivienda v, Aplica a WHERE v.poseeEscrituras.codigo=a.codigo GROUP BY a.nombre");
        try {
            List<Object[]> objs = query.getResultList();
            return objs;
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<Object[]> getContarPredioMunicipal() {
        Query query = em.createQuery("SELECT a.nombre,COUNT(v.predioMunicipal.codigo) FROM Vivienda v, Aplica a WHERE v.predioMunicipal.codigo=a.codigo GROUP BY a.nombre");
        try {
            List<Object[]> objs = query.getResultList();
            return objs;
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<Object[]> getContarRegistrada() {
        Query query = em.createQuery("SELECT a.nombre,COUNT(v.registrada.codigo) FROM Vivienda v, Aplica a WHERE v.registrada.codigo=a.codigo GROUP BY a.nombre");
        try {
            List<Object[]> objs = query.getResultList();
            return objs;
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<Object[]> getContarTenenciaDeVivienda() {
        Query query = em.createQuery("SELECT t.nombre,COUNT(v.codigoTenenciaDeVivienda.codigo) FROM Vivienda v, TenenciaDeVivienda t WHERE v.codigoTenenciaDeVivienda.codigo =t.codigo GROUP BY t.nombre");
        try {
            List<Object[]> objs = query.getResultList();
            return objs;
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<Object[]> getContarConstruidaPorChildren() {
        Query query = em.createQuery("SELECT a.nombre,COUNT(v.construidaPorChilden.codigo) FROM Vivienda v, Aplica a WHERE v.construidaPorChilden.codigo =a.codigo GROUP BY a.nombre");
        try {
            List<Object[]> objs = query.getResultList();
            return objs;
        } catch (NoResultException e) {
            return null;
        }
    }
    public List<Object[]> getContarTechoDanado() {
        Query query = em.createQuery("SELECT a.nombre,COUNT(v.techoDanado.codigo) FROM Vivienda v, Aplica a WHERE v.techoDanado.codigo=a.codigo AND v.construidaPorChilden.codigo=1 GROUP BY a.nombre");
        try {
            List<Object[]> objs = query.getResultList();
            return objs;
        } catch (NoResultException e) {
            return null;
        }
    }
    public List<Object[]> getContarPuertasDanadas() {
        Query query = em.createQuery("SELECT a.nombre,COUNT(v.puertasDanadas.codigo) FROM Vivienda v, Aplica a WHERE v.puertasDanadas.codigo=a.codigo AND v.construidaPorChilden.codigo=1 GROUP BY a.nombre");
        try {
            List<Object[]> objs = query.getResultList();
            return objs;
        } catch (NoResultException e) {
            return null;
        }
    }
    public List<Object[]> getContarVentanasDanadas() {
        Query query = em.createQuery("SELECT a.nombre,COUNT(v.ventanasDanadas.codigo) FROM Vivienda v, Aplica a WHERE v.ventanasDanadas.codigo=a.codigo AND v.construidaPorChilden.codigo=1 GROUP BY a.nombre");
        try {
            List<Object[]> objs = query.getResultList();
            return objs;
        } catch (NoResultException e) {
            return null;
        }
    }
    public List<Object[]> getContarParedesDanadas() {
        Query query = em.createQuery("SELECT a.nombre,COUNT(v.ventanasDanadas.codigo) FROM Vivienda v, Aplica a WHERE v.paredesDanadas.codigo=a.codigo AND v.construidaPorChilden.codigo=1 GROUP BY a.nombre");
        try {
            List<Object[]> objs = query.getResultList();
            return objs;
        } catch (NoResultException e) {
            return null;
        }
    }
    public List<Object[]> getContarPoseeAguaPotable() {
        Query query = em.createQuery("SELECT a.nombre,COUNT(v.aguaPotable.codigo) FROM Vivienda v, Aplica a WHERE v.aguaPotable.codigo=a.codigo GROUP BY a.nombre");
        try {
            List<Object[]> objs = query.getResultList();
            return objs;
        } catch (NoResultException e) {
            return null;
        }
    }
    public List<Object[]> getContarEnergiaElectrica() {
        Query query = em.createQuery("SELECT a.nombre,COUNT(v.energiaElectrica.codigo) FROM Vivienda v, Aplica a WHERE v.energiaElectrica.codigo=a.codigo GROUP BY a.nombre");
        try {
            List<Object[]> objs = query.getResultList();
            return objs;
        } catch (NoResultException e) {
            return null;
        }
    }
    public List<Object[]> getContarServicioDeAseo() {
        Query query = em.createQuery("SELECT a.nombre,COUNT(v.servicioAseo.codigo) FROM Vivienda v, Aplica a WHERE v.servicioAseo.codigo=a.codigo GROUP BY a.nombre");
        try {
            List<Object[]> objs = query.getResultList();
            return objs;
        } catch (NoResultException e) {
            return null;
        }
    }
    public List<Object[]> getContarTipoDeLetrina() {
        Query query = em.createQuery("SELECT t.nombre,COUNT(v.codigoTipoLetrina.codigo) FROM Vivienda v, TipoLetrina t WHERE v.codigoTipoLetrina.codigo=t.codigo GROUP BY t.nombre");
        try {
            List<Object[]> objs = query.getResultList();
            return objs;
        } catch (NoResultException e) {
            return null;
        }
    }
    public List<Object[]> getContarTipoDeTecho() {
        Query query = em.createQuery("SELECT t.nombre,COUNT(v.codigoTecho.codigo) FROM Vivienda v, Techo t WHERE v.codigoTecho.codigo=t.codigo AND v.construidaPorChilden.codigo=2 GROUP BY t.nombre");
        try {
            List<Object[]> objs = query.getResultList();
            return objs;
        } catch (NoResultException e) {
            return null;
        }
    }
    public List<Object[]> getContarMaterialDeVivienda() {
        Query query = em.createQuery("SELECT m.nombre,COUNT(v.codigoMaterial.codigo) FROM Vivienda v, MaterialDeVivienda m WHERE v.codigoMaterial.codigo=m.codigo AND v.construidaPorChilden.codigo=2 GROUP BY m.nombre");
        try {
            List<Object[]> objs = query.getResultList();
            return objs;
        } catch (NoResultException e) {
            return null;
        }
    }

}
