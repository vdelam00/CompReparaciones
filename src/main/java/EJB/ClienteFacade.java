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
import modelo.Cliente;

/**
 *
 * @author VictorFM
 */
@Stateless
public class ClienteFacade extends AbstractFacade<Cliente> implements ClienteFacadeLocal {

    @PersistenceContext(unitName = "my_persistence_unit")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ClienteFacade() {
        super(Cliente.class);
    }

    /**
     *
     * @param client
     * @return
     */
    @Override
    public Cliente iniciarSesion(Cliente client) {
        Cliente clients = null;
        String consulta = null;
        try{
            consulta = "FROM Cliente c WHERE c.user=:param1 and c.password=:param2";
            Query query = em.createQuery(consulta);
            query.setParameter("param1", client.getUser());
            query.setParameter("param2", client.getPassword());
            List<Cliente> resultado = query.getResultList();
            if(!resultado.isEmpty()){
                clients=resultado.get(0);
            }
        }catch(Exception e){
            throw e;
        }
        return clients;
    }
    
    
}
