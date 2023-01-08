package com.epitech.lemauvaiscoin.service.impl;

import com.epitech.lemauvaiscoin.domain.Admin;
import com.epitech.lemauvaiscoin.exception.admin.AdminConflictException;
import com.epitech.lemauvaiscoin.exception.admin.AdminNotFoundException;
import com.epitech.lemauvaiscoin.repository.AdminRepository;
import com.epitech.lemauvaiscoin.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;

    @Override
    public Admin createAdmin(Admin admin) throws AdminConflictException {
        if (adminRepository.existsByUsername(admin.getUsername()) || adminRepository.existsByMail(admin.getMail())) {
            throw new AdminConflictException();
        }

        admin.setJoinDate(LocalDate.now().toString());

        adminRepository.save(admin);
        return admin;
    }

    @Override
    public List<Admin> getAllAdmin() {
        return adminRepository.findAll();
    }

    @Override
    public Admin getAdminById(Long adminId) throws AdminNotFoundException {
        if (!adminRepository.existsById(adminId)) {
            throw new AdminNotFoundException();
        }

        return adminRepository.getById(adminId);
    }

    @Override
    public Admin updateAdminById(Admin admin) throws AdminNotFoundException {
        if (admin.getId() == null || !adminRepository.existsById(admin.getId())) {
            throw new AdminNotFoundException();
        }
        admin.setJoinDate(adminRepository.getById(admin.getId()).getJoinDate());

        adminRepository.save(admin);

        return admin;
    }

    @Override
    public void deleteAdminById(Long adminId) throws AdminNotFoundException {
        if (!adminRepository.existsById(adminId)) {
            throw new AdminNotFoundException();
        }

        adminRepository.deleteById(adminId);
    }
}
