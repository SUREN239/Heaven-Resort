package suren.heaven.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import suren.heaven.model.User;

public interface UserRepo  extends JpaRepository<User,Long>
{
    User findByEmail(String username);
}
