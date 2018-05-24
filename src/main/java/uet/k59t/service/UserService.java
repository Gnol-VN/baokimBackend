package uet.k59t.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uet.k59t.controller.dto.UserDTO;
import uet.k59t.model.User;
import uet.k59t.repository.UserRepository;

import java.util.UUID;

/**
 * Created by Long on 11/21/2016.
 */
@Service
public class UserService {
    @Autowired
    public UserRepository userRepository;

    public UserDTO createUser(UserDTO userDTO) {
//        User user = userRepository.findByUserName(userDTO.getUserName());
//        if(user == null){
//            user = new User();
//            user.setUserName(userDTO.getUserName());
//            user.setPassword(userDTO.getPassword());
//            user.setToken(UUID.randomUUID().toString());
//            userRepository.save(user);
//            return userDTO;
//        }
//        else {
//            throw new NullPointerException("Username da ton tai");
//        }
        return null;
    }

    public UserDTO getUserById(Long id) {
        if(userRepository.findOne(id) != null){
            User user = userRepository.findOne(id);
            UserDTO userDTO = new UserDTO();
            userDTO.setUserName(user.getUserName());
            return userDTO;
        }
        else throw new NullPointerException("Invalid id");
    }
}
