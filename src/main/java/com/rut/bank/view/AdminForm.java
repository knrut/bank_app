package com.rut.bank.view;

import net.coderazzi.filters.gui.TableFilterHeader;

import javax.swing.*;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class AdminForm {
    private final JFrame frame;
    private final JTable table;
    private final JButton buttonShowAccounts;
    private final JButton buttonShowTransactions;
    private final JButton buttonRefresh;
    private final JButton buttonLogout;

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


        buttonShowAccounts = new JButton("Show Accounts");
        buttonShowTransactions = new JButton("Show Transactions");

        var topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.add(buttonShowAccounts);
        topPanel.add(buttonShowTransactions);

        buttonRefresh = new JButton("Refresh");
        buttonLogout = new JButton("Logout");

        var bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.add(buttonRefresh);
        bottomPanel.add(buttonLogout);

        panel.add(topPanel, BorderLayout.NORTH);
        panel.add(new JScrollPane(table), BorderLayout.CENTER);
        panel.add(bottomPanel, BorderLayout.SOUTH);

        // callbacks tylko jako Runnable – View nie zna logiki
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override public void windowClosing(java.awt.event.WindowEvent e) {
                if (onLogout != null) onLogout.run();
            }
        });
        buttonLogout.addActionListener(e -> { if (onLogout != null) onLogout.run(); });

        frame.setContentPane(panel);
        frame.setSize(1100, 600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }

    public JFrame getFrame() { return frame; }
    public JTable getTable() { return table; }
    public void setTableModel(javax.swing.table.TableModel model) { table.setModel(model); }
    public void onShowAccount(Runnable r ) { buttonShowAccounts.addActionListener(e -> r.run());}
    public void onShowTransactions(Runnable r) { buttonShowTransactions.addActionListener(e -> r.run());}
    public void onRefresh(Runnable r) { buttonRefresh.addActionListener(e -> r.run()); }

}
