package bccliente;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.json.JSONObject;

public class Bccliente {
    static final String HOST = "localhost";
    static final int PUERTO = 5000;
    static final String IDUNICO = "0001";

interface MiFuncion {
    void ejecutar();
}    

    public class HiloDeServidor implements Runnable
    {   /* Guardamos el socket del servidor */
        DataInputStream flujoEntrada;
     
    
        /* Constructor al que pasamos el socket del servidor */
        public HiloDeServidor(DataInputStream flujo)
        {  flujoEntrada = flujo;
           //this.funcion = funcion;
        }
        /* Código que atenderá los mensajes del servidor de forma asíncrona */
        @Override
        public void run() {
            String respuestxp="finallllll";
            try {
                while (true) {
                    /* Obtenemos el mensaje en UTF8 y lo mostramos */
                    System.out.println("Mensaje cccc: " + flujoEntrada.readUTF());
                    System.out.println("Mensaje: hola mundo: "+2*2 );
                    //String traem=procesaCad(flujoEntrada.readUTF());
                    //System.out.println("Mensaje: hola: "+traem );
                    //funcion.ejecutar();
                }
            } catch(Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }        
    public Bccliente() {
        Socket skCliente = null;
        try {
            String mensaje;
            skCliente = new Socket(HOST, PUERTO);
            DataInputStream flujo = new DataInputStream(skCliente.getInputStream());
            System.out.println("----"+flujo.readUTF());
            /* A partir de aquí delegamos la lectura de mensajes a un segundo plano */
            //(new Thread(new HiloDeServidor(flujo))).start();
            DataOutputStream flujoSalida = new DataOutputStream(skCliente.getOutputStream());
            //envia el id unico
            flujoSalida.writeUTF(IDUNICO);
            //recibo el json de trabajo
            String traem=procesaCad(flujo.readUTF());
            
            System.out.println("----"+traem);
            flujoSalida.writeUTF(traem);
            /*
            DataInputStream flujoEntrada = new DataInputStream(skCliente.getInputStream());
            
            //
            //flujoSalida.writeUTF("vvvvvvvvv"+traem); 
            System.out.println("Mensaje xxxx: " + flujoEntrada.readUTF());
            
            while(true){
                System.out.println("cli: Escribe tu mensaje");
                //flujoSalida.writeUTF(System.console().readLine());
                flujoSalida.writeUTF("cli: adr");
                
                mensaje = flujoEntrada.readUTF();
                System.out.println("Mensaje: " + mensaje);
                
                //
                //flujoSalida.writeUTF("vvvvvvvvv"+traem); 
                
                break;
            } */
        } catch (Exception e) {
            System.out.println(e.getMessage() + " Limite de Peticiones en el Servidor");
        }
        try {
            if (skCliente != null) {
                skCliente.close();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage() + " Limite de Peticiones en el Servidor");
        }
    }    
    
    public static void main(String[] args) {

        new Bccliente();

        
    }
    public String procesaCad(String cadena){
        System.out.println("\nllego:" );
        
        JSONObject jsonObj = new JSONObject(cadena);
        JSONObject respuesta = new JSONObject();
        
        String hashanterior = jsonObj.get("hashanterior").toString();
        String rangoNonceIniStr = jsonObj.get("rangoNonceIni").toString();
        String rangoNonceFinStr = jsonObj.get("rangoNonceFin").toString();
        int nunCeros =Integer.parseInt(jsonObj.get("nunCeros").toString()); 
        String contenidoStr = jsonObj.get("contenido").toString();
        BigInteger rangoNonceIni=new BigInteger(rangoNonceIniStr);
        BigInteger rangoNonceFin=new BigInteger(rangoNonceFinStr);        
        
        CadenaBloques cadenaBloque = new CadenaBloques();  
        cadenaBloque=calcular(hashanterior,rangoNonceIni, rangoNonceFin,nunCeros,contenidoStr);
        
        System.out.println("___________________________________________");
        for(Bloque b:cadenaBloque.getCadenaBloques())  {
            if(b.isValido()==true){
                respuesta.put("nonce", b.getNonce() );
                respuesta.put("timeStamp", b.getTimeStamp() );
                respuesta.put("hash", b.getHash() );
                respuesta.put("idmin",IDUNICO );
            }else{
               // System.out.println("************fallido");
            }
            System.out.println("\nhash:"+b.getHash() );
         }      
        
        return respuesta.toString();

    }
    

    
    public static CadenaBloques calcular(String hashAnterior,BigInteger rangoNonceIni,BigInteger rangoNonceFin,int nunCeros, String contenido ){
        CadenaBloques cadenaBloque = new CadenaBloques();  

        Minar mineria = new Minar();
        Boolean bcad=false;
        
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date();
        long startTime=0;
        startTime = System.nanoTime();
        System.out.println("inicio"+dateFormat.format(date)+"-----"+startTime);
        
            Bloque bloque1 = new Bloque(1,contenido,hashAnterior,rangoNonceIni );  
            bcad=mineria.minando(bloque1, cadenaBloque,rangoNonceIni,rangoNonceFin,nunCeros); 
        
        long endTime=0;
        DateFormat dateFormat1 = new SimpleDateFormat("HH:mm:ss");
        Date date1 = new Date();
        endTime = System.nanoTime();
        System.out.println("fin"+dateFormat1.format(date1)+"-----"+endTime);
        System.out.println("Duración: " + (endTime-startTime)/1e6 + " ms");        
                
        return cadenaBloque;

    }
    
}
