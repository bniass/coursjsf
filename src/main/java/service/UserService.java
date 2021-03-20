package service;

import model.User;
import org.hibernate.Query;
import org.hibernate.Session;
import util.HibernateUtil;

import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class UserService extends AbstractFacade<User> implements IUser{
    private Session session;

    public UserService(){
        super(User.class);
    }

    @Override
    protected Session getSession() {
        session = HibernateUtil.getSessionFactory().openSession();
        return session;
    }

    @Override
    public User findUser(String login, String pwd) {
        Query query = getSession().createQuery("SELECT u FROM User u WHERE u.username = :username AND u.password = :password");
        query.setParameter("username", login);
        query.setParameter("password", pwd);
        List<User> users = query.list();
        return users.size()>0?users.get(0):null;
    }
}
