package com.capgemini.NightFox.repository;

import com.capgemini.NightFox.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppUserRepository extends JpaRepository <AppUser, Long> {
    AppUser findAppUserByUserName(String userName);
    boolean existsByUserName(String userName);

}
