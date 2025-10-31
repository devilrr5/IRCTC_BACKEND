package com.irctc.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.irctc.entity.Role;
public interface RoleRepository extends JpaRepository<Role, Long> {}
