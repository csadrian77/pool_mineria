package conectar;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbConnection {
     
   static String bd = "cadenasb";
   static String login = "root";
   static String password = "server";
   static String url = "jdbc:mysql://localhost/"+bd;

   Connection connection = null;

   /** Constructor de DbConnection */
   public DbConnection() {
      try{
         //obtenemos el driver de para mysql
         Class.forName("com.mysql.jdbc.Driver");
         //obtenemos la conexión
         connection = DriverManager.getConnection(url,login,password);

         if (connection!=null){
           // System.out.println("Conexión a base de datos "+bd+" OK\n");
         }
      }catch(Exception e){
         System.out.println(e);
      }
   }
   /**Permite retornar la conexión*/
   public Connection getConnection(){
      return connection;
   }

   public void desconectar(){
      connection = null;
   }
    
}
