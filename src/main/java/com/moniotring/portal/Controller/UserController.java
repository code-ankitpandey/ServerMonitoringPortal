package com.moniotring.portal.Controller;

import com.moniotring.portal.DTO.UserDTO;
import com.moniotring.portal.DTO.UserResponseDTO;
import com.moniotring.portal.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/createUser")
    public String createUser(@RequestBody UserDTO userDTO) {
        return userService.createUser(userDTO);
    }

    @GetMapping("/getUser")
    public UserResponseDTO getUser(@RequestParam String userName) {
        return userService.getUser(userName);
    }

    @PutMapping("/modifyUser")
    public String modifyUser(@RequestBody UserResponseDTO userResponseDTO) {
        return userService.modifyUser(userResponseDTO);
    }

    @PutMapping("/changePassword")
    public String resetPassword(@RequestParam String userName, String newPassword) {
        return userService.resetPassword(userName, newPassword);
    }

}
