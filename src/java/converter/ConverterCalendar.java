package converter;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value="ConverterCalendar")
public class ConverterCalendar implements Serializable, Converter 
{   
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    Calendar hoje = Calendar.getInstance();
    
    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) 
    {
        Calendar c = Calendar.getInstance();
        
        try {
          c.setTime(sdf.parse(string));
        } catch (Exception e ){
            return null;
        }
        return c;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) 
    {
        Calendar c = (Calendar) o;
        String str = sdf.format(c.getTime());
        return str;
    }  
}
