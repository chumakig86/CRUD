package com.crud.app.grid.column;


public interface IEditableGridColumn<T>
{
	EditableCellPanel<T> getEditableCellPanel(String componentId);
}
