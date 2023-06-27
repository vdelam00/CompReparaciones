/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import java.util.List;
import javax.ejb.Local;
import modelo.Cliente;
import modelo.Empleado;
import modelo.Reparacion;

/**
 *
 * @author VictorFM
 */
@Local
public interface ReparacionFacadeLocal {

    void create(Reparacion reparacion);

    void edit(Reparacion reparacion);

    void remove(Reparacion reparacion);

    Reparacion find(Object id);

    List<Reparacion> findAll();

    List<Reparacion> findRange(int[] range);

    int count();
    
    List<Reparacion> findByEmpleado(Empleado empleado);
    
    List<Reparacion> findByCliente(Cliente cliente);
    
}
