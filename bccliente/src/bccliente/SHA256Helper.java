
package bccliente;

import java.security.MessageDigest;  
  
public class SHA256Helper {  
  
    public static String generateHash(String data) 
    {   try 
        {   // Nosotros obtendremos la instancia de SHA256  
            MessageDigest digest = MessageDigest.getInstance("SHA-256");  
            // al usar la función digest(), obtendremos has como una matriz de bytes unidimensional (dimensional byte array)
            byte[] hash = digest.digest(data.getBytes("UTF-8"));  
            // queremos usar valores hexadecimales, no bytes en nuestro programa  
            // convertirá byte en hexadecimal
            StringBuffer hexadecimalString = new StringBuffer();  
            for (int i = 0; i < hash.length; i++) {   
                String hexadecimal = Integer.toHexString(0xff & hash[i]);  
                if (hexadecimal.length() == 1) hexadecimalString.append('0');  
                hexadecimalString.append(hexadecimal);  
            }  
        return hexadecimalString.toString();  
        }   
        catch (Exception e) {  
        throw new RuntimeException(e);  
        }  
    }  
  
}