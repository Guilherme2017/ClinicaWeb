package dao;

import java.io.Serializable;
import modelo.Consulta;

public class ConsultaDao<T> extends DaoGenerico implements Serializable 
{
    public ConsultaDao()
    {
        super();
        super.setClassePersistence(Consulta.class);
        super.setOrdem("id");
    }
}
