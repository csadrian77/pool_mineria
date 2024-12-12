
package bccliente;

import java.math.BigInteger;

public class Minar {
    private double recompensa;
    public Boolean minando(Bloque bloque, CadenaBloques cadenaBloques ,BigInteger rangoNonceIni,BigInteger rangoNonceFin,int nunCeros) 
    {   
       Boolean resultado=false;
       BigInteger i = rangoNonceIni;
     //while((i.compareTo(rangoNonceFin) <= 0)  || buscarCeroHash(bloque,nunCeros) ) {
        while( buscarCeroHash(bloque,nunCeros) ) {
            //System.out.println(bloque.getNonce());    // >
            bloque.generarHash();    
            if(bloque.getNonce().compareTo(rangoNonceFin)< 0) { }
            else {   resultado= false; bloque.setValido(false);  break;  }
            bloque.incrementarNonce();    
            
            //i = i.add(BigInteger.ONE);
            bloque.setValido(true);
            resultado= true;
        }
        
        System.out.println("___nonce:"+bloque.getNonce()+"___acaba de minar:"+bloque+"____Hash:"+bloque.getHash());
        //agregar el bloque a la cadena de bloques 
        cadenaBloques.agregarBloque(bloque);  
        //calculando la recompensa
        //recompensa+=Constantes.MINAR_RECOMPENZA;  
        return resultado;
    }      
    //Entonces, los mineros generarán valores hash hasta que encuentren el hash correcto.
    //que coincide con la variable DIFFICULTY declarada en la class Constant   
    public boolean  buscarCeroHash(Bloque bloque,int nunCeros) 
    {   String ceroIzquierda = new String(new char[nunCeros]).replace('\0', '0');  
        return !bloque.getHash().substring (0, nunCeros).equals (ceroIzquierda);  
    }  

}
