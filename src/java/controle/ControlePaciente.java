package controle;

import dao.PacienteDao;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import modelo.Paciente;
import util.Util;

@ManagedBean(name="ControlePaciente")
@SessionScoped
public class ControlePaciente implements Serializable
{
   private PacienteDao<Paciente> dao;
   private Paciente objeto;
   
    public ControlePaciente()
    {
       dao= new PacienteDao<>();
    }
    
    public String listar()
    {
        return "/privado/paciente/listar?faces-redirect=true";
    }
    
    public String cancelar()
    {
      return "listar";
    }
    
    public String novo()
    {
        setObjeto(new Paciente());
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
           setObjeto((Paciente) getDao().localizar(id));
        } catch(Exception e) {
           Util.mensagemErro(getDao().getMensagem());
        }
    }
    
    public void remover(Integer id)
    {
        setObjeto((Paciente) getDao().localizar(id));
        
        if(getDao().remove(getObjeto()))
        {
            Util.mensagemInformacao(getDao().getMensagem());
        }
        
        else{
            Util.mensagemErro(getDao().getMensagem());
        }
    }

    public PacienteDao<Paciente> getDao() 
    {
        return dao;
    }

    public void setDao(PacienteDao<Paciente> dao) 
    {
        this.dao = dao;
    }

    public Paciente getObjeto() 
    {
        return objeto;
    }

    public void setObjeto(Paciente objeto) 
    {
        this.objeto = objeto;
    }
}