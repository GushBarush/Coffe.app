package com.example.coffeapp.service;

import com.example.coffeapp.dto.user.RoleDTO;
import com.example.coffeapp.entity.user.Role;
import com.example.coffeapp.repository.RoleRepo;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class RoleService {

    private final RoleRepo roleRepo;

    public List<RoleDTO> allRole() {
        List<Role> roles = roleRepo.findAll();
        List<RoleDTO> roleDTOS = new ArrayList<>();
        ModelMapper mapper = new ModelMapper();

        for (Role role : roles) {
            roleDTOS.add(mapper.map(role, RoleDTO.class));
        }
        return roleDTOS;
    }

    public void addRole(RoleDTO roleDTO) {
        //Доделать
    }

    public void delRole(RoleDTO roleDTO) {
        roleRepo.delete(roleRepo.findByRoleName(roleDTO.getRoleName()));
    }
}
