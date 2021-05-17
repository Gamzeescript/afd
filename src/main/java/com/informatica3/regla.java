


public class regla{

    String estadoInicial;
    String alfabeto;
    String estadoFinal; 

    public String getEstadoInicial() {
        return estadoInicial;
    }

    public void setEstadoInicial(String estadoInicial) {
        this.estadoInicial = estadoInicial;
    }

    public String getAlfabeto() {
        return alfabeto;
    }

    public void setAlfabeto(String alfabeto) {
        this.alfabeto = alfabeto;
    }

    public String getEstadoFinal() {
        return estadoFinal;
    }

public void setEstadoFinal(String estadoFinal) {
        this.estadoFinal = estadoFinal;
    }

    

    public regla(String estadoInicial, String alfabeto, String estadoFinal) {
        this.estadoInicial = estadoInicial;
        this.alfabeto = alfabeto;
        this.estadoFinal = estadoFinal;
    }

    @Override
    public String toString() {
        return "regla{" + "estadoInicial=" + estadoInicial + ", alfabeto=" + alfabeto + ", estadoFinal=" + estadoFinal + '}';
    }
    
    
    
    
}
