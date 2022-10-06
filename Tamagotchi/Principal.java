// Lucas

/*
 * Como o nome já diz, esta classe fica responsável por armazenar
 * executar os métodos utilizados no jogo. Nas variáveis "parametros"
 * estão armazenadas as "regras do jogo", e na variável "tamagotchi" 
 * está armazenado o Tamagotchi a ser utilizado pelo usuário.
 */
 
public class Principal{
    public static void main (String[] args){
        int diaAtual = 1;
        //O jogo começa com a  definição dos parâmetros iniciais.
        Parametros parametros = new Parametros(15, 20, 45, 5);
        
        //Criação do Tamagotchi
        Tamagotchi tamagotchi;//= new Tamagotchi(true);
        
        boolean jogando = true;
        
        while (jogando){
            
            tamagotchi = criadorDeTamagotchi(parametros);
            
            while (tamagotchi.vivo){
                System.out.println("\u000C");
                Menu.imprimeTitulo(parametros.getTamanhoTextos(), tamagotchi.getNome() + ", dia " + diaAtual, parametros.getTempoImpressaoCaracter());
                //tamagotchi.imprimirTamagotchi();
                tamagotchi.imprimeStatus();
                
                decideEvento(tamagotchi);
                
                diaAtual = encerraDia(parametros.getTamanhoTextos(), parametros.getTempoImpressaoCaracter(), diaAtual);
            }
            
            jogando = perguntaJogarNovamente(parametros.getTamanhoTextos(), parametros.getTempoImpressaoCaracter());
        }

    }
    
    /*
     * Este método fica responsável por decidir qual evento deve ser
     * executado. Em seguida, ele o executa.
     */
    private static void decideEvento(Tamagotchi tamagotchi){
        double definirEvento = Math.random() * 100;
        
        if (definirEvento < 33)
            tamagotchi.eventoSono(tamagotchi.leitorEvento(tamagotchi.menuSono));
        else if (definirEvento > 66)
            tamagotchi.eventoFome(tamagotchi.leitorEvento(tamagotchi.menuFome));
        else
            tamagotchi.eventoTedio(tamagotchi.leitorEvento(tamagotchi.menuTedio));
    }
    
    /*
     * Este método serve para encerrar o dia, incrementando em 1 a variável
     * "diaAtual" e finalizando com o texdto "Digite qualquer coisa para continuar".
     */
    private static int encerraDia(int tamanho, int tempo, int diaAtual){
        Menu.imprimeTitulo(tamanho, "FIM DO DIA", tempo);
        Menu.imprimeTexto(tamanho, "Digite qualquer coisa para continuar.", tempo);
        Menu.imprimeDivisoria(tamanho, tempo);
        Teclado.leString();
        return (diaAtual + 1);
    }
    
    /*
     * Pede para que o usuário crie um Tamagotchi, definindo seus atributos.
     */
    public static Tamagotchi criadorDeTamagotchi(Parametros parametros){
        System.out.println("\u000C");
        int tamanho = parametros.getTamanhoTextos();
        int tempo = parametros.getTempoImpressaoCaracter();
        String nome = "";
        
        Menu.imprimeTitulo(tamanho, "CRIADOR DE TAMAGOTCHI", tempo);
        Menu.imprimeTexto(tamanho, "Bem-vindo ao criador de Tamagotchi!", tempo);
        Menu.imprimeDivisoria(tamanho, tempo);
        Menu.imprimeTexto(tamanho, "Digite qualquer coisa para continuar.", tempo);
        Menu.imprimeDivisoria(tamanho, tempo);
        System.out.println();
        Teclado.leString();
        
        boolean inputCorreto = false;
        while (inputCorreto == false){
            System.out.println("\u000C");
            Menu.imprimeDivisoria(tamanho, tempo);
            Menu.imprimeTexto(tamanho, "Digite o nome do seu Tamagotchi.", tempo);
            Menu.imprimeDivisoria(tamanho, tempo);
            nome = Teclado.leString();
            if (nome.length() < 1){
                Menu.imprimeDivisoria(tamanho, tempo);
                Menu.imprimeTexto(tamanho, "OPÇÃO INVÁLIDA!", tempo);
                Menu.imprimeDivisoria(tamanho, tempo);
                Teclado.leString();
            }
            else{
                inputCorreto = true;
            }
        }

        return new Tamagotchi(nome, parametros.getIdadeMaxmia(), parametros.getPesoMaximo(), tamanho, tempo);
    }
    
    /*
     * Cria um menu no final do jogo para perguntar ao usuário se ele
     * deseja criar um novo Tamagotchi. Caso ele digite 1, o jogo começa
     * novamente. Caso ele digite 2, o jogo é encerrado.
     */
    private static boolean perguntaJogarNovamente (int tamanho, int tempo){
        String[] opcoesMenuJogando = {"Sim", "Não"};
        Menu menuJogando = new Menu("JOGAR NOVAMENTE", "Seu Tamagotchi morreu. Gostaria de jogar novamente?", opcoesMenuJogando, tamanho);
        
        System.out.println("\u000C");
        menuJogando.imprimirMenu(tempo);
        int opcao = Teclado.leInt();
        while ((opcao <= 0 ) || opcao > menuJogando.opcoes.length){
            System.out.println("\u000C");
            System.out.println();
            Menu.imprimeDivisoria(tamanho, tempo);
            Menu.imprimeTexto(tamanho, "OPÇÃO INVÁLIDA!", tempo);
            Menu.imprimeDivisoria(tamanho, tempo);
            menuJogando.imprimirMenu(tempo);
            opcao = Teclado.leInt();
        }
        
        switch (opcao){
            case 1:
                return true;
            case 2:
                return false;
            default:
                return false;
        }
    }
}