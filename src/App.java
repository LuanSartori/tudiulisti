import controllers.TarefaController;

public class App {
    public static void main(String[] args) throws Exception {

        // Cria uma instância da tela e a inicia
        TarefaController tarefaController = new TarefaController();
        tarefaController.iniciar();

    }
}
