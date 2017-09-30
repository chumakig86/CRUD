package com.crud.app.sql;
import java.util.List;
import javax.sql.DataSource;

public interface UserDAO {
    /**
     * This is the method to be used to initialize
     * database resources ie. connection.
     */
    public void setDataSource(DataSource ds);

    /**
     * This is the method to be used to create
     * a record in the User table.
     */
    public void create(String name, String surname, String patronymic);

    /**
     * This is the method to be used to list down
     * a record from the User table corresponding
     * to a passed student name.
     */
    public User getUser(String name);

    /**
     * This is the method to be used to list down
     * all the records from the User table.
     */
    public List<User> listUsers();

    /**
     * This is the method to be used to delete
     * a record from the User table corresponding
     * to a passed user name.
     */
    public void delete(String name);

    /**
     * This is the method to be used to update
     * a record into the User table.
     */
    public void update(String name, String surname, String patronymic);
}
