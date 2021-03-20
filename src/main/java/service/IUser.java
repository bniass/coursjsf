package service;

import model.User;

import javax.ejb.Local;
import java.util.List;

@Local
public interface IUser {
    void createOrUpdate(User entity);
    void remove(User entity);
    User find(Long id);
    List<User> findAll();
    User findUser(String login, String pwd);
}
