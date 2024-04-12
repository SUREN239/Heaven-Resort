package suren.heaven.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import suren.heaven.model.Profile;

public interface ProfileRepo extends JpaRepository<Profile, Long> {


}
