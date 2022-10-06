// Lucas

/*
 * Esta classe fica responsável por gerenciar a maioria das impressões do
 * jogo, em especial a dos blocos de menu com opções.
 */
public class Menu{
    String nome;
    String descricaoEvento;
    String[] opcoes;
    int tamanho;
    
    /*
     * Todo menu necessariamente precisa ter um nome, uma descrição, as opções e
     * um tamanho máximo para a caixa de texto. Este tamanho máximo é fornecido
     * pela classe "Parametros".
     */
    public Menu(String nome, String descricaoEvento, String[] opcoes, int tamanho){
        this.nome = nome;
        this.descricaoEvento = descricaoEvento;
        this.opcoes = opcoes;
        this.tamanho = tamanho;
    }
    
    /*
     * Método principal da classe. Pode ser chamado por qualquer
     * um para que seja impresso determinado menu.
    */
    public void imprimirMenu(int tempo){
        System.out.println();
        imprimeTitulo(tamanho, nome, tempo);
        imprimeTexto(tamanho, descricaoEvento, tempo);
        imprimeDivisoria(tamanho, tempo);
        imprimeOpcoes(tamanho, tempo);
        imprimeDivisoria(tamanho, tempo);
        System.out.println();
    }
    
    // Imprime uma divisória no console, tendo como base um tamanho e um tempo de delay para imprimir cada caracter.
    public static void imprimeDivisoria(int tamanho, int tempo){
        System.out.print("+ ");
        for (int i = 0; i < (tamanho); i++){
            System.out.print("-");
            delay(tempo);
        }
        System.out.println(" +");
    }
    
    /*
     * Imprime um pedaço de texto. Ele valida o tamanho máximo
     * da caixa de diálogo e quebra a linha caso o texto seja
     * muito grande. Também é utilizado o parâmetro "tempo", garantindo
     * um delay após a impressão de cada caracter.
    */
    public static void imprimeTexto(int tamanho, String texto, int tempo){
        int contaCaracteres = 0;
        
        System.out.print("+ ");
        
        /* 
         * Este loop imprime todos os caracteres
         * e quebra a linha caso não caiba.
         */
        for (int i = 0; i < texto.length();){
            if (contaCaracteres < (tamanho)){
                System.out.print(texto.charAt(i));
                contaCaracteres++;
                i++;
                
                delay(tempo);
            }
            else
            {
                contaCaracteres = 0;
                
                System.out.println(" +");
                delay(tempo);
                
                System.out.print("+ ");
                delay(tempo);
            }
        }
        
        preencherLinha(tamanho - contaCaracteres, ' ');
        
        System.out.println(" +");
    }
    
    /*
     * Este método é bem simples. Ele pega a lista
     * de opções do menu e chama o método imprimeTexto
     * para imprimir a opção.
     */
    private void imprimeOpcoes(int tamanho, int tempo){
        for (int i = 0; i < opcoes.length; i++){
            imprimeTexto(tamanho, ((i+1) + ": " + opcoes[i]), tempo);
        }
    }
    
    /* 
     * Semelhante ao método imprimeDivisoria,
     * mas permite a inserção de um texto no meio da linha.
     * A variável "caracteresRestantes" serve apenas para determinar
     * quantos caracteres faltam do final do título ao final da caixa.
    */
    public static void imprimeTitulo(int tamanho, String titulo, int tempo){
        tamanho -= titulo.length();
        int caracteresRestantes = tamanho;
        
        System.out.print("+ ");
        delay(tempo);
        
        for (int i = 0; i < (tamanho / 2); i++){
            caracteresRestantes--;
            System.out.print("-");
            delay(tempo);
        }
        
        System.out.print(titulo);
        delay(tempo);
        
        preencherLinha(caracteresRestantes, '-');
        System.out.println(" +");
    }
    
    /* 
     * Conforme o exercício do cronômetro, este método usa
     * como base um parâmetro chamado "tempo", obrigando
     * o sistema a parar durante estes milisegundos. É utilizado
     * na impressão de cada caracter do jogo, exceto nos títulos.
     */
    public static void delay(int tempo){
        try{
            Thread.sleep(tempo);
        }catch(Exception e){}
    }
    
    /*
     * Esta parte fica responsável por preencher
     * o restante da linha, caso não seja preenchida pelos
     * caracteres do texto. Recebe como parâmetro a quantidade de
     * caracteres a serem inseridos e o caracter desejado.
    */
    public static void preencherLinha(int caracteresRestantes, char caracter){
        for (int i = 0; i < caracteresRestantes; i++){
            System.out.print(caracter);
        }
    }
    
    public void setDescricaoEvento(String descricaoEvento){
        this.descricaoEvento = descricaoEvento;
    }
    
    public void setOpcoes(String[] opcoes){
        this.opcoes = opcoes;
    }
    
    public void setTamanho(int tamanho){
        this.tamanho = tamanho;
    }
    
    public String getDescricaoEvento(){
        return descricaoEvento;
    }
    
    public String[] getOpcoes(){
        return opcoes;
    }
    
    public int getTamanho(){
        return tamanho;
    }
}