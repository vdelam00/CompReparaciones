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
import javax.persistence.TypedQuery;
import modelo.Empleado;
import modelo.Reparacion;

/**
 *
 * @author VictorFM
 */
@Stateless
public class ReparacionFacade extends AbstractFacade<Reparacion> implements ReparacionFacadeLocal {

    @PersistenceContext(unitName = "my_persistence_unit")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ReparacionFacade() {
        super(Reparacion.class);
    }
    
    
    public List<Reparacion> findByEmpleado(Empleado empleado) {
        TypedQuery<Reparacion> query = em.createQuery("SELECT r FROM Reparacion r WHERE r.empleado = :empleado", Reparacion.class);
        query.setParameter("empleado", empleado);
        return query.getResultList();
    }
    
}
