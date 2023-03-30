package com.innowise.employeeserviceee.service.impl;

import com.innowise.employeeserviceee.dto.UserCard;
import com.innowise.employeeserviceee.dto.UserDTO;
import com.innowise.employeeserviceee.dto.converter.UserCardConverter;
import com.innowise.employeeserviceee.dto.converter.UserConverter;
import com.innowise.employeeserviceee.entity.User;
import com.innowise.employeeserviceee.exception.AlreadyRegisteredException;
import com.innowise.employeeserviceee.exception.NoSuchRecordException;
import com.innowise.employeeserviceee.exception.UsernameNotFoundException;
import com.innowise.employeeserviceee.repository.AuthorityRepository;
import com.innowise.employeeserviceee.repository.UserRepository;
import com.innowise.employeeserviceee.security.EncryptService;
import com.innowise.employeeserviceee.security.TokenService;
import com.innowise.employeeserviceee.service.UserService;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Objects;
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
    public UserDTO addUser(UserDTO userDTO){
        User entityDTO = converter.toEntity(userDTO);
        if (userRepository.findByUsername(entityDTO.getUsername()) != null) {
            throw new AlreadyRegisteredException("User with this username already exists");
        }
        if (authorityRepository.findById(userDTO.getAuthorityId()) == null) {
            throw new NoSuchRecordException("There is no authority with id " + userDTO.getAuthorityId());
        }
        entityDTO.setPassword(EncryptService.hashPassword(entityDTO.getPassword()));
        User returnedEntity = userRepository.save(entityDTO);
        UserDTO convertedEntity = converter.toDTO(returnedEntity);
        return convertedEntity;
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
        return userCardConverter.toDTO(userRepository.findById(id));
    }

    @Override
    public UserDTO findByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (Objects.isNull(user)) {
            throw new UsernameNotFoundException(String.format("User %s is not found", username));
        }

        return converter.toDTO(user);
    }

    @Override
    public boolean checkCredentials(UserDTO userDTO) {
        User user = userRepository.findByUsername(userDTO.getUsername());
        return user != null && EncryptService.checkPassword(userDTO.getPassword(), user.getPassword());
    }

    @Override
    public String generateToken(UserDTO userDTO) {
        User user = userRepository.findByUsername(userDTO.getUsername());
        return jwtTokenService.generateToken(user.getUsername(), user.getAuthorityName());
    }

}
