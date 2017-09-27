package com.crud.app.page;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import com.crud.app.grid.EditableGrid;
import com.crud.app.grid.column.AbstractEditablePropertyColumn;
import com.crud.app.grid.column.RequiredEditableTextFieldColumn;
import com.crud.app.grid.provider.EditableListDataProvider;

public class HomePage extends WebPage
{
	private static final long serialVersionUID = 1L;
	
	private FeedbackPanel feedbackPanel;

	public HomePage(final PageParameters parameters)
	{
		super(parameters);
		
		feedbackPanel = new FeedbackPanel("feedBack");
		feedbackPanel.setOutputMarkupPlaceholderTag(true);
		
		add(feedbackPanel);

		add(new EditableGrid<Person, String>("grid", getColumns(), new EditableListDataProvider<Person, String>(getPersons()), 50, Person.class)
		{
			private static final long serialVersionUID = 1L;

			@Override
			protected void onError(AjaxRequestTarget target)
			{
				target.add(feedbackPanel);
			}
			@Override
			protected void onCancel(AjaxRequestTarget target)
			{
				target.add(feedbackPanel);
			}
			@Override
			protected void onDelete(AjaxRequestTarget target, IModel<Person> rowModel)
			{
				target.add(feedbackPanel);
			}
			@Override
			protected void onSave(AjaxRequestTarget target, IModel<Person> rowModel)
			{
				target.add(feedbackPanel);
			}
		});
    }

	private List<AbstractEditablePropertyColumn<Person, String>> getColumns()
	{
		List<AbstractEditablePropertyColumn<Person, String>> columns = new ArrayList<AbstractEditablePropertyColumn<Person, String>>();
		columns.add(new RequiredEditableTextFieldColumn<Person, String>(new Model<String>("Name"), "name"));
		columns.add(new RequiredEditableTextFieldColumn<Person, String>(new Model<String>("Surname"), "address"));
		columns.add(new RequiredEditableTextFieldColumn<Person, String>(new Model<String>("Patronymic"), "age"));
		return columns;
	}

	private List<Person> getPersons()
	{
		List<Person> persons = new ArrayList<Person>();
		persons.add(new Person("Person1","12", "Address1"));
		persons.add(new Person("Person2","13", "Address2"));
		persons.add(new Person("Person3","13", "Address3"));
		persons.add(new Person("Person4","13", "Address4"));
		persons.add(new Person("Person5","13", "Address5"));
		persons.add(new Person("Person6","13", "Address6"));
		return persons;
	}
	@Override
	protected void onInitialize() {
		super.onInitialize();


		Form logout = new Form("logout") {
			protected void onSubmit() {
				AuthenticatedWebSession.get().invalidate();
				setResponsePage(getApplication().getHomePage());
			}
		};
		add(logout);
	}
}
