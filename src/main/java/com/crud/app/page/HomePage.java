package com.crud.app.page;

import java.util.ArrayList;
import java.util.List;

import com.crud.app.sql.User;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.markup.html.WebPage;
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

		add(new EditableGrid<User, String>("grid", getColumns(), new EditableListDataProvider<User, String>(), 50, User.class)
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
			protected void onDelete(AjaxRequestTarget target, IModel<User> rowModel)
			{
				target.add(feedbackPanel);
			}
			@Override
			protected void onSave(AjaxRequestTarget target, IModel<User> rowModel)
			{
				target.add(feedbackPanel);
			}
		});
		searchform= new Form<Void>("searchform");



		final TextField<String> code = new TextField("searchname",new Model<String>(""));

		searchform.add(code);



		AjaxButton ab=new AjaxButton("search") {
			protected void onSubmit(AjaxRequestTarget target, Form form) {

				if (target!=null)
				{




				}

			}

		};

		searchform.add(ab);
		add(searchform);
    }


	private List<AbstractEditablePropertyColumn<User, String>> getColumns()
	{
		List<AbstractEditablePropertyColumn<User, String>> columns = new ArrayList<AbstractEditablePropertyColumn<User, String>>();
		columns.add(new RequiredEditableTextFieldColumn<User, String>(new Model<String>("Name"), "name"));
		columns.add(new RequiredEditableTextFieldColumn<User, String>(new Model<String>("Surname"), "surname"));
		columns.add(new RequiredEditableTextFieldColumn<User, String>(new Model<String>("Patronymic"), "patronymic"));
		return columns;
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
