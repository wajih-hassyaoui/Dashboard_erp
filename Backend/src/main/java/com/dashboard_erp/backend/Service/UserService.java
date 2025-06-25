package com.dashboard_erp.backend.Service;

import com.dashboard_erp.backend.Entity.Role;
import com.dashboard_erp.backend.Entity.User;
import com.dashboard_erp.backend.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    public UserService(UserRepository userRepository) {
        this.userRepository= userRepository;
    }
    public void save(User user) {
        if(userRepository.existsByEmail(user.getEmail()) ) {
            throw new RuntimeException("User already exists");

        }
        if (user.getRole() == Role.admin && userRepository.existsByRole(Role.admin)) {
            throw new RuntimeException("An admin user already exists");
        }
        userRepository.save(user);
    }
    public List<User> getUsers() {
        return userRepository.findAll();
    }
    public User getUserById(int id) {
        return userRepository.findById(id).orElse(null);

    }
    public void deleteUserById(int id) {
        userRepository.deleteById(id);
    }
    public User updateUser(Integer id,User user) {
        User existingUser = userRepository.findById(id).orElseThrow(()->new RuntimeException("User Not Found"));
        if(user.getFirstName()!=null)
            existingUser.setFirstName(user.getFirstName());
        if(user.getLastName()!=null)
            existingUser.setLastName(user.getLastName());
        if(user.getEmail()!=null)
            existingUser.setEmail(user.getEmail());
        if(user.getPhone()!=null)
            existingUser.setPhone(user.getPhone());


        return userRepository.save(existingUser);
    }
}
