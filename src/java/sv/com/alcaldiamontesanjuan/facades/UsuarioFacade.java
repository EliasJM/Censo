/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.com.alcaldiamontesanjuan.facades;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import sv.com.alcaldiamontesanjuan.entities.Entrevistado;
import sv.com.alcaldiamontesanjuan.entities.Usuario;

/**
 *
 * @author elias
 */
@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> {

    @PersistenceContext(unitName = "projectCensoAlcaldiaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }

    public Usuario consultarDatosIniciarSesion(String contrasena, String email) {
        try {
            Entrevistado n = new Entrevistado();
            Query query = em.createQuery("SELECT u FROM Usuario u WHERE u.contrasena=:contrasena AND u.email=:email");
            query.setParameter("contrasena", contrasena);
            query.setParameter("email", email);
            return (Usuario) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

}
