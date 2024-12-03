package views;

import models.Tarefa;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TarefaForm extends JDialog {
    private JTextField tituloField, descricaoField, prazoField;
    private JCheckBox statusField;
    private JButton salvarButton, cancelarButton;

    private Tarefa tarefa;
    private boolean isEditMode;

    public TarefaForm(Frame parent, String title) {
        super(parent, title, true);
        this.isEditMode = false;
        initializeComponents();
    }

    public TarefaForm(Frame parent, String title, Tarefa tarefa) {
        super(parent, title, true);
        this.tarefa = tarefa;
        this.isEditMode = true;
        initializeComponents();
        preencherCampos();
    }

    private void initializeComponents() {
        // Configurações da janela
        tituloField = new JTextField(20);
        descricaoField = new JTextField(20);
        prazoField = new JTextField(20);
        statusField = new JCheckBox("Concluída");
        salvarButton = new JButton("Salvar");
        cancelarButton = new JButton("Cancelar");

        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));
        panel.add(new JLabel("Titulo:"));
        panel.add(tituloField);
        panel.add(new JLabel("Descrição:"));
        panel.add(descricaoField);
        panel.add(new JLabel("Prazo:"));
        panel.add(prazoField);
        if (isEditMode) {
            panel.add(new JLabel("Concluída:"));
            panel.add(statusField);
        }
        panel.add(salvarButton);
        panel.add(cancelarButton);

        // Adicionando uma margem de 10 pixels nas bordas laterais e verticais
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Funções dos botões
        salvarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validarCampos()) {
                    if (isEditMode) {
                        atualizarTarefa();
                    } else {
                        adicionarTarefa();
                    }
                    dispose();
                }
            }
        });

        cancelarButton.addActionListener(e -> dispose());

        // Adicionado tudo a janela
        this.add(panel);
        this.pack();
        this.setLocationRelativeTo(getParent());
    }

    private void preencherCampos() {
        if (tarefa != null) {
            tituloField.setText(tarefa.getTitulo());
            descricaoField.setText(tarefa.getDescricao());
            prazoField.setText(tarefa.getPrazo());
            if (isEditMode) { 
                statusField.setSelected(tarefa.getStatus());
            }
        }
    }

    private boolean validarCampos() {
        if (tituloField.getText().trim().isEmpty() ||
            descricaoField.getText().trim().isEmpty() ||
            prazoField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(
                this,
                "Todos os campos são obrigatórios",
                "Erro", JOptionPane.ERROR_MESSAGE
            );
            return false;
        }
        return true;
    }

    private void adicionarTarefa() {
        tarefa = new Tarefa(
            tituloField.getText().trim(),
            descricaoField.getText().trim(),
            prazoField.getText().trim(),
            statusField.isSelected()
        );
    }

    private void atualizarTarefa() {
        if (tarefa != null) {
            tarefa.setTitulo(tituloField.getText().trim());
            tarefa.setDescricao(descricaoField.getText().trim());
            tarefa.setPrazo(prazoField.getText().trim());
            tarefa.setStatus(statusField.isSelected());
        }
    }

    public Tarefa getTarefa() {
        return tarefa;
    }
}
