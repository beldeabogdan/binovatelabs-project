package com.beldeabogdan.binovate.services;

import com.beldeabogdan.binovate.factories.ErrorResponseFactory;
import com.beldeabogdan.binovate.models.User;
import com.beldeabogdan.binovate.repositories.UserRepository;
import com.beldeabogdan.binovate.utils.IntegerParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(@NonNull User user) {
        return userRepository.save(user);
    }

    public Page<User> findAll(int page, int itemsPerPage) {
        return userRepository.findAll(PageRequest.of(page, itemsPerPage));
    }

    public User findById(@NonNull Integer id) {
        Optional<User> byId = userRepository.findById(id);

        if (!byId.isPresent()) {
            throw ErrorResponseFactory.genericForbidden();
        }

        return byId.get();
    }

    public User findById(@NonNull String idString) {
        final int id = IntegerParser.parse(idString);

        return findById(id);
    }
}
