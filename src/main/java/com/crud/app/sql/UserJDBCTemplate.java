package com.crud.app.sql;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;

public class UserJDBCTemplate implements UserDAO {
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplateObject;
    @Override
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }

    @Override
    public void create(String name, String surname, String patronymic) {

    }

    @Override
    public User getUser(String name) {
        return null;
    }

    @Override
    public List<User> listUsers() {
        String SQL = "select * from User";
        List <User> users = jdbcTemplateObject.query(SQL, new UserMapper());
        return users;
    }

    @Override
    public void delete(String name) {

    }

    @Override
    public void update(String name, String surname, String patronymic) {

    }
}
