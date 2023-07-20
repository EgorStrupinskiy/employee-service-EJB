package com.strupinski.employeeserviceee.service.impl;

import com.strupinski.employeeserviceee.dto.UserCard;
import com.strupinski.employeeserviceee.dto.UserDTO;
import com.strupinski.employeeserviceee.dto.converter.UserCardConverter;
import com.strupinski.employeeserviceee.dto.converter.UserConverter;
import com.strupinski.employeeserviceee.entity.User;
import com.strupinski.employeeserviceee.exception.AlreadyRegisteredException;
import com.strupinski.employeeserviceee.exception.NoSuchRecordException;
import com.strupinski.employeeserviceee.exception.ServerException;
import com.strupinski.employeeserviceee.exception.UsernameNotFoundException;
import com.strupinski.employeeserviceee.repository.AuthorityRepository;
import com.strupinski.employeeserviceee.repository.UserRepository;
import com.strupinski.employeeserviceee.security.EncryptService;
import com.strupinski.employeeserviceee.security.TokenService;
import com.strupinski.employeeserviceee.service.UserService;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Stateless
@NoArgsConstructor
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    @EJB
    private UserConverter converter;
    @EJB
    private UserRepository userRepository;
    @EJB
    private AuthorityRepository authorityRepository;
    @EJB
    private UserCardConverter userCardConverter;
    @EJB
    private TokenService jwtTokenService;

    @Override
    public UserDTO addUser(UserDTO userDTO) {
        User entityDTO = converter.toEntity(userDTO);
        if (userRepository.findByUsername(entityDTO.getUsername()).isPresent()) {
            throw new AlreadyRegisteredException("user/", "User with this username already exists");
        }
        if (authorityRepository.findById(userDTO.getAuthorityId()).isPresent()) {
            throw new NoSuchRecordException("user/", "There is no authority with id " + userDTO.getAuthorityId());
        }
        entityDTO.setPassword(EncryptService.hashPassword(entityDTO.getPassword()));
        return converter.toDTO(userRepository.save(entityDTO).orElseThrow(() -> new ServerException("/departments", "error while adding new department")));
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Transactional
    public List<UserCard> findAll() {
        return userRepository.findAll()
                .stream()
                .map(userCardConverter::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UserCard findById(Long id) {
        return userCardConverter.toDTO(userRepository.findById(id).orElseThrow(() -> new NoSuchRecordException("user/", "User with with this id not found" + id)));
    }

    @Override
    public UserDTO findByUsername(String username) throws UsernameNotFoundException {
        return converter.toDTO(userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("user/", String.format("User %s is not found", username))));
    }

    @Override
    public boolean isAuthenticated(UserDTO userDTO) {
        return userRepository.findByUsername(userDTO.getUsername()).isPresent() &&
                EncryptService.checkPassword(userDTO.getPassword(),
                        userRepository.findByUsername(userDTO.getUsername()).get().getPassword());
    }

    @Override
    public String generateToken(UserDTO userDTO) {
        User user = userRepository.findByUsername(userDTO.getUsername()).orElseThrow(() -> new NoSuchRecordException("user/", "User with with this username not found: " + userDTO.getUsername()));
        return jwtTokenService.generateToken(user.getUsername(), user.getAuthorityName());
    }

}
