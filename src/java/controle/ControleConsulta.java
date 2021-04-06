package controle;

import dao.ConsultaDao;
import dao.ExameDao;
import dao.MedicoDao;
import dao.PacienteDao;
import dao.ReceituarioDao;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import modelo.Consulta;
import util.Util;

@ManagedBean(name="ControleConsulta")
@ViewScoped
public class ControleConsulta implements Serializable
{
    private ConsultaDao<Consulta> dao;
    private Consulta objeto;
    private MedicoDao daoMedico;
    private PacienteDao daoPaciente;
    private ExameDao daoExame;
    private ReceituarioDao daoReceituario;
   
    public ControleConsulta()
    {
        dao= new ConsultaDao<>();
        daoMedico= new MedicoDao();
        daoPaciente= new PacienteDao();
        daoExame= new ExameDao();
        daoReceituario= new ReceituarioDao();
    }

    public String listar()
    {
        return "/privado/consulta/listar?faces-redirect=true";
    }
    
    public String cancelar()
    {
      return "listar";
    }
    
    public String novo()
    {
        setObjeto(new Consulta());
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
           setObjeto((Consulta) getDao().localizar(id));
        } catch(Exception e) {
           Util.mensagemErro(getDao().getMensagem());
        }
    }
    
    public void remover(Integer id)
    {
        setObjeto((Consulta) getDao().localizar(id));
        
        if(getDao().remove(getObjeto()))
        {
            Util.mensagemInformacao(getDao().getMensagem());
        }
        
        else{
            Util.mensagemErro(getDao().getMensagem());
        }
    }
    
    public ConsultaDao<Consulta> getDao() 
    {
        return dao;
    }

    public void setDao(ConsultaDao<Consulta> dao) 
    {
        this.dao = dao;
    }

    public Consulta getObjeto() 
    {
        return objeto;
    }

    public void setObjeto(Consulta objeto) 
    {
        this.objeto = objeto;
    }

    public MedicoDao getDaoMedico() 
    {
        return daoMedico;
    }

    public void setDaoMedico(MedicoDao daoMedico) 
    {
        this.daoMedico = daoMedico;
    }

    public PacienteDao getDaoPaciente() 
    {
        return daoPaciente;
    }

    public void setDaoPaciente(PacienteDao daoPaciente) 
    {
        this.daoPaciente = daoPaciente;
    }
    
    public ExameDao getDaoExame() 
    {
        return daoExame;
    }

    public void setDaoExame(ExameDao daoExame) 
    {
        this.daoExame = daoExame;
    }
    
    public ReceituarioDao getDaoReceituario() 
    {
        return daoReceituario;
    }

    public void setDaoReceituario(ReceituarioDao daoReceituario) 
    {
        this.daoReceituario = daoReceituario;
    }   
}
