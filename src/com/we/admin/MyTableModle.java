package com.we.admin;

import javax.swing.table.AbstractTableModel;

@SuppressWarnings("serial")
public class MyTableModle extends AbstractTableModel {

	private Object[][] objs;

	private int rowCount;
	private int columnCount;

	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}

	public void setColumnCount(int columnCount) {
		this.columnCount = columnCount;
	}

	public MyTableModle(Object[][] objs, int rowCount, int columnCount) {
		this.objs = objs;
		this.rowCount = rowCount;
		this.columnCount = columnCount;
	}

	@Override
	public int getRowCount() {
		return rowCount;
	}

	@Override
	public int getColumnCount() {
		return columnCount;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return objs[rowIndex][columnIndex];
	}

}
