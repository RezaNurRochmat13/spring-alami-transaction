package com.alami.transaction.service;

import com.alami.transaction.entity.User;
import com.alami.transaction.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public Page<User> doFindAllUsersPagination(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public User doCreateNewUsers(User user) {
        return userRepository.save(user);
    }
}
