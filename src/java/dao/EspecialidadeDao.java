package dao;

import java.io.Serializable;
import modelo.Especialidade;

public class EspecialidadeDao<T> extends DaoGenerico implements Serializable 
{
    public EspecialidadeDao()
    {
        super();
        super.setClassePersistence(Especialidade.class);
        super.setOrdem("id");
    }
}