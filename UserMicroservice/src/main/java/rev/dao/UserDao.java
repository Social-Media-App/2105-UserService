package rev.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import rev.model.User;

public interface UserDao extends JpaRepository<User, Integer> {

}
