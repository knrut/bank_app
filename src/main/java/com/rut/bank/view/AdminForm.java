package com.rut.bank.view;

import com.rut.bank.model.Entity;
import com.rut.bank.repository.Repository;
import com.rut.bank.table.GenericTableModel;
import net.coderazzi.filters.gui.TableFilterHeader;

import javax.swing.*;
import java.awt.*;

public class AdminForm {
    private final JFrame frame;
    private JTable table;
    private JButton buttonLogout;

    public AdminForm(Runnable onLogout) {
        frame = new JFrame("Bank - Admin Panel");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        var panel = new JPanel(new BorderLayout(20,20));
        panel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        table = new JTable();
        JScrollPane scrollPane_1 = new JScrollPane();
        @SuppressWarnings("unused")
        TableFilterHeader tableFilterHeader = new TableFilterHeader(table);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.setAutoCreateRowSorter(true);
        scrollPane_1.setViewportView(table);

        buttonLogout = new JButton("Logout");
        var bottom = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottom.add(buttonLogout);

        panel.add(new JScrollPane(table), BorderLayout.CENTER);
        panel.add(bottom, BorderLayout.SOUTH);

        // callbacks tylko jako Runnable – View nie zna logiki
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override public void windowClosing(java.awt.event.WindowEvent e) {
                if (onLogout != null) onLogout.run();
            }
        });
        buttonLogout.addActionListener(e -> { if (onLogout != null) onLogout.run(); });

        frame.setContentPane(panel);
        frame.setSize(1000, 500);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }

    public JFrame getFrame() { return frame; }
    public JTable getTable() { return table; }

    public void setTableModel(javax.swing.table.TableModel model) {
        table.setModel(model);
    }

}
