package com.rut.bank.view;
import javax.swing.*;
import java.awt.*;

public class RegisterClientForm {

    private final JFrame frame;
    private final JPanel panel;

    private final JTextField nationalIdField;
    private final JTextField firstNameField;
    private final JTextField lastNameField;

    private final JTextField loginField;
    private final JPasswordField passwordField;

    private final JButton registerButton;
    private final JButton backButton;

    public RegisterClientForm(Runnable onCloseCallback) {
        frame = new JFrame("Register Client Account");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(520, 460);
        frame.setLocationRelativeTo(null);

        // callback przy zamykaniu
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override public void windowClosing(java.awt.event.WindowEvent e) {
                if (onCloseCallback != null) onCloseCallback.run();
            }
        });

        // === PANEL GŁÓWNY (jasny) ===
        panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 36, 20, 36));

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(8, 8, 8, 8);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1;

        Font labelFont = new Font("Arial", Font.BOLD, 14);
        Font fieldFont = new Font("Arial", Font.PLAIN, 14);

        java.util.function.Function<String, JLabel> mkLabel = (text) -> {
            JLabel l = new JLabel(text);
            l.setFont(labelFont);
            l.setForeground(Color.BLACK);
            return l;
        };

        int row = 0;

        // === NATIONAL ID ===
        c.gridx = 0; c.gridy = row++;
        panel.add(mkLabel.apply("National ID:"), c);

        nationalIdField = new JTextField();
        nationalIdField.setFont(fieldFont);
        c.gridy = row++;
        panel.add(nationalIdField, c);

        // === FIRST NAME ===
        c.gridy = row++;
        panel.add(mkLabel.apply("First name:"), c);

        firstNameField = new JTextField();
        firstNameField.setFont(fieldFont);
        c.gridy = row++;
        panel.add(firstNameField, c);

        // === LAST NAME ===
        c.gridy = row++;
        panel.add(mkLabel.apply("Last name:"), c);

        lastNameField = new JTextField();
        lastNameField.setFont(fieldFont);
        c.gridy = row++;
        panel.add(lastNameField, c);

        // === LOGIN ===
        c.gridy = row++;
        panel.add(mkLabel.apply("Login:"), c);

        loginField = new JTextField();
        loginField.setFont(fieldFont);
        c.gridy = row++;
        panel.add(loginField, c);

        // === PASSWORD ===
        c.gridy = row++;
        panel.add(mkLabel.apply("Password:"), c);

        passwordField = new JPasswordField();
        passwordField.setFont(fieldFont);
        c.gridy = row++;
        panel.add(passwordField, c);

        // === PRZYCISKI ===
        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 14, 0));
        buttonPanel.setOpaque(false);

        registerButton = new JButton("Register");
        registerButton.setFont(labelFont);
        registerButton.setForeground(Color.WHITE);
        registerButton.setBackground(new Color(33, 150, 243)); // kontrastowy
        registerButton.setOpaque(true);
        registerButton.setBorderPainted(false);
        registerButton.setFocusPainted(false);
        buttonPanel.add(registerButton);

        backButton = new JButton("Back");
        backButton.setFont(labelFont);
        backButton.setBackground(new Color(240, 240, 240));
        backButton.setFocusPainted(false);
        backButton.setOpaque(true);
        buttonPanel.add(backButton);

        c.gridy = row++;
        panel.add(buttonPanel, c);

        frame.setContentPane(panel);
        frame.setVisible(true);
    }

    // --- Gettery dla kontrolera ---
    public JFrame getFrame() { return frame; }
    public JTextField getNationalIdField() { return nationalIdField; }
    public JTextField getFirstNameField() { return firstNameField; }
    public JTextField getLastNameField() { return lastNameField; }
    public JTextField getLoginField() { return loginField; }
    public JPasswordField getPasswordField() { return passwordField; }
    public JButton getRegisterButton() { return registerButton; }
    public JButton getBackButton() { return backButton; }

    // --- Wygodne helpery (opcjonalnie) ---
    public String getNationalId() { return nationalIdField.getText().trim(); }
    public String getFirstName()  { return firstNameField.getText().trim(); }
    public String getLastName()   { return lastNameField.getText().trim(); }
    public String getLogin()      { return loginField.getText().trim(); }
    public char[] getPassword()   { return passwordField.getPassword(); } // kontroler może później wyczyścić tablicę
}

