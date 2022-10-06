// Lucas

/*
 * Esta classe serve para armazenar
 * parâmetros/regras utilizadas ao longo do jogo.
 */ 

public class Parametros{
    int idadeMaxima;
    int pesoMaximo;
    int tamanhoTextos;
    int tempoImpressaoCaracter;
    
    public Parametros(int idadeMaxima, int pesoMaximo, int tamanhoTextos, int tempoImpressaoCaracter){
        this.idadeMaxima = idadeMaxima;
        this.pesoMaximo = pesoMaximo;
        this.tamanhoTextos = tamanhoTextos;
        this.tempoImpressaoCaracter = tempoImpressaoCaracter;
    }
    
    public void setIdadeMaxima(int idadeMaxima){
        this.idadeMaxima = idadeMaxima;
    }
    
    public void setPesoMaximo(int pesoMaximo){
        this.pesoMaximo = pesoMaximo;
    }
     
    public void setTamanhoTextos(int tamanhoTextos){
        this.tamanhoTextos = tamanhoTextos;
    }
    
    public void setTempoImpressaoCaracter(int tempoImpressaoCaracter){
        this.tempoImpressaoCaracter = tempoImpressaoCaracter;
    }
    
    public int getIdadeMaxmia(){
        return idadeMaxima;
    }
    
    public int getPesoMaximo(){
        return pesoMaximo;
    }
    
    public int getTamanhoTextos(){
        return tamanhoTextos;
    }
    
    public int getTempoImpressaoCaracter(){
        return tempoImpressaoCaracter;
    }
}