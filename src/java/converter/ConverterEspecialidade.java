package converter;

import java.io.Serializable;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.persistence.EntityManager;
import jpa.EntityManagerUtil;
import modelo.Especialidade;

@FacesConverter(value="ConverterEspecialidade")
public class ConverterEspecialidade implements Serializable, Converter 
{
    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) 
    {
        if(string == null || string.equals("Selecione um registro"))
        {
           return null;
        }
        
        try{
            EntityManager em= EntityManagerUtil.getEntityManager();
            return em.find(Especialidade.class, Integer.parseInt(string));
        } catch(Exception e){
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) 
    {
        if(o == null)
        {
           return null;
        }
        
        Especialidade obj= (Especialidade) o;
        return obj.getId().toString();
    }   
}
