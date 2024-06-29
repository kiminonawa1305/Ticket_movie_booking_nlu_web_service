package com.lamnguyen.server.services.impl;

import com.lamnguyen.server.exceptions.ApplicationException;
import com.lamnguyen.server.models.dto.UserDTO;
import com.lamnguyen.server.models.entity.User;
import com.lamnguyen.server.repositories.UserRepository;
import com.lamnguyen.server.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;


    @Override
    public UserDTO register(User user) {
        user.setLock(false);
        return convert(userRepository.saveAndFlush(user));
    }

    @Override
    public UserDTO findByEmail(String email) {
        if (userRepository.findByEmail(email) == null)
            throw new ApplicationException(ApplicationException.ErrorCode.USER_NON_EXIST);
        return convert(userRepository.findByEmail(email));
    }

    private UserDTO convert(User user) {
        return modelMapper.map(user, UserDTO.class);
    }
}
