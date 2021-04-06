package dao;

import java.io.Serializable;
import modelo.Paciente;

public class PacienteDao<T> extends DaoGenerico implements Serializable
{
    public PacienteDao()
    {
        super();
        super.setClassePersistence(Paciente.class);
        super.setOrdem("id");
    }
}