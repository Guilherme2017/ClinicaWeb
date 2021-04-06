package controle;

import dao.ReceituarioDao;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import modelo.Receituario;
import util.Util;

@ManagedBean(name="ControleReceituario")
@SessionScoped
public class ControleReceituario implements Serializable
{
    private ReceituarioDao<Receituario> dao;
    private Receituario objeto;
    
    public ControleReceituario()
    {
        dao= new ReceituarioDao<>();
    }

    public String listar()
    {
        return "/privado/receituario/listar?faces-redirect=true";
    }
    
    public String cancelar()
    {
      return "listar";
    }
    
    public String novo()
    {
        setObjeto(new Receituario());
        return "formulario";
    }
    
    public void salvar()
    {
        boolean persistiu;
        
        if(getObjeto().getId() == null)
        {
            persistiu= getDao().persist(getObjeto());
        }
        
        else{
            persistiu= getDao().merge(getObjeto());
        }
        
        if(persistiu)
        {
            Util.mensagemInformacao(getDao().getMensagem());
        }
        
        else{
            Util.mensagemErro(getDao().getMensagem());
        }
    }
    
    public void editar(Integer id)
    {
        try{
           setObjeto((Receituario) getDao().localizar(id));
        } catch(Exception e) {
           Util.mensagemErro(getDao().getMensagem());
        }
    }
    
    public void remover(Integer id)
    {
        setObjeto((Receituario) getDao().localizar(id));
        
        if(getDao().remove(getObjeto()))
        {
            Util.mensagemInformacao(getDao().getMensagem());
        }
        
        else{
            Util.mensagemErro(getDao().getMensagem());
        }
    }
    
    public ReceituarioDao<Receituario> getDao() 
    {
        return dao;
    }

    public void setDao(ReceituarioDao<Receituario> dao) 
    {
        this.dao = dao;
    }

    public Receituario getObjeto() 
    {
        return objeto;
    }

    public void setObjeto(Receituario objeto) 
    {
        this.objeto = objeto;
    }    
}
