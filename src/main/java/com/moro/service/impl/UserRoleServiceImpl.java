package com.moro.service.impl;

import com.moro.dao.repository.UserRoleRepository;
import com.moro.model.entity.UserRole;
import com.moro.model.enums.UserRoleEnum;
import com.moro.model.exception.EntityNotFoundException;
import com.moro.service.UserRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@Slf4j
public class UserRoleServiceImpl implements UserRoleService {

    private UserRoleRepository userRoleRepository;

    @Autowired
    public UserRoleServiceImpl(final UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }

    @Override
    public UserRole findUserRoleById(final Integer userRoleId) {
        log.info("Finding user role by id {}", userRoleId);

        return userRoleRepository
                .findById(userRoleId)
                .orElseThrow(
                        () -> new EntityNotFoundException(
                                String.format("No user role with id %d", userRoleId)));
    }

    @Override
    public UserRole findUserRoleByName(final UserRoleEnum name) {
        log.info("Finding user role by name {}", name);

        return userRoleRepository
                .findByName(name)
                .orElseThrow(
                        () -> new EntityNotFoundException(
                                String.format("No user role with name %s", name.name())));
    }

    @Override
    public List<UserRole> findAllUserRoles() {
        log.info("Finding all user roles");

        return userRoleRepository.findAll();
    }
}
