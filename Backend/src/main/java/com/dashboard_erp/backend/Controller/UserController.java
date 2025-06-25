package com.dashboard_erp.backend.Controller;

import com.dashboard_erp.backend.Entity.User;
import com.dashboard_erp.backend.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> addUser(@Valid @RequestBody User user) {
        try{
        userService.save(user);
        return ResponseEntity.ok(user);
        }
        catch(RuntimeException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getAllUser() {
        try{
            List<User> users= this.userService.getUsers();
            return ResponseEntity.ok().body(users);
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getUserById(@PathVariable("id") int id) {
        try{
            User user= this.userService.getUserById(id);
            return ResponseEntity.ok(user);
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> deleteUserById(@PathVariable("id") int id) {
        try{
            this.userService.deleteUserById(id);
            return ResponseEntity.ok("user deleted with success");
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<?> updateUserById(@PathVariable("id") int id,@RequestBody User user) {
        try{
            User updatedUser= this.userService.updateUser(id, user);
            return ResponseEntity.ok(updatedUser);
        }catch (RuntimeException e){
            if(ResponseEntity.status(HttpStatus.CONFLICT).equals(HttpStatus.CONFLICT)){
                return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
            }
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

    }
}
