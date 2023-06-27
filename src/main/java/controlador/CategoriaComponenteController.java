/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import EJB.CategoriaComponenteFacadeLocal;
import java.io.Serializable;
import java.util.Objects;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import modelo.CategoriaComponente;

/**
 *
 * @author VictorFM
 */
@Named("buttonBasicView")
@ViewScoped
public class CategoriaComponenteController implements Serializable {
    //esto para cada uno
	private CategoriaComponente cat;
	@EJB
	private CategoriaComponenteFacadeLocal categoriacomponenteEJB;

    public CategoriaComponente getCat() {
        return cat;
    }

    public void setCat(CategoriaComponente cat) {
        this.cat = cat;
    }

    public CategoriaComponenteFacadeLocal getCategoriacomponenteEJB() {
        return categoriacomponenteEJB;
    }

    public void setCategoriacomponenteEJB(CategoriaComponenteFacadeLocal categoriacomponenteEJB) {
        this.categoriacomponenteEJB = categoriacomponenteEJB;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + Objects.hashCode(this.cat);
        hash = 71 * hash + Objects.hashCode(this.categoriacomponenteEJB);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CategoriaComponenteController other = (CategoriaComponenteController) obj;
        if (!Objects.equals(this.cat, other.cat)) {
            return false;
        }
        if (!Objects.equals(this.categoriacomponenteEJB, other.categoriacomponenteEJB)) {
            return false;
        }
        return true;
    }

	//Constructor, no necesario pero necesario para resevar memoria, para que se ejecute el primero
	@PostConstruct
	public void init(){
		cat=new CategoriaComponente();
	}

	public void insertarCategoria(){
		try{
			categoriacomponenteEJB.create(cat);
		}catch(Exception e){
			System.out.println("Error insertar categoria");
		}
	}
}
