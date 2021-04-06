package controle;

import dao.EspecialidadeDao;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import modelo.Especialidade;
import util.Util;

@ManagedBean(name="ControleEspecialidade")
@SessionScoped
public class ControleEspecialidade implements Serializable
{
   private EspecialidadeDao<Especialidade> dao;
   private Especialidade objeto;
   
    public ControleEspecialidade()
    {
       dao= new EspecialidadeDao<>();
    }
    
    public String listar()
    {
        return "/privado/especialidade/listar?faces-redirect=true";
    }
    
    public String cancelar()
    {
      return "listar";
    }
    
    public String novo()
    {
        setObjeto(new Especialidade());
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
           setObjeto((Especialidade) getDao().localizar(id));
        } catch(Exception e) {
           Util.mensagemErro(getDao().getMensagem());
        }
    }
    
    public void remover(Integer id)
    {
        setObjeto((Especialidade) getDao().localizar(id));
        
        if(getDao().remove(getObjeto()))
        {
            Util.mensagemInformacao(getDao().getMensagem());
        }
        
        else{
            Util.mensagemErro(getDao().getMensagem());
        }
    }

    public EspecialidadeDao<Especialidade> getDao() 
    {
        return dao;
    }

    public void setDao(EspecialidadeDao<Especialidade> dao) 
    {
        this.dao = dao;
    }

    public Especialidade getObjeto() 
    {
        return objeto;
    }

    public void setObjeto(Especialidade objeto) 
    {
        this.objeto = objeto;
    }  
}
