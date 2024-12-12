package bccliente;


import java.math.BigInteger;
import java.util.Date;

public class Bloque {
    private int id;  
    private BigInteger nonce;   
    // la siguiente variable se usa para almacenar la marca de tiempo del bloque en milisegundos.  
    private long timeStamp;  
    //la variable hash contendrá el hash del bloque
    private String hash;  
    //La variable anteriorHash contiene el hash del bloque anterior  
    private String hashPrevio;  
    private String contenido;      
    private boolean valido;
    
    public Bloque(int id, String contenido, String hashPrevio,BigInteger rangoNonceIni ) 
    {   this.id = id;  
        this.contenido = contenido;  
        this.hashPrevio = hashPrevio;  
        this.timeStamp = new Date().getTime();  
        this.nonce=rangoNonceIni;
        this.valido=false;
        generarHash();  
    }  
    /*
    public Bloque(int id, String transaction, String hashPrevio, long timeStamp, BigInteger nonce,String hash) 
    {   this.id = id;  
        this.contenido = contenido;  
        this.hashPrevio = hashPrevio;  
        this.timeStamp = timeStamp;  
        this.nonce=nonce;
        this.hash=hash;
        //generateHash();  
    }  */
    
    public void generarHash() 
    {   String dataToHash = Integer.toString(id) + hashPrevio + Long.toString(timeStamp) + nonce.toString() + contenido.toString();  
        String hashValue = SHA256Helper.generateHash(dataToHash);  
        this.hash = hashValue;  
    }  


    public String getHash()                         {  return this.hash;   }  
    public void setHash(String hash)                {  this.hash = hash;   }  
    
    public String getHashPrevio()                   {  return this.hashPrevio;         }  
    public void setHashPrevio(String previousHash)  {  this.hashPrevio = hashPrevio; } 

    public int getId()                              {  return id;    }
    public void setId(int id)                       {  this.id = id;    }
    
    public BigInteger getNonce()                    {  return nonce;    }
    public void setNonce(BigInteger nonce)          {  this.nonce = nonce;    }

    public long getTimeStamp()                      {  return timeStamp;   }
    public void setTimeStamp(long timeStamp)        {  this.timeStamp = timeStamp;    }

    public boolean isValido()                       {  return valido; }
    public void setValido(boolean valido)           {  this.valido = valido; }
    
    
    
    
    public void incrementarNonce()   { 
        //System.out.println("Nonce:"+nonce);
        //this.nonce++;  
        this.nonce=this.nonce.add(BigInteger.ONE); 
        // System.out.println("Incremented number: " + this.nonce);
    }  
    
    @Override  
    public String toString() 
    {   return this.id+"-"+this.hashPrevio+"-"+this.timeStamp+"-"+this.nonce+"-"+this.contenido;  
    }      
    
}

