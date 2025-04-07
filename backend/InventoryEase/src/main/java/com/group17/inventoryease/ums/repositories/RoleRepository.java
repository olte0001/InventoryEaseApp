package com.group17.inventoryease.ums.repositories;

import com.group17.inventoryease.ums.models.Roles;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolesRepository extends JpaRepository<Roles, Long> {
}