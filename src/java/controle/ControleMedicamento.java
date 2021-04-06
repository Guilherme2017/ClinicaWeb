package controle;

import dao.MedicamentoDao;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import modelo.Medicamento;
import util.Util;

@ManagedBean(name="ControleMedicamento")
@SessionScoped
public class ControleMedicamento implements Serializable 
{
   private MedicamentoDao<Medicamento> dao;
   private Medicamento objeto;

    public ControleMedicamento()
    {
       dao= new MedicamentoDao<>();
    } 
    
    public String listar()
    {
        return "/privado/medicamento/listar?faces-redirect=true";
    }
    
    public String cancelar()
    {
      return "listar";
    }
    
    public String novo()
    {
        setObjeto(new Medicamento());
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
           setObjeto((Medicamento) getDao().localizar(id));
        } catch(Exception e) {
           Util.mensagemErro(getDao().getMensagem());
        }
    }
    
    public void remover(Integer id)
    {
        setObjeto((Medicamento) getDao().localizar(id));
        
        if(getDao().remove(getObjeto()))
        {
            Util.mensagemInformacao(getDao().getMensagem());
        }
        
        else{
            Util.mensagemErro(getDao().getMensagem());
        }
    }

    public MedicamentoDao<Medicamento> getDao() 
    {
        return dao;
    }

    public void setDao(MedicamentoDao<Medicamento> dao) 
    {
        this.dao = dao;
    }

    public Medicamento getObjeto() 
    {
        return objeto;
    }

    public void setObjeto(Medicamento objeto) 
    {
        this.objeto = objeto;
    }   
}
