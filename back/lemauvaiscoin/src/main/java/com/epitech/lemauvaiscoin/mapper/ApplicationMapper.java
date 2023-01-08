package com.epitech.lemauvaiscoin.mapper;

import com.epitech.lemauvaiscoin.domain.Application;
import com.epitech.lemauvaiscoin.mapper.dto.ApplicationDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class ApplicationMapper {

    public abstract ApplicationDTO applicationToApplicationDto(Application application);

    public abstract Application applicationDtoToApplication(ApplicationDTO applicationDTO);

    public abstract List<ApplicationDTO> applicationListToApplicationListDto(List<Application> applicationList);
}
