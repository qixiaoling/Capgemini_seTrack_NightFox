package com.capgemini.NightFox.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long role_id;
    @Column
    @Enumerated(EnumType.STRING)
    private ApplicationUserRole roleName;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST, mappedBy = "roles")
    private Set<AppUser> appUsers;

    public Role() {
    }

    public Role(ApplicationUserRole roleName) {
        this.roleName = roleName;
    }

    public Long getRole_id() {
        return role_id;
    }

    public void setRole_id(Long role_id) {
        this.role_id = role_id;
    }

    public ApplicationUserRole getRoleName() {
        return roleName;
    }

    public void setRoleName(ApplicationUserRole roleName) {
        this.roleName = roleName;
    }

    public Set<AppUser> getAppUsers() {
        return appUsers;
    }

    public void setAppUsers(Set<AppUser> appUsers) {
        this.appUsers = appUsers;
    }
}
