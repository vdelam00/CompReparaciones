/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import java.util.List;
import javax.ejb.Local;
import modelo.Ordenador;

/**
 *
 * @author VictorFM
 */
@Local
public interface OrdenadorFacadeLocal {

    void create(Ordenador ordenador);

    void edit(Ordenador ordenador);

    void remove(Ordenador ordenador);

    Ordenador find(Object id);

    List<Ordenador> findAll();

    List<Ordenador> findRange(int[] range);

    int count();
    
}
