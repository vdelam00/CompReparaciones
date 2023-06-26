/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import modelo.Administrador;

/**
 *
 * @author VictorFM
 */
@Stateless
public class AdministradorFacade extends AbstractFacade<Administrador> implements AdministradorFacadeLocal {

    @PersistenceContext(unitName = "my_persistence_unit")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AdministradorFacade() {
        super(Administrador.class);
    }
    
        /**
     *
     * @param admin
     * @return
     */
    @Override
    public Administrador iniciarSesion(Administrador admin) {
        Administrador admini = null;
        String consulta = null;
        try{
            consulta = "FROM Administrador a WHERE a.user=:param1 and a.password=:param2";
            Query query = em.createQuery(consulta);
            query.setParameter("param1", admin.getUser());
            query.setParameter("param2", admin.getPassword());
            List<Administrador> resultado = query.getResultList();
            if(!resultado.isEmpty()){
                admini=resultado.get(0);
            }
        }catch(Exception e){
            throw e;
        }
        return admini;
    }
}
