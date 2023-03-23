package com.sparta.spartamongodbfinalproject.security.auth;

import com.sparta.spartamongodbfinalproject.model.entities.SecurityUser;
import com.sparta.spartamongodbfinalproject.model.entities.Session;
import com.sparta.spartamongodbfinalproject.model.entities.roles.ERole;
import com.sparta.spartamongodbfinalproject.model.entities.roles.Role;
import com.sparta.spartamongodbfinalproject.model.repositories.RoleRepository;
import com.sparta.spartamongodbfinalproject.model.repositories.SessionRepository;
import com.sparta.spartamongodbfinalproject.security.config.JwtService;
import com.sparta.spartamongodbfinalproject.model.entities.User;
import com.sparta.spartamongodbfinalproject.model.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.lang.StringBuilder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.*;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
  private final UserRepository repository;
  private final SessionRepository sessionRepository;

  private final RoleRepository roleRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;


  public AuthenticationResponse register(RegisterRequest request) {
    var user =  new User();
    user.setName(request.getName());
    user.setEmail(request.getEmail());
    user.setPassword(passwordEncoder.encode(request.getPassword()));
    Optional<Role> role = roleRepository.findByName(ERole.ROLE_BASIC);
    user.setRoles(Set.of(role.get()));
    var savedUser = repository.save(user);
    UserDetails userDetails = new SecurityUser(user);
    var jwtToken = jwtService.generateToken(userDetails);
    saveUserToken(savedUser, jwtToken);
    return AuthenticationResponse.builder()
        .token(jwtToken)
        .build();
  }

  public AuthenticationResponse authenticate(AuthenticationRequest request) {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            request.getEmail(),
            request.getPassword()
        )
    );
    var user = repository.findByEmail(request.getEmail())
        .orElseThrow();
    UserDetails userDetails = new SecurityUser(user);
    var jwtToken = jwtService.generateToken(userDetails);
    revokeAllUserTokens(user);
    saveUserToken(user, jwtToken);
    return AuthenticationResponse.builder()
        .token(jwtToken)
        .build();
  }

  private void saveUserToken(User user, String jwtToken) {
    Session session = new Session();
    String user_id = "random";
    try {
      Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
      SecretKey secretKey = new SecretKeySpec("8S4!WGfL2,mqpz9KrJCf,CJbX[kwa{6?".getBytes(), "AES");
      cipher.init(Cipher.ENCRYPT_MODE, secretKey);
      user_id = new String(cipher.doFinal(user.getEmail().getBytes()));
    } catch (Exception e) {
      e.printStackTrace();
    }
    session.setUser_id(user_id);
    System.out.println("what is it" + user_id);
    session.setJwt(jwtToken);
    sessionRepository.save(session);
  }

  private void revokeAllUserTokens(User user) {

    String user_id = "random";
    try {
      Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
      SecretKey secretKey = new SecretKeySpec("8S4!WGfL2,mqpz9KrJCf,CJbX[kwa{6?".getBytes(), "AES");
      cipher.init(Cipher.ENCRYPT_MODE, secretKey);
      user_id = new String(cipher.doFinal(user.getEmail().getBytes()));
    } catch (Exception e) {
      e.printStackTrace();
    }
    sessionRepository.deleteByUser_id(user_id);
  }
}
