import java.util.Scanner;

/**
 *
 * @author Luiz Merschmann
 */
public class Principal {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try{
            System.out.println("Bem vindo ao aplicativo de simulação bancária!");
            System.out.println("_______________________________________________\n");
            System.out.println("Você deve digitar os parâmetros para a execução da simulação:");
            System.out.println("Digite o número de caixas comuns: (entre 1 e 8):");
            int numeroCaixasComuns = sc.nextInt();
            if(numeroCaixasComuns < 1 || numeroCaixasComuns > 8)
                throw new RuntimeException("Número de caixas comuns fora do range");
            System.out.println("Digite o número de caixas preferênciais: (entre 1 e 8):");
            int numeroCaixasPreferenciais = sc.nextInt();
            if(numeroCaixasPreferenciais < 1 || numeroCaixasPreferenciais > 8)
                throw new RuntimeException("Número de caixas preferenciais fora do range");
            System.out.println("Digite os tempos mínimo e máximo de atendimento de clientes comuns: (o tempo mínimo deve ser estritamente menor que o tempo máximo):");
            int tempoAtendMinClienteComum = sc.nextInt();
            int tempoAtendMaxClienteComum = sc.nextInt();
            if(!(tempoAtendMinClienteComum < tempoAtendMaxClienteComum))
                throw new RuntimeException("O valor do tempo máximo de atendimento de clientes comuns não é menor que do tempo mínimo");
            System.out.println("Digite os tempos mínimo e máximo de atendimento de clientes preferenciais: (o tempo mínimo deve ser estritamente menor que o tempo máximo):");
            int tempoAtendMinClientePreferencial = sc.nextInt();
            int tempoAtendMaxClientePreferencial = sc.nextInt();
            if(!(tempoAtendMinClienteComum < tempoAtendMaxClienteComum))
                throw new RuntimeException("O valor do tempo máximo de atendimento de clientes preferenciais não é menor que do tempo mínimo");
            System.out.println("Digite uma constante de geração de clientes comuns, valores baixos indicam maior probabilidade de geração e valores altos indicam menor probabalidade (entre 5 e 50):");
            int geracaoClientesComuns = sc.nextInt();
            if(5 > geracaoClientesComuns || geracaoClientesComuns > 50)
                throw new RuntimeException("Constante de geração de clientes comuns está fora do range");
            System.out.println("Digite uma constante de geração de clientes preferenciais, valores baixos indicam maior probabilidade de geração e valores altos indicam menor probabalidade (entre 5 e 50):");
            int geracaoClientesPreferenciais = sc.nextInt();
            if(5 > geracaoClientesComuns || geracaoClientesComuns > 50)
                throw new RuntimeException("Constante de geração de clientes preferenciais está fora do range");
            System.out.println("Digite Digite o número de passos da simulação (deve ser positivo):");
            int numeroDePassos = sc.nextInt();
            if(1 > numeroDePassos)
                throw new RuntimeException("O número de passos deve ser positivo");
            Simulacao sim = new Simulacao(geracaoClientesComuns, geracaoClientesPreferenciais, tempoAtendMinClienteComum, tempoAtendMaxClienteComum,
            tempoAtendMinClientePreferencial, tempoAtendMaxClientePreferencial, numeroCaixasComuns, numeroCaixasPreferenciais);
            sim.executarSimulacao(numeroDePassos);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        finally{
            sc.close();
        }
    }
}
