/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import controlador.LoginController;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import modelo.Empleado;

/**
 *
 * @author VictorFM
 */
@Stateless
public class EmpleadoFacade extends AbstractFacade<Empleado> implements EmpleadoFacadeLocal{

    @PersistenceContext(unitName = "my_persistence_unit")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EmpleadoFacade() {
        super(Empleado.class);
    }
    
        /**
     *
     * @param emple
     * @return
     */
    @Override
    public Empleado iniciarSesion(Empleado emple) {
        Empleado emp = null;
        String consulta = null;
        try{
            consulta = "FROM Empleado e WHERE e.user=:param1 and e.password=:param2";
            Query query = em.createQuery(consulta);
            query.setParameter("param1", emple.getUser());
            query.setParameter("param2", emple.getPassword());
            List<Empleado> resultado = query.getResultList();
            if(!resultado.isEmpty()){
                emp=resultado.get(0);
            }
        }catch(Exception e){
            throw e;
        }
        return emp;
    }
    
}
