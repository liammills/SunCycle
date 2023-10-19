package com.SunCycle.SunCycle.service;

import com.SunCycle.SunCycle.dto.LoginResponseDTO;
import com.SunCycle.SunCycle.model.User;
import com.SunCycle.SunCycle.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AuthenticationService  {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;


    public LoginResponseDTO registerUser(String username, String password){
        System.out.println("registering user");

        String encodedPassword = "{bcrypt}"+passwordEncoder.encode(password);

        User user = userRepository.save(new User(username, encodedPassword));

        try{
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );

            String token = tokenService.generateJwt(auth);

            return new LoginResponseDTO(user, token);
        }catch (AuthenticationException e) {
            System.out.println(e);
            return new LoginResponseDTO("register error");
        }

    }

    public LoginResponseDTO loginUser(String username, String password){
        System.out.println("logging in");

        try{
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );

            String token = tokenService.generateJwt(auth);

            return new LoginResponseDTO(userRepository.findByEmail(username).get(), token);

        } catch(AuthenticationException e){
            System.out.println(e);
            return new LoginResponseDTO("login error");
        }
    }

}
