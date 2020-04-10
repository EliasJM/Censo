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
import sv.com.alcaldiamontesanjuan.utils.ListaModelo;

/**
 *
 * @author elias
 */
@Stateless
public class IntegrantesFacade extends AbstractFacade<Integrantes> {

    @PersistenceContext(unitName = "projectCensoAlcaldiaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public IntegrantesFacade() {
        super(Integrantes.class);
    }

   //inicia metodos reportes
    public List<Object[]> getContarProfesionOficioIntegrantes() {
        Query query = em.createQuery("SELECT p.nombre,COUNT(e.sePuedeDesempenar.codigo) FROM Integrantes e, ProfesionOficio p WHERE e.sePuedeDesempenar.codigo=p.codigo GROUP BY p.nombre");
        try {
            List<Object[]> objs=query.getResultList();
            return objs;
        } catch (NoResultException e) {
            return null;
        }
    }    
    public List<Object[]> getContarPersonasDejanDeEstudiar() {
        Query query = em.createQuery("SELECT a.nombre,COUNT(e.desertoDelEstudio.codigo) FROM Integrantes e, Aplica a WHERE e.desertoDelEstudio.codigo=a.codigo AND e.nivelAcademico.codigo<>'5' GROUP BY a.nombre");
        try {
            List<Object[]> objs=query.getResultList();
            return objs;
        } catch (NoResultException e) {
            return null;
        }
    }    
    public  List<Object[]> getPersonasNivelAcademicoIntegrantes(){
        Query query = em.createQuery("SELECT ne.nombre,COUNT(e.nivelAcademico.codigo) FROM Integrantes e, NivelesDeEducacion ne WHERE e.nivelAcademico.codigo= ne.codigo GROUP BY ne.nombre");
        try {
            List<Object[]> objs=query.getResultList();
            return objs;
        } catch (NoResultException e) {
            return null;
        }        
    }
    public  List<Object[]> getPersonasGustariaSeguirEstudiando(){
        //personas que les gustaria seguir estudiando deben de haber dejado de estudiar alguna vez y tambien deben tener un nivel de eduacción diferente de 5        
        Query query = em.createQuery("SELECT a.nombre,COUNT(e.gustariaSeguirEstudiando.codigo) FROM Integrantes e,Aplica a WHERE e.nivelAcademico.codigo!='5' AND e.desertoDelEstudio.codigo='1' AND e.gustariaSeguirEstudiando.codigo=a.codigo  GROUP BY a.nombre");
        try {
            List<Object[]> objs=query.getResultList();
            return objs;
        } catch (NoResultException e) {
            return null;
        }        
    }

    public List<Object[]> getConteoEnfermedadesIntegrantes() {        
        Query query = em.createQuery("SELECT e.nombre as nombre, COUNT(ienf.codigoIntegrante.codigo) as cantidad FROM IntegrantesEnfermos iEnf, EnfermedadesCronicas e WHERE iEnf.discapacidadEnfermedad.codigo=e.codigo GROUP BY e.nombre");
        try {
            //lista=query.getResultList();
            List<Object[]> objs = query.getResultList();
            return objs;
        } catch (NoResultException e) {
            return null;
        }
    }
    public List<Object[]> getConteoPersonasSinIngresosMensuales() {        
        Query query = em.createQuery("SELECT COUNT(i) FROM Integrantes i WHERE i.ingresosMensuales is NULL");
        try {
            //lista=query.getResultList();
            List<Object[]> objs = query.getResultList();
            return objs;
        } catch (NoResultException e) {
            return null;
        }
    }
    public List<Object[]> getConteoPersonasIngresosMensualesMayoresOIgualA300() {        
        Query query = em.createQuery("SELECT COUNT(i) FROM Integrantes i WHERE i.ingresosMensuales>=300");
        try {
            //lista=query.getResultList();
            List<Object[]> objs = query.getResultList();
            return objs;
        } catch (NoResultException e) {
            return null;
        }
    }
    public List<Object[]> getConteoIngresosMenores300yMayoresOIgualA100() {        
        Query query = em.createQuery("SELECT COUNT(i) FROM Integrantes i WHERE i.ingresosMensuales<300 AND i.ingresosMensuales>=100");
        try {            
            List<Object[]> objs = query.getResultList();
            return objs;
        } catch (NoResultException e) {
            return null;
        }
    }
    public List<Object[]> getConteoIngresosMenores100() {        
        Query query = em.createQuery("SELECT COUNT(i) FROM Integrantes i WHERE i.ingresosMensuales<100");
        try {
            //lista=query.getResultList();
            List<Object[]> objs = query.getResultList();
            return objs;
        } catch (NoResultException e) {
            return null;
        }
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
   //fin metodos reportes 
    
    public List<Integrantes> findPorEntrevistado(String codigo) {
        List<Integrantes> lista = new ArrayList<Integrantes>();
        Query query = em.createQuery("SELECT i FROM Integrantes i WHERE i.codigoEntrevistado.id=:codigo");
        query.setParameter("codigo", codigo);
        try {
            lista = query.getResultList();
            return lista;
        } catch (NoResultException e) {
            return lista;
        }
    }

    public List<Object[]> findIngesosPorFamilia(String codigoEntrevistado) {
        List<Object[]> objs = new ArrayList<Object[]>();
        Query query = em.createQuery("SELECT SUM(i.ingresosMensuales) FROM Integrantes i WHERE i.codigoEntrevistado.id=:codigoEntrevistado");
        query.setParameter("codigoEntrevistado", codigoEntrevistado);
        try {
            objs=query.getResultList();
            return objs;
        } catch (NoResultException e) {
            return objs;
        }
    }
    public List<Object[]> findContarMenoresDeEdad(String codigoEntrevistado) {
        List<Object[]> objs = new ArrayList<Object[]>();
        Query query = em.createQuery("SELECT COUNT(i) FROM Integrantes i WHERE i.codigoEntrevistado.id=:codigoEntrevistado AND i.edad<18");
        query.setParameter("codigoEntrevistado", codigoEntrevistado);
        try {
            objs=query.getResultList();
            return objs;
        } catch (NoResultException e) {
            return objs;
        }
    }
    public List<Object[]> findContarMayoresDeEdad(String codigoEntrevistado) {
        List<Object[]> objs = new ArrayList<Object[]>();
        Query query = em.createQuery("SELECT COUNT(i) FROM Integrantes i WHERE i.codigoEntrevistado.id=:codigoEntrevistado AND i.edad>17");
        query.setParameter("codigoEntrevistado", codigoEntrevistado);
        try {
            objs=query.getResultList();
            return objs;
        } catch (NoResultException e) {
            return objs;
        }
    }
    

    public List<Object[]> getListarBusquedaPersonalizadaFindAll() {
        List<Object[]> objs = new ArrayList<Object[]>();
        Query query = em.createQuery("SELECT i.nombre,i.edad,i.ingresosMensuales,e.nombre,can.nombre, cas.nombre,s.nombre FROM Integrantes i, Entrevistado e, Canton can, Caserio cas, Sexo s WHERE e.id=i.codigoEntrevistado.id AND e.codigoCanton.codigo=can.codigo AND e.codigoCaserio.codigo=cas.codigo AND i.sexo.codigo=s.codigo");
        try {
            objs = query.getResultList();
            return objs;
        } catch (NoResultException e) {
            return objs;
        }
    }

    public List<Object[]> getListarBusquedaPersonalizada(String canton, int edadMinima, int edadMaxima) {
        List<Object[]> objs = new ArrayList<Object[]>();
        Query query = em.createQuery("SELECT i.nombre,i.edad,i.ingresosMensuales,e.nombre,can.nombre, cas.nombre,s.nombre FROM Integrantes i, Entrevistado e, Canton can, Caserio cas,Sexo s WHERE e.id=i.codigoEntrevistado.id AND e.codigoCanton.codigo=can.codigo AND e.codigoCaserio.codigo=cas.codigo AND i.sexo.codigo=s.codigo");

        if (edadMaxima == 0 && edadMinima == 0) {
            query = em.createQuery("SELECT i.nombre,i.edad,i.ingresosMensuales,e.nombre,can.nombre, cas.nombre,s.nombre FROM Integrantes i, Entrevistado e, Canton can, Caserio cas,Sexo s WHERE e.id=i.codigoEntrevistado.id AND e.codigoCanton.codigo=:canton AND e.codigoCaserio.codigo=cas.codigo AND i.sexo.codigo=s.codigo");
            query.setParameter("canton", canton);

        } else if (edadMaxima == 0) {
            query = em.createQuery("SELECT i.nombre,i.edad,i.ingresosMensuales,e.nombre,can.nombre, cas.nombre,s.nombre FROM Integrantes i, Entrevistado e, Canton can, Caserio cas,Sexo s WHERE e.id=i.codigoEntrevistado.id AND e.codigoCanton.codigo=:canton AND e.codigoCaserio.codigo=cas.codigo AND i.edad>:edadMinima AND i.sexo.codigo=s.codigo");
            query.setParameter("canton", canton);
            query.setParameter("edadMinima", edadMinima);

        } else if (edadMaxima > edadMinima) {
            if (edadMinima >= 0) {
                query = em.createQuery("SELECT i.nombre,i.edad,i.ingresosMensuales,e.nombre,can.nombre, cas.nombre,s.nombre FROM Integrantes i, Entrevistado e, Canton can, Caserio cas,Sexo s WHERE e.id=i.codigoEntrevistado.id AND e.codigoCanton.codigo=:canton AND e.codigoCaserio.codigo=cas.codigo AND i.edad>=:edadMinima AND i.edad<=:edadMaxima AND i.sexo.codigo=s.codigo");
                query.setParameter("canton", canton);
                query.setParameter("edadMinima", edadMinima);
                query.setParameter("edadMaxima", edadMaxima);

            }
        }
        try {
            objs = query.getResultList();
            return objs;
        } catch (NoResultException e) {
            return objs;
        }
    }

}
