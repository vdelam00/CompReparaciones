/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import modelo.Ordenador;

/**
 *
 * @author VictorFM
 */
@Stateless
public class OrdenadorFacade extends AbstractFacade<Ordenador> implements OrdenadorFacadeLocal {

    @PersistenceContext(unitName = "my_persistence_unit")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public OrdenadorFacade() {
        super(Ordenador.class);
    }
    
}
