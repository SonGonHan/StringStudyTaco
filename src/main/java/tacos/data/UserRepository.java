package tacos.data;

import org.springframework.data.jpa.repository.JpaRepository;

import tacos.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

}
