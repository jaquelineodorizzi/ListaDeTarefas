import java.util.ArrayList;
import java.util.Scanner;

public class ListaDeTarefas {

    public static void main(String[] args) {
        ListaDeTarefas programa = new ListaDeTarefas();
        programa.iniciar();
    }

    private ArrayList<Tarefa> listaTarefas;
    private Scanner scanner;

    public ListaDeTarefas() {
        this.listaTarefas = new ArrayList<>();
        this.scanner = new Scanner(System.in);
    }

    public void iniciar() {
        boolean sair = false;
        while (!sair) {
            exibirMenu();
            int opcao = obterOpcao();
            switch (opcao) {
                case 1:
                    adicionarTarefa();
                    break;
                case 2:
                    exibirTarefas();
                    break;
                case 3:
                    marcarComoConcluida();
                    break;
                case 4:
                    sair = true;
                    System.out.println("Saindo do programa.");
                    break;
                default:
                    System.out.println("Opção inválida. Por favor, escolha uma opção válida.");
                    break;
            }
        }
        scanner.close();
    }

    private void exibirMenu() {
        System.out.println("\n===== Lista de Tarefas =====");
        System.out.println("Escolha uma opção:");
        System.out.println("1 - Adicionar Tarefa");
        System.out.println("2 - Exibir Tarefas");
        System.out.println("3 - Marcar Tarefa como Concluída");
        System.out.println("4 - Sair");
    }

    private int obterOpcao() {
        System.out.print("Digite sua opção: ");
        int opcao = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer do scanner
        return opcao;
    }

    private void adicionarTarefa() {
        System.out.print("Digite a descrição da nova tarefa: ");
        String descricao = scanner.nextLine();
        Tarefa novaTarefa = new Tarefa(descricao);
        listaTarefas.add(novaTarefa);
        System.out.println("Tarefa adicionada com sucesso!");
    }

    private void exibirTarefas() {
        if (listaTarefas.isEmpty()) {
            System.out.println("A lista de tarefas está vazia.");
        } else {
            System.out.println("\nLista de Tarefas:");
            for (int i = 0; i < listaTarefas.size(); i++) {
                Tarefa tarefa = listaTarefas.get(i);
                System.out.printf("%d. [%s] %s%n", i + 1, (tarefa.isConcluida() ? "X" : " "), tarefa.getDescricao());
            }
        }
    }

    private void marcarComoConcluida() {
        exibirTarefas();
        if (!listaTarefas.isEmpty()) {
            System.out.print("Digite o número da tarefa que deseja marcar como concluída: ");
            int indice = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer do scanner

            if (indice > 0 && indice <= listaTarefas.size()) {
                Tarefa tarefa = listaTarefas.get(indice - 1);
                tarefa.setConcluida(true);
                System.out.println("Tarefa marcada como concluída.");
            } else {
                System.out.println("Número de tarefa inválido.");
            }
        }
    }

    private class Tarefa {
        private String descricao;
        private boolean concluida;

        public Tarefa(String descricao) {
            this.descricao = descricao;
            this.concluida = false; // Inicialmente, a tarefa não está concluída
        }

        public String getDescricao() {
            return descricao;
        }

        public boolean isConcluida() {
            return concluida;
        }

        public void setConcluida(boolean concluida) {
            this.concluida = concluida;
        }
    }
}