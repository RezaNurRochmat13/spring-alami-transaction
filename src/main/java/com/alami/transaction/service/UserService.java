package com.alami.transaction.service;

import com.alami.transaction.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;;

public interface UserService {
    Page<User> doFindAllUsersPagination(Pageable pageable);
    User doCreateNewUsers(User user);
}
