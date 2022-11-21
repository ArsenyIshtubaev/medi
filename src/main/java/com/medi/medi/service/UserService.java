package com.medi.medi.service;

import com.medi.medi.dto.UserDto;

import java.util.List;

public interface UserService {

    List<UserDto> findAllById(List<Long> ids);

    List<UserDto> findAllWithoutID(int from, int size);

    UserDto save(UserDto userDto);

    void deleteById(long userId);

}
