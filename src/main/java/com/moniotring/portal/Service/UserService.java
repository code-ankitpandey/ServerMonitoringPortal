package com.moniotring.portal.Service;

import com.moniotring.portal.DTO.UserDTO;
import com.moniotring.portal.DTO.UserResponseDTO;
import com.moniotring.portal.Entity.User;
import com.moniotring.portal.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class    UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordService passwordService;

    public String createUser(UserDTO userDTO) {
        if(userRepository.existsById(userDTO.getUserName())){
           return userDTO.getUserName()+" already exists in the system.";
        }
        User user =new User();
        user.setUserName(userDTO.getUserName());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setRole(userDTO.getRole());
        user.setActive(userDTO.isActive());
        user.setPassword(passwordService.encryptPassword(userDTO.getPassword()));
        try{
           userRepository.save(user);
        }catch (Exception e){
            return e.toString();
        }
       return user.getUserName()+" has been created in the system.";
    }

    public String modifyUser(UserResponseDTO userDTO) {
        Optional<User> optionalUser = userRepository.findById(userDTO.getUserName());
        if(optionalUser.isEmpty()){
            return userDTO.getUserName()+ " does not exists in the system.";
        }
        User user = optionalUser.get();
        user.setUserName(userDTO.getUserName());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setRole(userDTO.getRole());
        user.setActive(userDTO.isActive());
        try{
            userRepository.save(user);
        }catch (Exception e){
            return e.toString();
        }
        return user.getUserName()+" has been updated in the system.";
    }


    public UserResponseDTO getUser(String userName){
       Optional<User> optionalUser = userRepository.findById(userName);
        if (optionalUser.isEmpty())return  new UserResponseDTO();
        User user = optionalUser.get();
        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setEmail(user.getEmail());
        userResponseDTO.setUserName(user.getUserName());
        userResponseDTO.setFirstName(user.getFirstName());
        userResponseDTO.setLastName(user.getLastName());
        userResponseDTO.setRole(user.getRole());
        userResponseDTO.setActive(user.isActive());
        return userResponseDTO;
    }

    public String resetPassword(String userName, String newPassword) {
        Optional<User> optionalUser = userRepository.findById(userName);
        if (optionalUser.isEmpty())return userName+" does not exists in the system.";
        User user = optionalUser.get();
        user.setPassword(passwordService.encryptPassword(newPassword));
        userRepository.save(user);
        return "Password reset successful";
    }
}
