package olsvc.models;

import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

@Transactional
public interface UserDao extends CrudRepository<User, Long> {

    public User findByLogin(String login);
    public User findByEmail(String email);

}
