package com.epitech.lemauvaiscoin.service;

import com.epitech.lemauvaiscoin.domain.Admin;
import com.epitech.lemauvaiscoin.exception.admin.AdminConflictException;
import com.epitech.lemauvaiscoin.exception.admin.AdminNotFoundException;

import java.util.List;

public interface AdminService {

    Admin createAdmin(Admin admin) throws AdminConflictException;

    List<Admin> getAllAdmin();

    Admin getAdminById(Long adminId) throws AdminNotFoundException;

    Admin updateAdminById(Admin admin) throws AdminNotFoundException;

    void deleteAdminById(Long adminId) throws AdminNotFoundException;
}
