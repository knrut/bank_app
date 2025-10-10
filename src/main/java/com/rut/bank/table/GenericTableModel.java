package com.rut.bank.table;

import javax.swing.table.AbstractTableModel;
import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;


public class GenericTableModel<E> extends AbstractTableModel {
    private List<E> data = new ArrayList<>();
    private final String[] columns;
    private final Field[] fields;

    public GenericTableModel(Class<E> clazz) {
        this.fields = clazz.getDeclaredFields();
        this.columns = new String[fields.length];

        for (int i = 0; i < fields.length; i++) {
            fields[i].setAccessible(true);
            columns[i] = fields[i].getName();
        }
    }

    public void reload(List<E> newData) {
        this.data = newData;
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
       return data.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public String getColumnName(int column) {
        return columns[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        E obj = data.get(rowIndex);
        try {
            return fields[columnIndex].get(obj);
        } catch (IllegalAccessException e) {
            return "N/A";
        }
    }
}
