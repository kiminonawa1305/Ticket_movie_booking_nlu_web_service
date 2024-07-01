package com.lamnguyen.server.services.impl;

import com.lamnguyen.server.enums.RoleUser;
import com.lamnguyen.server.exceptions.ApplicationException;
import com.lamnguyen.server.models.dto.UserDTO;
import com.lamnguyen.server.models.entity.User;
import com.lamnguyen.server.repositories.UserRepository;
import com.lamnguyen.server.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;


    @Override
    public UserDTO register(User user) {
        user.setLock(false);
        user.setRole(RoleUser.USER);
        return convert(userRepository.saveAndFlush(user));
    }

    @Override
    public UserDTO login(String email) {
        User user = userRepository.findByEmail(email);
        if (user == null)
            throw new ApplicationException(ApplicationException.ErrorCode.USER_NON_EXIST);
        if (user.isLock())
            throw new ApplicationException(ApplicationException.ErrorCode.USER_IS_BLOCKED);
        return convert(user);
    }

    @Override
    public List<UserDTO> findAll() {
        return userRepository.findAll().stream().map(this::convert).toList();
    }

    @Override
    public UserDTO lock(Integer userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ApplicationException(ApplicationException.ErrorCode.USER_NON_EXIST));
        user.setLock(!user.isLock());
        return convert(userRepository.saveAndFlush(user));
    }

    private UserDTO convert(User user) {
        return modelMapper.map(user, UserDTO.class);
    }
}
