package com.university.service;

import com.university.model.Roles;
import com.university.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;

    public void createRole(Roles roles) {
        roleRepository.save(roles);
    }

    public void createRoleFromList(List<Roles> rolesList) {
        roleRepository.saveAll(rolesList);
    }

    public List<Roles> findAllRoles() {
        return roleRepository.findAll();
    }
}
