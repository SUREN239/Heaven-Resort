package suren.heaven.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import suren.heaven.model.User;


import java.util.List;

public interface UserRepo  extends JpaRepository<User,Long>
{
    User findByEmail(String email);

    @Query("SELECT u FROM User u WHERE u.username = :username")
    List<User> findByUsername(@Param("username") String username);


    @Query("SELECT u FROM User u JOIN u.profile p WHERE p.fullName = :fullName")
    List<User> findByFullName(@Param("fullName") String fullName);

    @Query("SELECT DISTINCT u FROM User u JOIN u.posts p")
    List<User> findUsersWithPosts();

    Page<User> findAll(Pageable pageable);

}
