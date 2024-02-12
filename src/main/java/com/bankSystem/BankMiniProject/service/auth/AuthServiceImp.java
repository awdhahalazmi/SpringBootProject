package com.bankSystem.BankMiniProject.service.auth;

import com.bankSystem.BankMiniProject.bo.auth.AuthenticationResponse;
import com.bankSystem.BankMiniProject.bo.auth.CreateLoginRequest;
import com.bankSystem.BankMiniProject.bo.auth.LogoutResponse;
import com.bankSystem.BankMiniProject.bo.customUserDetail.CustomUserDetails;
import com.bankSystem.BankMiniProject.bo.user.CreateSignUpRequest;
import com.bankSystem.BankMiniProject.config.JWTUtil;
import com.bankSystem.BankMiniProject.entity.RoleEntity;
import com.bankSystem.BankMiniProject.entity.UserEntity;
import com.bankSystem.BankMiniProject.repository.RoleRepository;
import com.bankSystem.BankMiniProject.repository.UserRepository;
import com.bankSystem.BankMiniProject.util.enums.Roles;
import com.bankSystem.BankMiniProject.util.enums.Status;
import com.bankSystem.BankMiniProject.util.exception.BodyGuardException;
import com.bankSystem.BankMiniProject.util.exception.UserNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImp implements AuthService{
    private final AuthenticationManager authenticationManager;
    private final CustomUserService userDetailService;
    private final JWTUtil jwtUtil;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private  final RoleRepository roleRepository;
    private final UserRepository userRepository;

    public AuthServiceImp(AuthenticationManager authenticationManager, CustomUserService userDetailService, JWTUtil jwtUtil, BCryptPasswordEncoder bCryptPasswordEncoder, RoleRepository roleRepository, UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.userDetailService = userDetailService;
        this.jwtUtil = jwtUtil;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void signup(CreateSignUpRequest createSignupRequest) {
        RoleEntity roleEntity= roleRepository.findRoleEntityByTitle(Roles.user.name())
                .orElseThrow(() -> new BodyGuardException("no Roles Found"));;
        UserEntity user= new UserEntity();
        user.setUsername(createSignupRequest.getUsername());
        user.setPhoneNumber(createSignupRequest.getPhonenumber());
        user.setEmail(createSignupRequest.getEmail());
        user.setRole(roleEntity);
        user.setStatus(Status.ACTIVE);
        user.setPassword(bCryptPasswordEncoder.encode(createSignupRequest.getPassword()));
        userRepository.save(user);
    }


    @Override
    public AuthenticationResponse login(CreateLoginRequest createLoginRequest) {
        requiredNonNull(createLoginRequest.getUsername(),"username");
        requiredNonNull(createLoginRequest.getPassword(),"password");

        String username= createLoginRequest.getUsername().toLowerCase();
        String password= createLoginRequest.getPassword();
        authentication(username,password);

        CustomUserDetails userDetails = userDetailService.loadUserByUsername(username);
        String accessToken = jwtUtil.generateToken(userDetails);
        AuthenticationResponse response = new AuthenticationResponse();
        response.setId(userDetails.getId());
        response.setUsername(userDetails.getUsername());
        response.setRole(userDetails.getRole());
        response.setToken("Bearer"+ accessToken);
        return response;



    }

    @Override
    public void logout(LogoutResponse logoutResponse) {
        requiredNonNull(logoutResponse.getToken(),"Token");

    }
    private void requiredNonNull(Object obj,String name){
        if(obj == null || obj.toString().isEmpty()){
            throw new BodyGuardException(name+"can not be empty");
        }
    }
    private void authentication(String username, String password){
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,password));
        }catch (BodyGuardException e){
            throw new BodyGuardException("Incorrect password");
        }catch (AuthenticationServiceException e){
            throw new UserNotFoundException("Incorrect username");
        }
    }
}
