package com.flag.travelplanner.user.repository;

import com.flag.travelplanner.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import static java.sql.Types.*;

@Repository
public class UserRepositoryJdbc implements UserRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public long count() {
        long count = 0;
        try {
            count = jdbcTemplate.queryForObject("select count(*) from users", Long.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public int create(User user) {
        String sqlQuery = "insert ignore into users (userId, userName, password, firstName, lastName, email) " +
                "values (?, ?, ?, ?, ?, ?)";

        return jdbcTemplate.update(sqlQuery, user.getUserId(),
                user.getUserName(),
                user.getPassword(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail());
    }

    @Override
    public User findById(long id) {
        String sqlQuery = "select * from users where userId = ?";

        User user = null;
        try {
            user = jdbcTemplate.queryForObject(sqlQuery, new Object[]{id}, new int[]{BIGINT},
                    (rs, rowNum)-> new User(rs.getInt("userId"),
                            rs.getString("userName"),
                            rs.getString("password"),
                            rs.getString("firstName"),
                            rs.getString("lastName"),
                            rs.getString("email")));
        } catch(Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public User findByUserName(String userName) {
        String sqlQuery = "select * from users where userName = ?";
        User user = null;
        try {
            user = jdbcTemplate.queryForObject(sqlQuery, new Object[]{userName}, new int[]{VARCHAR},
                    (rs, rowNum)-> new User(rs.getInt("userId"),
                            rs.getString("userName"),
                            rs.getString("password"),
                            rs.getString("firstName"),
                            rs.getString("lastName"),
                            rs.getString("email")));
        } catch(Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public User findByEmail(String email) {
        String sqlQuery = "select * from users where email = ?";

        User user = null;
        try {
            user = jdbcTemplate.queryForObject(sqlQuery, new Object[]{email}, new int[]{VARCHAR},
                    (rs, rowNum)-> new User(rs.getInt("userId"),
                            rs.getString("userName"),
                            rs.getString("password"),
                            rs.getString("firstName"),
                            rs.getString("lastName"),
                            rs.getString("email")));
        } catch(Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public int update(User user) {
        String sqlQuery ="update users set userName = ?, password = ?, firstName = ?, lastName = ?, email = ? where userId = ?";

        return jdbcTemplate.update(sqlQuery, user.getUserName(),
                user.getPassword(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getUserId());
    }

    @Override
    public int deleteById(long id) {
        return jdbcTemplate.update("delete from users where userId = ?", id);
    }

    @Override
    public int deleteByUserName(String userName) {
        return jdbcTemplate.update("delete from users where userName = ?", userName);
    }
}
