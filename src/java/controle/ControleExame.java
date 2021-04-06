package controle;

import dao.ExameDao;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import modelo.Exame;
import util.Util;

@ManagedBean(name="ControleExame")
@SessionScoped
public class ControleExame implements Serializable 
{
    private ExameDao<Exame> dao;
    private Exame objeto;
    
    public ControleExame()
    {
        dao= new ExameDao<>();
    }

    public String listar()
    {
        return "/privado/exame/listar?faces-redirect=true";
    }
    
    public String cancelar()
    {
      return "listar";
    }
    
    public String novo()
    {
        setObjeto(new Exame());
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
           setObjeto((Exame) getDao().localizar(id));
        } catch(Exception e) {
           Util.mensagemErro(getDao().getMensagem());
        }
    }
    
    public void remover(Integer id)
    {
        setObjeto((Exame) getDao().localizar(id));
        
        if(getDao().remove(getObjeto()))
        {
            Util.mensagemInformacao(getDao().getMensagem());
        }
        
        else{
            Util.mensagemErro(getDao().getMensagem());
        }
    }
    
    public ExameDao<Exame> getDao() 
    {
        return dao;
    }

    public void setDao(ExameDao<Exame> dao) 
    {
        this.dao = dao;
    }

    public Exame getObjeto() 
    {
        return objeto;
    }

    public void setObjeto(Exame objeto) 
    {
        this.objeto = objeto;
    }    
}
