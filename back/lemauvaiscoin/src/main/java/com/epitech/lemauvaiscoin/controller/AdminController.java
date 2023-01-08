package com.epitech.lemauvaiscoin.controller;

import com.epitech.lemauvaiscoin.domain.UserRole;
import com.epitech.lemauvaiscoin.exception.admin.AdminConflictException;
import com.epitech.lemauvaiscoin.exception.admin.AdminNotFoundException;
import com.epitech.lemauvaiscoin.mapper.AdminMapper;
import com.epitech.lemauvaiscoin.mapper.dto.AdminDTO;
import com.epitech.lemauvaiscoin.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;

    private final AdminMapper adminMapper;

    @GetMapping("/{adminId}")
    @RolesAllowed({UserRole.Values.ADMIN})
    public AdminDTO getAdminById(@PathVariable(value = "adminId") Long adminId) throws AdminNotFoundException {
        return  adminMapper.adminToAdminDto(adminService.getAdminById(adminId));
    }

    @GetMapping()
    @RolesAllowed({UserRole.Values.ADMIN})
    public List<AdminDTO> getAllAdmin() {
        return adminMapper.adminListToAdminListDto(adminService.getAllAdmin());
    }

    @PostMapping()
    public AdminDTO createAdmin(@Valid @RequestBody AdminDTO adminDTO) throws AdminConflictException {
        return adminMapper.adminToAdminDto(adminService.createAdmin(adminMapper.adminDtoToAdmin(adminDTO)));
    }

    @PutMapping()
    @RolesAllowed({UserRole.Values.ADMIN})
    public AdminDTO updateAdminById(@Valid @RequestBody AdminDTO adminDTO) throws AdminNotFoundException {
        return adminMapper.adminToAdminDto(adminService.updateAdminById(adminMapper.adminDtoToAdmin(adminDTO)));
    }

    @DeleteMapping("/{adminId}")
    @RolesAllowed({UserRole.Values.ADMIN})
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteAdminById(@PathVariable(value = "adminId") Long adminId) throws AdminNotFoundException {
        adminService.deleteAdminById(adminId);
    }

}
