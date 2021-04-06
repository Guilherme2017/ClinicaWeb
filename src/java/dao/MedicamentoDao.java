package dao;

import java.io.Serializable;
import modelo.Medicamento;

public class MedicamentoDao<T> extends DaoGenerico implements Serializable 
{
    public MedicamentoDao()
    {
        super();
        super.setClassePersistence(Medicamento.class);
        super.setOrdem("id");
    }
}
