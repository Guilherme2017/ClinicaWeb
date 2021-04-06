package controle;

import dao.EspecialidadeDao;
import dao.MedicoDao;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import modelo.Medico;
import util.Util;

@ManagedBean(name="ControleMedico")
@SessionScoped
public class ControleMedico implements Serializable
{
   private MedicoDao<Medico> dao;
   private Medico objeto;
   private EspecialidadeDao daoEspecialidade;
   
    public ControleMedico()
    {
       dao= new MedicoDao<>();
       daoEspecialidade= new EspecialidadeDao();
    }
    
    public String listar()
    {
        return "/privado/medico/listar?faces-redirect=true";
    }
    
    public String cancelar()
    {
      return "listar";
    }
    
    public String novo()
    {
        setObjeto(new Medico());
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
           setObjeto((Medico) getDao().localizar(id));
        } catch(Exception e) {
           Util.mensagemErro(getDao().getMensagem());
        }
    }
    
    public void remover(Integer id)
    {
        setObjeto((Medico) getDao().localizar(id));
        
        if(getDao().remove(getObjeto()))
        {
            Util.mensagemInformacao(getDao().getMensagem());
        }
        
        else{
            Util.mensagemErro(getDao().getMensagem());
        }
    }

    public MedicoDao<Medico> getDao() 
    {
        return dao;
    }

    public void setDao(MedicoDao<Medico> dao) 
    {
        this.dao = dao;
    }

    public Medico getObjeto() 
    {
        return objeto;
    }

    public void setObjeto(Medico objeto)
    {
        this.objeto = objeto;
    }

    public EspecialidadeDao getDaoEspecialidade() 
    {
        return daoEspecialidade;
    }

    public void setDaoEspecialidade(EspecialidadeDao daoEspecialidade) 
    {
        this.daoEspecialidade = daoEspecialidade;
    }   
}
