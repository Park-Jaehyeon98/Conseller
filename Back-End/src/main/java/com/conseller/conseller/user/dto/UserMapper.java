package com.conseller.conseller.user.dto;

import com.conseller.conseller.entity.User;
import com.conseller.conseller.user.dto.request.SignUpRequest;
import com.conseller.conseller.user.dto.response.UserInfoResponse;
import com.conseller.conseller.user.enums.AccountBanks;
import com.conseller.conseller.user.enums.UserStatus;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(source = "userId", target = "userId")
    @Mapping(source = "userPassword", target = "userPassword")
    @Mapping(source = "userNickname", target = "userNickname")
    @Mapping(source = "userEmail", target = "userEmail")
    @Mapping(source = "userProfileUrl", target = "userProfileUrl")
    @Mapping(source = "userAccount", target = "userAccount")
    @Mapping(source = "userAccountBank", target = "userAccountBank")
    @Mapping(source = "userPhoneNumber", target = "userPhoneNumber")
    UserInfoResponse toUserInfoResponse(User user);

    @Mapping(target = "userAccountBank",
            expression = "java(com.conseller.conseller.user.enums.AccountBanks.fromString(signUpRequest.getUserAccountBank()).getBank())")
    User SignUpDtoToUser(SignUpRequest signUpRequest);
}
