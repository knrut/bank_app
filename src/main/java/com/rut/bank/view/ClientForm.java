package com.rut.bank.view;

import com.rut.bank.model.Nationality;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

public class ClientForm {

    private final JFrame frame;
    private final JPanel panel;

    private final JTextField nationalIdField;   // 🔹 zamiast PESEL
    private final JTextField firstNameField;
    private final JTextField lastNameField;
    private final JSpinner dateOfBirthSpinner;
    private final JComboBox<Nationality> nationalityCombo;

    private final JButton createButton;
    private final JButton backButton;

    public ClientForm(Nationality[] nationalities, Runnable onCloseCallback) {
        frame = new JFrame("Create Client Profile");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(520, 520);
        frame.setLocationRelativeTo(null);

        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                if (onCloseCallback != null) onCloseCallback.run();
            }
        });

        // === PANEL GŁÓWNY ===
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
        ((AbstractDocument) nationalIdField.getDocument()).setDocumentFilter(new LengthLimitFilter(20)); // max 20 znaków
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

        // === DATE OF BIRTH ===
        c.gridy = row++;
        panel.add(mkLabel.apply("Date of birth:"), c);

        Calendar cal = Calendar.getInstance();
        cal.set(2000, Calendar.JANUARY, 1);
        Date initial = cal.getTime();

        SpinnerDateModel dateModel = new SpinnerDateModel(initial, null, new Date(), Calendar.DAY_OF_MONTH);
        dateOfBirthSpinner = new JSpinner(dateModel);
        dateOfBirthSpinner.setFont(fieldFont);
        dateOfBirthSpinner.setEditor(new JSpinner.DateEditor(dateOfBirthSpinner, "yyyy-MM-dd"));
        c.gridy = row++;
        panel.add(dateOfBirthSpinner, c);

        // === NATIONALITY ===
        c.gridy = row++;
        panel.add(mkLabel.apply("Nationality:"), c);

        nationalityCombo = new JComboBox<>(nationalities);
        nationalityCombo.setFont(fieldFont);
        c.gridy = row++;
        panel.add(nationalityCombo, c);

        // === PRZYCISKI ===
        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 14, 0));
        buttonPanel.setOpaque(false);

        createButton = new JButton("Create");
        createButton.setFont(labelFont);
        createButton.setForeground(Color.WHITE);
        createButton.setBackground(new Color(33, 150, 243)); // primary
        createButton.setOpaque(true);
        createButton.setBorderPainted(false);
        createButton.setFocusPainted(false);
        buttonPanel.add(createButton);

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

    // Konstruktor uproszczony
    public ClientForm(Runnable onCloseCallback) {
        this(Nationality.values(), onCloseCallback);
    }

    // --- Gettery ---
    public JFrame getFrame() { return frame; }
    public JTextField getNationalIdField() { return nationalIdField; }
    public JTextField getFirstNameField() { return firstNameField; }
    public JTextField getLastNameField() { return lastNameField; }
    public JSpinner getDateOfBirthSpinner() { return dateOfBirthSpinner; }
    public JComboBox<Nationality> getNationalityCombo() { return nationalityCombo; }
    public JButton getCreateButton() { return createButton; }
    public JButton getBackButton() { return backButton; }

    // --- Helpery do odczytu wartości ---
    public String getNationalId() { return nationalIdField.getText().trim(); }

    public LocalDate getDateOfBirth() {
        Date d = (Date) dateOfBirthSpinner.getValue();
        return Instant.ofEpochMilli(d.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
    }
    public Nationality getSelectedNationality() { return (Nationality) nationalityCombo.getSelectedItem(); }
    public String getFirstName() { return firstNameField.getText().trim(); }
    public String getLastName() { return lastNameField.getText().trim(); }

    // === DocumentFilter: ograniczenie długości ===
    private static class LengthLimitFilter extends DocumentFilter {
        private final int max;
        LengthLimitFilter(int max) { this.max = max; }

        @Override
        public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
            String current = fb.getDocument().getText(0, fb.getDocument().getLength());
            StringBuilder sb = new StringBuilder(current);
            sb.replace(offset, offset + length, text != null ? text : "");
            if (sb.length() <= max) {
                super.replace(fb, offset, length, text, attrs);
            }
        }
    }
}
