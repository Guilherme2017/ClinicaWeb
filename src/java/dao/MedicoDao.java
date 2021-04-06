package dao;

import java.io.Serializable;
import modelo.Medico;

public class MedicoDao<T> extends DaoGenerico implements Serializable
{
    public MedicoDao()
    {
        super();
        super.setClassePersistence(Medico.class);
        super.setOrdem("id");
    }
}
