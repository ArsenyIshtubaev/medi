package com.medi.medi.controller;

import com.medi.medi.dto.UserDto;
import com.medi.medi.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/users")
public class UserController {

    private final UserService userService;

    @GetMapping
    public List<UserDto> findAll(@RequestParam(required = false) List<Long> ids,
                                 @RequestParam(defaultValue = "0") int from,
                                 @RequestParam(defaultValue = "10") int size) {
        if (ids != null && !ids.isEmpty()) {
            return userService.findAllById(ids);
        } else {
            return userService.findAllWithoutID(from, size);
        }
    }

    @PostMapping
    public UserDto create(@RequestBody @Valid UserDto userDto) {
        log.info("Get request: '{} {}', User: Name: {} and Email: {}", "POST", "/users",
                userDto.getName(), userDto.getEmail());
        return userService.save(userDto);
    }

    @DeleteMapping("/{id}")
    public void deleteUserById(@PathVariable long id) {
        userService.deleteById(id);
    }

}
