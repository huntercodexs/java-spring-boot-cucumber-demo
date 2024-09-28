package com.huntercodexs.demo.mapper;

import com.huntercodexs.demo.entity.UserEntity;
import com.huntercodexs.demo.model.UserModel;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {
    UserEntity toEntity(UserModel userModel);
    UserModel toModel(UserEntity userEntity);
}
