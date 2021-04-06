package dao;

import java.io.Serializable;
import modelo.Receituario;

public class ReceituarioDao<T> extends DaoGenerico implements Serializable 
{
   public ReceituarioDao() 
   {
       super();
       super.setClassePersistence(Receituario.class);
       super.setOrdem("id");
   }
}
