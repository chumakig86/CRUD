package com.crud.app.grid.column;

import com.crud.app.grid.component.EditableDataTable;
import com.crud.app.grid.component.EditableGridSubmitLink;
import com.crud.app.grid.provider.EditableListDataProvider;
import com.crud.app.sql.User;
import com.crud.app.sql.UserJDBCTemplate;
import org.apache.wicket.MetaDataKey;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.attributes.AjaxCallListener;
import org.apache.wicket.ajax.attributes.AjaxRequestAttributes;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.event.Broadcast;
import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;

import com.crud.app.grid.model.GridOperationData;
import com.crud.app.grid.model.OperationType;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public abstract class EditableGridActionsPanel<T> extends Panel
{
	ApplicationContext context = new ClassPathXmlApplicationContext("com/crud/app/sql/Beans.xml");

	UserJDBCTemplate userJDBCTemplate =
			(UserJDBCTemplate)context.getBean("userJDBCTemplate");
	public final static MetaDataKey<Boolean> EDITING = new MetaDataKey<Boolean>()
	{
		private static final long serialVersionUID 	= 1L;
	};

	private static final long serialVersionUID = 1L;
	
	protected abstract void onSave(AjaxRequestTarget target);
	protected abstract void onError(AjaxRequestTarget target);
	protected abstract void onCancel(AjaxRequestTarget target);
	protected abstract void onDelete(AjaxRequestTarget target);

	public EditableGridActionsPanel(String id, final Item<ICellPopulator<T>> cellItem)
	{
		super(id);

		@SuppressWarnings("unchecked")
		final Item<T> rowItem = ((Item<T>) cellItem.findParent(Item.class));
		
		add(newEditLink(rowItem));
		add(newSaveLink(rowItem));
		add(newCancelLink(rowItem));
		add(newDeleteLink(rowItem));
	}

	private EditableGridSubmitLink newSaveLink(final Item<T> rowItem)
	{
		return new EditableGridSubmitLink("save", rowItem) 
		{

			private static final long serialVersionUID = 1L;
		
			@Override
			public boolean isVisible()
			{
				return isThisRowBeingEdited(rowItem);
			}
			@Override
			protected void onSuccess(AjaxRequestTarget target)
			{
				rowItem.setMetaData(EDITING, Boolean.FALSE);
				User u = (User) rowItem.getModelObject();
				userJDBCTemplate.update(u.getId(),u.getName(), u.getSurname(), u.getPatronymic());
				send(getPage(), Broadcast.BREADTH, rowItem);
				target.add(rowItem);
				onSave(target);
				
			}
			@Override
			protected void onError(AjaxRequestTarget target)
			{
				EditableGridActionsPanel.this.onError(target);
			}
		};
	}

	private AjaxLink<String> newDeleteLink(final Item<T> rowItem)
	{
		return new AjaxLink<String>("delete") 
		{

			private static final long serialVersionUID = 1L;
			@Override
			protected void updateAjaxAttributes(AjaxRequestAttributes attributes)
			{
				super.updateAjaxAttributes(attributes);
				AjaxCallListener listener = new AjaxCallListener(); 
				listener.onPrecondition("if(!confirm('Do you really want to delete?')){return false;}"); 
				attributes.getAjaxCallListeners().add(listener); 
			}
			@SuppressWarnings("unchecked")
			@Override
			public void onClick(AjaxRequestTarget target)
			{
				send(getPage(), Broadcast.BREADTH, new GridOperationData<T>(OperationType.DELETE, (T) rowItem.getDefaultModelObject()));
				target.add(rowItem.findParent(EditableDataTable.class));
			}
		};
	}

	private AjaxLink<String> newCancelLink(final Item<T> rowItem)
	{
		return new AjaxLink<String>("cancel")
		{

			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(AjaxRequestTarget target) {
				rowItem.setMetaData(EDITING, Boolean.FALSE);
				send(getPage(), Broadcast.BREADTH, rowItem);
				target.add(rowItem);
				onCancel(target);
			}
			@Override
			public boolean isVisible() 
			{
				return isThisRowBeingEdited(rowItem);
			}
		};
	}

	private AjaxLink<String> newEditLink(final Item<T> rowItem)
	{
		return new AjaxLink<String>("edit") 
		{

			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(AjaxRequestTarget target)
			{							
				rowItem.setMetaData(EDITING, Boolean.TRUE);
				send(getPage(), Broadcast.BREADTH, rowItem);
				target.add(rowItem);
			}
			@Override
			public boolean isVisible()
			{
				return !isThisRowBeingEdited(rowItem);
			}
		};
	}
	
	private boolean isThisRowBeingEdited(Item<T> rowItem)
	{
		return rowItem.getMetaData(EDITING);
	}
}
