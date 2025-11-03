package com.rut.bank.view;

import com.rut.bank.model.Nationality;
import javax.swing.*;
import java.awt.*;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

public class ClientForm {

    private final JFrame frame;
    private final JPanel panel;

    private final JTextField firstNameField;
    private final JTextField lastNameField;
    private final JSpinner dateOfBirthSpinner;
    private final JComboBox<Nationality> nationalityCombo;

    private final JButton createButton;
    private final JButton backButton;

    // ✅ Konstruktor główny – kontroler przekazuje listę enumów
    public ClientForm(Nationality[] nationalities, Runnable onCloseCallback) {
        frame = new JFrame("Create Client Profile");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(720, 600);
        frame.setLocationRelativeTo(null);

        // callback przy zamykaniu
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                if (onCloseCallback != null) onCloseCallback.run();
            }
        });

        // panel główny
        panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(16, 16, 16));
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(24, 24, 24, 24),
                BorderFactory.createLineBorder(new Color(200, 200, 200))
        ));

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(18, 24, 18, 24);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1;

        Font labelFont = new Font("Arial", Font.BOLD, 20);
        Font fieldFont = new Font("Arial", Font.PLAIN, 18);
        Color fg = new Color(235, 235, 235);
        Color bgField = new Color(30, 30, 30);
        Color borderField = new Color(70, 70, 70);

        java.util.function.Function<String, JLabel> mkLabel = (text) -> {
            JLabel l = new JLabel(text, SwingConstants.CENTER);
            l.setFont(labelFont);
            l.setForeground(fg);
            return l;
        };

        // FIRST NAME
        c.gridx = 0; c.gridy = 0; c.gridwidth = 2;
        panel.add(mkLabel.apply("FirstName"), c);

        firstNameField = new JTextField();
        styleTextField(firstNameField, fieldFont, fg, bgField, borderField);
        c.gridy = 1;
        panel.add(firstNameField, c);

        // LAST NAME
        c.gridy = 2;
        panel.add(mkLabel.apply("lastName"), c);

        lastNameField = new JTextField();
        styleTextField(lastNameField, fieldFont, fg, bgField, borderField);
        c.gridy = 3;
        panel.add(lastNameField, c);

        // DATE OF BIRTH
        c.gridy = 4;
        panel.add(mkLabel.apply("DateOfBirth"), c);

        Calendar cal = Calendar.getInstance();
        cal.set(2000, Calendar.JANUARY, 1);
        Date initial = cal.getTime();

        SpinnerDateModel dateModel = new SpinnerDateModel(
                initial, null, new Date(), Calendar.DAY_OF_MONTH
        );
        dateOfBirthSpinner = new JSpinner(dateModel);
        dateOfBirthSpinner.setFont(fieldFont);
        dateOfBirthSpinner.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(borderField),
                BorderFactory.createEmptyBorder(6, 8, 6, 8)
        ));
        dateOfBirthSpinner.setEditor(new JSpinner.DateEditor(dateOfBirthSpinner, "yyyy-MM-dd"));
        ((JSpinner.DefaultEditor) dateOfBirthSpinner.getEditor()).getTextField().setBackground(bgField);
        ((JSpinner.DefaultEditor) dateOfBirthSpinner.getEditor()).getTextField().setForeground(fg);
        c.gridy = 5;
        panel.add(dateOfBirthSpinner, c);

        // NATIONALITY
        c.gridy = 6;
        panel.add(mkLabel.apply("Nationality"), c);

        nationalityCombo = new JComboBox<>(nationalities);
        nationalityCombo.setFont(fieldFont);
        nationalityCombo.setBackground(bgField);
        nationalityCombo.setForeground(fg);
        nationalityCombo.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                Component comp = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                comp.setBackground(isSelected ? new Color(55, 55, 55) : bgField);
                comp.setForeground(fg);
                setHorizontalAlignment(CENTER);
                return comp;
            }
        });
        c.gridy = 7;
        panel.add(nationalityCombo, c);

        // BUTTONS
        JPanel buttons = new JPanel(new GridLayout(2, 1, 0, 16));
        buttons.setOpaque(false);

        createButton = new JButton("Create");
        styleButton(createButton, labelFont, fg);
        buttons.add(createButton);

        backButton = new JButton("Back");
        styleButton(backButton, labelFont, fg);
        buttons.add(backButton);

        c.gridy = 8;
        panel.add(buttons, c);

        frame.setContentPane(panel);
        frame.setVisible(true);
    }

    // konstruktor uproszczony – domyślnie używa wszystkich wartości enuma
    public ClientForm(Runnable onCloseCallback) {
        this(Nationality.values(), onCloseCallback);
    }

    // stylizacja pól
    private void styleTextField(JTextField tf, Font font, Color fg, Color bg, Color border) {
        tf.setFont(font);
        tf.setForeground(fg);
        tf.setBackground(bg);
        tf.setCaretColor(fg);
        tf.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(border),
                BorderFactory.createEmptyBorder(8, 10, 8, 10)
        ));
    }

    private void styleButton(JButton b, Font font, Color fg) {
        b.setFont(font);
        b.setFocusPainted(false);
        b.setForeground(fg);
        b.setBackground(new Color(28, 28, 28));
        b.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(90, 90, 90)),
                BorderFactory.createEmptyBorder(12, 24, 12, 24)
        ));
    }

    // --- Gettery ---
    public JFrame getFrame() { return frame; }
    public JTextField getFirstNameField() { return firstNameField; }
    public JTextField getLastNameField() { return lastNameField; }
    public JSpinner getDateOfBirthSpinner() { return dateOfBirthSpinner; }
    public JComboBox<Nationality> getNationalityCombo() { return nationalityCombo; }
    public JButton getCreateButton() { return createButton; }
    public JButton getBackButton() { return backButton; }

    // --- Helpery ---
    public LocalDate getDateOfBirth() {
        Date d = (Date) dateOfBirthSpinner.getValue();
        return Instant.ofEpochMilli(d.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public Nationality getSelectedNationality() {
        return (Nationality) nationalityCombo.getSelectedItem();
    }

    public String getFirstName() {
        return firstNameField.getText().trim();
    }

    public String getLastName() {
        return lastNameField.getText().trim();
    }
}
