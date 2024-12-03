import controllers.TarefaController;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        TarefaController tarefaController = new TarefaController();
        tarefaController.iniciar();
    }
}
