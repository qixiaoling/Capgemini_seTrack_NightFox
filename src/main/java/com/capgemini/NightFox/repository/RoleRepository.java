package com.capgemini.NightFox.repository;

import com.capgemini.NightFox.model.ApplicationUserRole;
import com.capgemini.NightFox.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository <Role, Long> {
        Role findByRoleName(ApplicationUserRole roleName);
        boolean existsByRoleName(ApplicationUserRole roleName);


}
