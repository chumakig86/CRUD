package com.crud.app.grid.provider;

import java.io.Serializable;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import com.crud.app.sql.User;
import com.crud.app.sql.UserJDBCTemplate;
import org.apache.wicket.extensions.markup.html.repeater.data.sort.ISortState;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class EditableListDataProvider<T extends Serializable, S> implements IEditableDataProvider<User,S>
{

	private static final long serialVersionUID = 1L;

	ApplicationContext context = new ClassPathXmlApplicationContext("com/crud/app/sql/Beans.xml");

	UserJDBCTemplate userJDBCTemplate =
			(UserJDBCTemplate)context.getBean("userJDBCTemplate");
	private List<User> list;

	public EditableListDataProvider() {
		list = userJDBCTemplate.listUsers();
	}

	public List<User> getData()
	{
		list = userJDBCTemplate.listUsers();
		return list;
	}

	@Override
	public Iterator<? extends User> iterator(final long first, final long count)
	{
		List<User> list = getData();

		long toIndex = first + count;
		if (toIndex > list.size())
		{
			toIndex = list.size();
		}
		return list.subList((int)first, (int)toIndex).listIterator();
	}

	@Override
	public long size()
	{
		return getData().size();
	}

	@Override
	public IModel<User> model(User object)
	{
		return new Model<User>(object);
	}

	@Override
	public void detach()
	{
	}

	@Override
	public void add(User item)
	{
		userJDBCTemplate.create(item.getName(), item.getSurname(), item.getPatronymic());
		getData();

	}

	@Override
	public void remove(User item)
	{
		userJDBCTemplate.delete(item.getName());
		getData();
	}


	@Override
	public ISortState<S> getSortState() {
		return null;
	}
}
