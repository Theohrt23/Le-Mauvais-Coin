package com.epitech.lemauvaiscoin.mapper;

import com.epitech.lemauvaiscoin.domain.Admin;
import com.epitech.lemauvaiscoin.mapper.dto.AdminDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class AdminMapper extends UtilMapper {

    public abstract AdminDTO adminToAdminDto(Admin admin);

    @Mapping(target = "password", source = "password", qualifiedByName = "encryptPwd")
    public abstract Admin adminDtoToAdmin(AdminDTO adminDTO);

    public abstract List<AdminDTO> adminListToAdminListDto(List<Admin> adminList);

}
