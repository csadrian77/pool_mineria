package conectar;

import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class Consultas {
    
    public Mineros getMinero(String id){
       DbConnection conex = new DbConnection();
       Mineros persona= new Mineros();
       try {
           
          PreparedStatement consulta = conex.getConnection().prepareStatement("SELECT * FROM mineros where idmin=? ");
          //System.out.println("SELECT * FROM mineros where idmin='"+id+"'");
          consulta.setString(1, id);
          ResultSet res = consulta.executeQuery();
          persona.setValido(false); 
          if(res.next()){
              //System.out.println("pasoooooooo:"+res.getString("idmin"));
              persona.setId(Integer.parseInt(res.getString("id")));
              persona.setIdmin(res.getString("idmin"));
              persona.setTiempo(res.getString("tiempo"));
              persona.setCantidadcli(res.getString("cantidadcli"));
              persona.setNumerogrupo(res.getString("numerogrupo"));
              persona.setProcentaje(res.getString("procentaje"));
              persona.setTotalsobregrp(res.getString("totalsobregrp"));
              persona.setValido(true); 
          }
          res.close();
          consulta.close();
          conex.desconectar();
       } 
       catch (Exception e) {
          System.out.println("error" );
       } 
       return persona;
    }
    
}
