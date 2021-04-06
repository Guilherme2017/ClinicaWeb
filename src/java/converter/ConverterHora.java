package converter;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value="ConverterHora")
public class ConverterHora implements Serializable, Converter 
{
    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
    
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
