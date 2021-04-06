package dao;

import java.io.Serializable;
import modelo.Exame;

public class ExameDao<T> extends DaoGenerico implements Serializable 
{
    public ExameDao()
    {
        super();
        super.setClassePersistence(Exame.class);
        super.setOrdem("id");
    }
}
