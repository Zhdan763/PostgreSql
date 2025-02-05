package util;

import domain.User;
import domain.entity.UserEntity;

public class UserConverter implements Converter<UserEntity, User> {

    @Override
    public UserEntity convertDoMainToEntity(User user) {
        return UserEntity.builder()
                .id(user.getId())
                .login(user.getLogin())
                .password(user.getPassword())
                .build();
    }

    @Override
    public User convertEntityToDoMain(UserEntity userEntity) {
        return User.builder()
                .id(userEntity.getId())
                .login(userEntity.getLogin())
                .password(userEntity.getPassword())
                .build();
    }
}
