// Lucas

/*
 * Classe do Tamagotchi. Possui os atributos necessários para
 * o funcionamento e alguns métodos de impressão em tela. Se o programa
 * desenvolvido neste trabalho tiver continuidade, transportarei os métodos
 * de impressão para a classe do Menu, centralizando esta tarefa lá.
 */

public class Tamagotchi{
    String nome;
    int idade, idadeMaxima;
    int peso, pesoMaximo;
    int tamanhoTextos, tempo;
    int diasAcordado;
    boolean vivo;
    String[] tamagotchiArte;
    Menu menuSono, menuFome, menuTedio;
    
    public Tamagotchi(boolean vivo){
        this.vivo = vivo;
    }
    
    public Tamagotchi(String nome, int idadeMaxima, int pesoMaximo, int tamanhoTextos, int tempo){
        this.nome = nome;
        idade = 0;
        this.idadeMaxima = idadeMaxima;
        peso = 1;
        this.pesoMaximo = pesoMaximo;
        this.tamanhoTextos = tamanhoTextos;
        this.tempo = tempo;
        diasAcordado = 0;
        vivo = true;
        
        /*
         * Não consegui passar os arrays como parâmetro dos construtores da classe Menu. Portanto,
         * criei variáveis temporárias para armazenar as opções e, em seguida, passei elas para
         * o construtor.
         */
        
        String[] opcoesSono = {"Dormir", "Permanecer acordado"};
        menuSono = new Menu("SONO", nome + " está sonolento! Por favor, trate de dar a ele uma boa noite de descanso.", opcoesSono, tamanhoTextos);
        String[] opcoesFome = {"Comer muito", "Comer pouco", "Não comer"};
        menuFome = new Menu("FOME", nome + " está com fome! Alimente-o com o melhor da gastronomia virtual.", opcoesFome, tamanhoTextos);
        String[] opcoesTedio = {"Correr 10 minutos", "Caminhar 10 minutos"};
        menuTedio = new Menu("TÉDIO", nome + " está entediado... Se ele não sair de casa, vai desenvolver uma série de complexos. Pode ser até que se revolte contra você. Não queira arriscar.", opcoesTedio, tamanhoTextos);
    }
    
    /*
     * Este método imprime as informações atuais do Tamagotchi. Para que o gráfico
     * do jogo ficasse um pouco mais interessante, é utilizado o método "definirBarraProgresso".
     */
    public void imprimeStatus(){
        Menu.imprimeTitulo(tamanhoTextos, "STATUS", tempo);
        Menu.imprimeTexto(tamanhoTextos, "PESO: " + peso + " Kg.", tempo);
        System.out.println("+ "+ definirBarraProgresso(peso, 0, pesoMaximo, tamanhoTextos) + " +");
        Menu.imprimeTexto(tamanhoTextos, "IDADE: " + idade + " dias de vida.", tempo);
        System.out.println("+ "+ definirBarraProgresso(idade, 0, idadeMaxima, tamanhoTextos) + " +");
        Menu.imprimeDivisoria(tamanhoTextos, tempo);
        System.out.println();
    }
    
    /*
     * Este método fica responsável por fazer as álgebras necessárias para definir
     * a barra de progresso. Mais detalhes nos comentários do código.
     */
    private String definirBarraProgresso(int atual, int minimo, int maximo, int tamanho){
        String retorno = "";
        
        /*
         * O número 8 não é arbitrário. Trata-se da parte do percentual e dos colchetes.
         */ 
        tamanho -= 8;
        
        // Porcentagem atual da propriedade, comparada com o máximo.
        double porcentagem = (atual * 100.00) / maximo;
        
        // Determina quantos porcento cada "#" vale.
        double progresso = (100.00 / tamanho);

        // Variável para incrementar no loop abaixo.
        double contadorPorcentagem = 0;
        
        /*
         * O que o loop abaixo faz é ir somando os progressos até chegar na porcentagem
         * atual da propriedade. Cada vez que ele soma, um símbolo de # é adicionado à String.
         * A exceção do 0 é porque, caso a propriedade esteja
         * zerada e o contador também, sistema acabará trazendo um símbolo de #, dando
         * a falsa sensação ao usuário de que há alguma quantia na porcentagem.
         */
        retorno += "[";
        for (int i = 0;  i < (tamanho); i++){
            if ((contadorPorcentagem <= porcentagem) && (porcentagem != 0)){
                retorno += "#";
                contadorPorcentagem += progresso;
            }
            else{
                retorno += "/";
            }
        }
        
        // O if ternário é utilizado para identificar se o percentual é menor que dez, para adição de um zero na frente.
        retorno += ("] " + (porcentagem < 10 ? ("0" + String.format("%.1f", porcentagem)) : String.format("%.1f", porcentagem)) + "%");
        return retorno;
    }
    
    public void imprimirTamagotchi(){
        System.out.println("* * * * * * * *");
        System.out.println("* * * * * * * *");
        System.out.println("* * * * * * * *");
        System.out.println("* * * * * * * *");
        System.out.println("* * * * * * * *");
        System.out.println("* * * * * * * *");
        System.out.println("* * * * * * * *");
        System.out.println("* * * * * * * *");
        System.out.println("* * * * * * * *");
    }
    
    /*
     * Este método imprime o menu do evento atual e recebe
     * o input do usuário. Também são feitas validações para as
     * possíveis exceções que o usuário venha a cometer. O int
     * retornado pelo método é utilizado nos métodos de "Evento".
     */
    public int leitorEvento(Menu menu){
        if (vivo){
            menu.imprimirMenu(tempo);
            int opcao = Teclado.leInt();
            while ((opcao <= 0 ) || opcao > menu.opcoes.length){
                System.out.println();
                Menu.imprimeDivisoria(tamanhoTextos, tempo);
                Menu.imprimeTexto(tamanhoTextos, "OPÇÃO INVÁLIDA!", tempo);
                Menu.imprimeDivisoria(tamanhoTextos, tempo);
                menu.imprimirMenu(tempo);
                opcao = Teclado.leInt();
            }
            return opcao; 
        }
        else{
            return -1;
        }
    }
    
    /*
     * Os métodos "Evento" possuem o parâmetro "opcaoSelecionada".
     * É testando este parâmetro com um switch que o sistema consegue
     * identificar exatamente qual a resposta dar ao usuário.
     * Alguns eventos puxam outros eventos, como é o caso de comer muito
     * ou correr por 10 minutos. No caso da corrida, não é utilizado o
     * método "leitorEvento", pois é garantido que o Tamagotchi comerá muito.
     */
    public void eventoSono(int opcaoSelecionada){
        switch (opcaoSelecionada){
            case 1:
                setIdade(idade + 1);
                setDiasAcordado(0);
                imprimeResposta(nome + " descansou e envelheceu em 1 dia.");
                validarStatus();
                break;
            case 2:
                if (diasAcordado >= 5){
                    setIdade(idade + 1);
                    imprimeResposta(nome + " estava cansado demais, e por isso acabou dormindo de qualquer forma. Ele ficou 1 dia mais velho.");
                    validarStatus();
                }
                else{
                    setDiasAcordado(diasAcordado + 1);
                    imprimeResposta(nome + " passou a noite em claro assistindo Netflix. Ele não envelheceu e está há " + (diasAcordado > 1 ? (diasAcordado + " dias sem dormir.") : (diasAcordado + " dia sem dormir.")));
                }
        }
    }
    
    public void eventoFome(int opcaoSelecionada){
        switch (opcaoSelecionada){
            case 1:
                setPeso(peso + 5);
                imprimeResposta(nome + " devorou toda a comida da casa! Ele engordou 5 Kg e ficou extremamente cansado.");
                if (validarStatus())
                    eventoSono(leitorEvento(menuSono));
                break;
            case 2:
                setPeso(peso + 1);
                imprimeResposta(nome + " comeu um pouco e se saciou. Ele engordou 1 Kg.");
                validarStatus();
                break;
            case 3:
                setPeso(peso - 2);
                imprimeResposta(nome + " passou fome. Ele perdeu 2 Kg no processo.");
                validarStatus();
        }
    }
    
    public void eventoTedio(int opcaoSelecionada){
        switch (opcaoSelecionada){
            case 1:
                setPeso(peso - 4);
                imprimeResposta(nome + " correu muito! Tanto que acabou gastando 4 Kg. Ele está com tanta fome que devorará algo!");
                if (validarStatus())
                    eventoFome(1);
                break;
            case 2:
                setPeso(peso - 1);
                imprimeResposta(nome + " deu uma caminhada por 10 minutos. Ele perdeu 1 Kg e ficou com fome.");
                if (validarStatus())
                    eventoFome(leitorEvento(menuFome));
        }
    }

    /*
     * Este método serve para imprimir as respostas que o sistema
     * dá após alguma ação do usuário.
     */    
    private void imprimeResposta(String texto){
        System.out.println();
        Menu.imprimeTitulo(tamanhoTextos, "RESPOSTA", tempo);
        Menu.imprimeTexto(tamanhoTextos, texto, tempo);
        Menu.imprimeDivisoria(tamanhoTextos, tempo);
        System.out.println();
    }
    
    /*
     * Este método utiliza os atributos atuais do Tamagotchi
     * para validar se ele ainda está vivo. O atributo booleano "vivo"
     * é setado como falso caso ele se encaixe em uma das opções previstas
     * no enunciado. Por medidas de segurança, o método também retorna
     * o booleano do atributo "vivo", de modo que o sistema não puxará
     * o próximo evento caso seja intercalado. Também há uma validação de
     * "vivo" no método "leitorEvento", mas quis ter certeza.
     */
    private boolean validarStatus(){
        if (idade >= idadeMaxima){
            setVivo(false);
            imprimeMorte("Chegados os seus " + idade + " dias de vida, o Tamagotchi " + nome + " faleceu de causas naturais.");
        }
        else if (peso <= 0){
            setVivo(false);
            imprimeMorte("Partindo desta vida de maneira prematura, o Tamagotchi " + nome + " faleceu de desnutrição.");
        }
        else if (peso >= pesoMaximo){
            setVivo(false);
            imprimeMorte("O Tamagotchi " + nome + " faleceu com obesidade mórbida. Seu corpo não aguentou a enorme quantidade de comida ingerida, fazendo com que ele explodisse. Você deveria ter cuidado melhor dele.");
        }
        return vivo;
    }
    
    /*
     * Este método é bem simples, apenas comunica ao usuário que
     * o Tamagotchi faleceu, fornecendo também uma causa.
     */
    private void imprimeMorte(String causa){
        Menu.imprimeTitulo(tamanhoTextos, "MORTE", tempo);
        Menu.imprimeTexto(tamanhoTextos, "Seu Tamagotchi faleceu. " + causa, tempo);
        Menu.imprimeDivisoria(tamanhoTextos, tempo);
    }

    public void setNome(String nome){
        this.nome = nome;
    }
    
    public void setIdade(int idade){
        this.idade = idade;
    }
    
    public void setPeso(int peso){
        this.peso = peso;
    }
    
    public void setVivo(boolean vivo){
        this.vivo = vivo;
    }
    
    public void setDiasAcordado(int diasAcordado){
        this.diasAcordado = diasAcordado;
    }
    
    public String getNome(){
        return nome;
    }
    
    public int getIdade(){
        return idade;
    }
    
    public int getPeso(){
        return peso;
    }
    
    public boolean getVivo(){
        return vivo;
    }
    
    public int getDiasAcordado(){
        return diasAcordado;
    }
}