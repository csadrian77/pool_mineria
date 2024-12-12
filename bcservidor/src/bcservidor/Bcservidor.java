package bcservidor;

import conectar.Consultas;
import conectar.Mineros;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import org.json.JSONObject;


public class Bcservidor {
    static final int PUERTO = 5000;
    static final String HASHANTERIOR ="adrgggg" ;
    static final String NUMCEROS = "1";
    static final String CONTENIDO = "contenidooooo";

    public class HiloDeCliente implements Runnable
    {
        /* Guardamos el socket del cliente y el número de cliente */
        Socket skCliente;
        int numCli;
        /* Constructor al que pasamos el socket del cliente */
        public HiloDeCliente(Socket skEnviado, int num)
        {   skCliente = skEnviado;
            numCli = num;
            /* Mostramos información del cliente */
            System.out.println("Sirvo al cliente: " + num);
            System.out.println("En la dirección: " + skEnviado.getRemoteSocketAddress());
            System.out.println("En el puerto: " + skEnviado.getPort());
        }
        /* Código que atenderá al cliente hasta que cierre la conexión */
        public void run() {
            try {
                /* Mensajes recibidos */
                String mensaje;
                Mineros perx= new Mineros();
                /* Obtenemos un DataOutputStream del socket y escribimos en él un saludo */
                DataOutputStream flujoSalida = new DataOutputStream(skCliente.getOutputStream());
                flujoSalida.writeUTF("Srv: " + numCli);

                /* Obtenemos un DataInputStream para leer lo que recibamos del cliente y mostrarlo en pantalla */
                DataInputStream flujoEntrada = new DataInputStream(skCliente.getInputStream());
                
                mensaje = flujoEntrada.readUTF();
System.out.println("srv envio: "+mensaje);
                perx=validaId(mensaje);
                if(perx.isValido()==true){
                    JSONObject jo = new JSONObject();
                    jo.put("hashanterior", perx.getHashanterior() );
                    jo.put("rangoNonceIni", perx.getRangoNonceIni() );
                    jo.put("rangoNonceFin",  perx.getRangoNonceFin() );
                    jo.put("nunCeros", perx.getNunCeros() ); 
                    jo.put("contenido", perx.getContenido());
System.out.println("srv envio: "+jo);
                    flujoSalida.writeUTF(""+jo);
                    
                    while (true) {
                        //Obtenemos el mensaje en UTF8 
                        mensaje = flujoEntrada.readUTF();
                        System.out.println("Mensaje [" + numCli + "]: " + mensaje);
                        // Hacemos eco, sólo por depurar 
                        flujoSalida.writeUTF("Recibido mensaje: " + mensaje);
                    }
                }
                else{
                    
                }
            } catch(Exception e) {
                System.out.println(e.getMessage());
            }
            /* Cerramos el socket */
            try {
                skCliente.close();
            } catch(Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
    public Mineros validaId(String idmin){
        Consultas cons= new Consultas();
        Mineros persona= new Mineros();
        persona=cons.getMinero(idmin);
        persona.setHashanterior(HASHANTERIOR);
        persona.setRangoNonceIni("0");
        persona.setRangoNonceFin("100");
        persona.setNunCeros(NUMCEROS);
        persona.setContenido(CONTENIDO);
        return persona;
    }     

    public static void main(String[] args) {
        // TODO code application logic here
        new Bcservidor();
    }
    public Bcservidor() {
        try {
            ServerSocket skServidor = new ServerSocket(PUERTO);
            System.out.println("srv puerto: " + PUERTO);
            int numCli = 0;
            String nom = " ";

            /* No nos andamos con tonterías, aceptamos conexiones hasta finalizar la ejecución */
            while (true) {
                /* Quedamos bloqueados en este punto hasta que llegue una nueva conexión */
                Socket socket = skServidor.accept();
                /* Pasamos la información a un nuevo hilo para que atienda al cliente */
                (new Thread(new HiloDeCliente(socket, ++numCli))).start();
            }
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }    
    
}
