package com.moro.service;

import com.moro.model.entity.UserRole;
import com.moro.model.enums.UserRoleEnum;

import java.util.List;

public interface UserRoleService {

    UserRole findUserRoleById(Integer userRoleId);

    UserRole findUserRoleByName(UserRoleEnum name);

    List<UserRole> findAllUserRoles();
}
