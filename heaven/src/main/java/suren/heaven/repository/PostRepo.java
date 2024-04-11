package suren.heaven.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import suren.heaven.model.Post;


public interface PostRepo extends JpaRepository<Post, Long> {


}
