package com.crud.app.page;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
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
	Form<Void> searchform =null;

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
		searchform= new Form<Void>("searchform");

		final Model<String> model = new Model<String>() {
			private String location ="";
			public String getObject() {
				return location;
			}

			public void setObject(String location) {
				this.location=location;
			}

		};

		final TextField<String> code = new TextField("searchname",new Model<String>(""));

		searchform.add(code);



		AjaxButton ab=new AjaxButton("search") {
			protected void onSubmit(AjaxRequestTarget target, Form form) {

				if (target!=null)
				{

					String search=form.getRequest().getRequestParameters().getParameterValue("searchname").toString();


				}

			}

		};

		searchform.add(ab);
		add(searchform);
    }
	private String getLocation(Integer cyCode)
	{

		HashMap<Integer,String> locations=new HashMap<Integer, String>();
		locations.put(1, "USA");
		locations.put(61,"Australia");
		locations.put(91, "India");
		return locations.get(cyCode);
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
		persons.add(new Person("Ivan","Vasilyevich", "Ivanov"));
		persons.add(new Person("Vasya","Pupkin", "Petrovich"));
		persons.add(new Person("Frunk","Kafka", "Valerianovich"));

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
