package com.crud.app.sql;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;

public class UserMapper implements RowMapper <User> {
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User User = new User();
        User.setName(rs.getString("name"));
        User.setSurname(rs.getString("surname"));
        User.setPatronymic(rs.getString("patronymic"));

        return User;
    }
}
