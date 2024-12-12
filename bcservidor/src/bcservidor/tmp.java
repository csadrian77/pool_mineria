package bcservidor;

import conectar.Consultas;
import conectar.Mineros;


public class tmp {
    public static void main(String[] args) {
       Consultas cons= new Consultas();
       Mineros persona= new Mineros();
       persona=cons.getMinero("0001");
       System.out.println("xxxxxxxxx:"+persona.getIdmin());
       System.out.println("xxxxxxxxx:"+persona.isValido() );

        
    }
}
