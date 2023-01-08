package com.epitech.lemauvaiscoin.mapper;

import com.epitech.lemauvaiscoin.domain.User;
import com.epitech.lemauvaiscoin.mapper.dto.UserDTO;
import com.epitech.lemauvaiscoin.service.UserService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class UserMapper extends UtilMapper {

    public abstract UserDTO userToUserDto(User user);

    @Mapping(target = "password", source = "password", qualifiedByName = "encryptPwd")
    public abstract User userDtoToUser(UserDTO userDTO);

    public abstract List<UserDTO> userListToUserListDto(List<User> userList);

}

