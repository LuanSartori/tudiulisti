package views;

import models.Tarefa;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class MenuTarefas extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;
    public JButton adicionarButton, editarButton, deletarButton;

    public MenuTarefas() {
        super("Tu-Diu Listi");
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        // DEFINIÇÃO DA TABELA
        String[] colunas = {"ID", "Titulo", "Descrição", "Prazo", "Status"};
        tableModel = new DefaultTableModel(colunas, 0);
        table = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(table); // Coloca uma scroll bar na tabela
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // DEFINIÇÃO DOS BOTÕES
        adicionarButton = new JButton("Adicionar");
        editarButton = new JButton("Editar");
        deletarButton = new JButton("Deletar");

        JPanel panel = new JPanel(new GridLayout(1, 3, 20, 20));
        panel.add(adicionarButton);
        panel.add(editarButton);
        panel.add(deletarButton);
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // ORGANIZANDO TUDO
        this.setLayout(new BorderLayout());
        this.add(scrollPane, BorderLayout.NORTH);
        this.add(panel, BorderLayout.SOUTH);
        this.pack();

        // this.setSize(1000, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
    }

    public void atualizarTabela(List<Tarefa> tarefas) {
        tableModel.setRowCount(0); // Limpa a tabela
        for (Tarefa tarefa : tarefas) {
            Object[] row = {
                tarefa.getId(),
                tarefa.getTitulo(),
                tarefa.getDescricao(),
                tarefa.getPrazo(),
                tarefa.getStatus()
            };
            tableModel.addRow(row);
        }
    }

    public int getSelectedTarefaId() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            return (int) tableModel.getValueAt(selectedRow, 0);
        }
        return -1;
    }
}
