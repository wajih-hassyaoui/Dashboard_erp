package com.dashboard_erp.backend.Controller;

import com.dashboard_erp.backend.Config.UserAuthProvider;
import com.dashboard_erp.backend.DTO.UserCredentialsDto;
import com.dashboard_erp.backend.DTO.UserDto;
import com.dashboard_erp.backend.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    private final UserService userService;
    private final UserAuthProvider userAuthProvider;
    public AuthController(UserService userService, UserAuthProvider userAuthProvider) {
        this.userService = userService;
        this.userAuthProvider = userAuthProvider;
    }
    @PostMapping("/login")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<?> login(@Valid @RequestBody UserCredentialsDto userCredentialsDto) {
        try{
         UserDto user = userService.login(userCredentialsDto);
         user.setToken(userAuthProvider.createToken(user));
        return  ResponseEntity.ok(user);
        }catch (RuntimeException e){
            if(ResponseEntity.status(HttpStatus.UNAUTHORIZED).equals(HttpStatus.UNAUTHORIZED)){
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("e.getMessage()");
            }
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());

        }
    }
}
