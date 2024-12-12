
package bccliente;

import java.util.ArrayList;
import java.util.List;

public class CadenaBloques {
    private List<Bloque> cadenaBloques;  
    public CadenaBloques() {  
        //además, instanciando la lista como una ArrayList
        this.cadenaBloques = new ArrayList<>();  
    }  
    public void agregarBloque(Bloque bloque) {  
        this.cadenaBloques.add(bloque);  
    }  
    public List<Bloque> getCadenaBloques() {  
        return this.cadenaBloques;  
    }  
    public int size() {  
        return this.cadenaBloques.size();  
    }     
      
    @Override  
    public String toString() {  
        String cadena = "";  
        for(Bloque bloque : this.cadenaBloques)  
            cadena+=bloque.toString()+"\n";  
        return cadena;  
    }  
}
