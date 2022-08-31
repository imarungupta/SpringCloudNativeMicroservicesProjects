package com.spring.cloud.employee.api.service;

import com.spring.cloud.employee.api.DTO.JwtRequestDTO;
import com.spring.cloud.employee.api.DTO.JwtResponseDTO;
import com.spring.cloud.employee.api.entity.Employee;
import com.spring.cloud.employee.api.entity.Role;
import com.spring.cloud.employee.api.repository.EmployeeRepository;
import com.spring.cloud.employee.api.repository.RoleRepository;
import com.spring.cloud.employee.api.usernameandclaimfromtoken.UserNameAndClaimFromToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class JwtService implements UserDetailsService {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserNameAndClaimFromToken userNameAndClaimFromToken;
    @Autowired
    private AuthenticationManager authenticationManager;

    public JwtResponseDTO createJwtToken(JwtRequestDTO jwtRequestDTO) {

        String username = jwtRequestDTO.getUsername();
        String password = jwtRequestDTO.getPassword();

        authenticateUserNamePassword(username, password);

        final UserDetails userDetails = loadUserByUsername(username);

        String generatedNewToken = userNameAndClaimFromToken.generateTokenFromUserDetail(userDetails);
        /*String generatedNewToken = userNameAndClaimFromToken
                .getUserNameFromToken(String.valueOf(userDetails));
*/
        Employee employee= employeeRepository.findByEmail(username);

       return new JwtResponseDTO(employee,generatedNewToken);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Employee employee = employeeRepository.findByEmail(username);
        if (employee != null) {
            return new User(employee.getEmail(),
                       employee.getEmpPassword(),
                       Collections.singleton(getAuthorities(employee)));
        } else {
            throw new UsernameNotFoundException("User name is not valid");
        }
    }

    private SimpleGrantedAuthority getAuthorities(Employee employee) {
        //Set authority= new HashSet();
        Role rOleByRoleId = roleRepository.findROleByRoleId(employee.getRoleId()); //"ROLE_".trim() +
        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(rOleByRoleId.getRole());
        return simpleGrantedAuthority;
    }

    // Authenticate the username and password using spring inbuilt method AuthenticationManager
    private void authenticateUserNamePassword(String username, String password) {
        // Inbuilt standard method
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException disabledException) {
            throw new DisabledException("User is disabled");
        } catch (BadCredentialsException badCredentialsException) {
            throw new BadCredentialsException("Bad username or password");
        }
    }


}
