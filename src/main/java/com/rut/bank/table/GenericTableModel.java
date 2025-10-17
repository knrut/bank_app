package com.rut.bank.table;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class GenericTableModel<E> extends AbstractTableModel {
    private List<E> data = new ArrayList<>();
    private final String[] columns;
    private final List<Function<E, Object>> extractors;

    // NOWY konstruktor – pasuje do Twojego wywołania
    public GenericTableModel(String[] columns, List<Function<E, Object>> extractors) {
        if (columns.length != extractors.size()) {
            throw new IllegalArgumentException("columns and extractors length must match");
        }
        this.columns = columns;
        this.extractors = extractors;
    }

    public void reload(List<E> newData) {
        this.data = (newData != null) ? newData : List.of();
        fireTableDataChanged();
    }

    @Override public int getRowCount() { return data.size(); }
    @Override public int getColumnCount() { return columns.length; }
    @Override public String getColumnName(int column) { return columns[column]; }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        E row = data.get(rowIndex);
        return extractors.get(columnIndex).apply(row);
    }
}
