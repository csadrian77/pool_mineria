
package conectar;

public class Mineros {
private int id;
private String idmin;
private String tiempo;
private String cantidadcli;
private String numerogrupo;
private String procentaje;
private String totalsobregrp; 
private boolean valido;
private String hashanterior;
private String rangoNonceIni; 
private String rangoNonceFin; 
private String nunCeros;
private String contenido;

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public String getHashanterior() {
        return hashanterior;
    }

    public void setHashanterior(String hashanterior) {
        this.hashanterior = hashanterior;
    }

    public String getRangoNonceIni() {
        return rangoNonceIni;
    }

    public void setRangoNonceIni(String rangoNonceIni) {
        this.rangoNonceIni = rangoNonceIni;
    }

    public String getRangoNonceFin() {
        return rangoNonceFin;
    }

    public void setRangoNonceFin(String rangoNonceFin) {
        this.rangoNonceFin = rangoNonceFin;
    }

    public String getNunCeros() {
        return nunCeros;
    }

    public void setNunCeros(String nunCeros) {
        this.nunCeros = nunCeros;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdmin() {
        return idmin;
    }

    public void setIdmin(String idmin) {
        this.idmin = idmin;
    }

    public String getTiempo() {
        return tiempo;
    }

    public void setTiempo(String tiempo) {
        this.tiempo = tiempo;
    }

    public String getCantidadcli() {
        return cantidadcli;
    }

    public void setCantidadcli(String cantidadcli) {
        this.cantidadcli = cantidadcli;
    }

    public String getNumerogrupo() {
        return numerogrupo;
    }

    public void setNumerogrupo(String numerogrupo) {
        this.numerogrupo = numerogrupo;
    }

    public String getProcentaje() {
        return procentaje;
    }

    public void setProcentaje(String procentaje) {
        this.procentaje = procentaje;
    }

    public String getTotalsobregrp() {
        return totalsobregrp;
    }

    public void setTotalsobregrp(String totalsobregrp) {
        this.totalsobregrp = totalsobregrp;
    }

    public boolean isValido() {
        return valido;
    }

    public void setValido(boolean valido) {
        this.valido = valido;
    }

  
}
