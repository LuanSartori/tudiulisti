package controllers;

import models.Tarefa;
import repository.TarefaRepository;
import views.MenuTarefas;
import views.TarefaForm;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class TarefaController {
    private TarefaRepository repository;
    private MenuTarefas menu;

    public TarefaController() {
        repository = new TarefaRepository();
        menu = new MenuTarefas();
    }

    private void inicializar() {
        // Atualizar a tabela com os tarefas existentes
        atualizarTabela();

        // Ações dos botões
        menu.adicionarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adicionarTarefa();
            }
        });

        menu.editarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editarTarefa();
            }
        });

        menu.deletarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deletarTarefa();
            }
        });

        menu.setVisible(true);
    }

    private void atualizarTabela() {
        List<Tarefa> tarefas = repository.obterTodosTarefas();
        menu.atualizarTabela(tarefas);
    }

    private void adicionarTarefa() {
        TarefaForm form = new TarefaForm(menu, "Adicionar Tarefa");
        form.setVisible(true);
        Tarefa novoTarefa = form.getTarefa();
        if (novoTarefa != null) {
            repository.adicionarTarefa(novoTarefa);
            atualizarTabela();
        }
    }

    private void editarTarefa() {
        int selectedId = menu.getSelectedTarefaId();
        if (selectedId != -1) {
            Tarefa tarefa = repository.obterTarefaPorId(selectedId);
            if (tarefa != null) {
                TarefaForm form = new TarefaForm(menu, "Editar Tarefa", tarefa);
                form.setVisible(true);
                Tarefa tarefaAtualizado = form.getTarefa();
                if (tarefaAtualizado != null) {
                    tarefaAtualizado = new Tarefa(
                        selectedId,
                        tarefaAtualizado.getTitulo(),
                        tarefaAtualizado.getDescricao(),
                        tarefaAtualizado.getPrazo(),
                        tarefaAtualizado.getStatus()
                    );
                    repository.atualizarTarefa(tarefaAtualizado);
                    atualizarTabela();
                }
            } else {
                JOptionPane.showMessageDialog(menu, "Tarefa não encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(menu, "Selecione um tarefa para editar.", "Aviso", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void deletarTarefa() {
        int selectedId = menu.getSelectedTarefaId();
        if (selectedId != -1) {
            
            int confirm = JOptionPane.showConfirmDialog(
                menu,
                "Tem certeza que deseja deletar este tarefa?",
                "Confirmar deleção",
                JOptionPane.YES_NO_OPTION
            );
            if (confirm == JOptionPane.YES_OPTION) {
                repository.deletarTarefa(selectedId);
                atualizarTabela();
            }

        } else {
            JOptionPane.showMessageDialog(menu, "Selecione um tarefa para deletar.", "Aviso", JOptionPane.WARNING_MESSAGE);
        }
    }

    public void iniciar() {
        inicializar();
    }
}
