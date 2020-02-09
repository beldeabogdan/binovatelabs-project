package com.beldeabogdan.binovate.services;

import com.beldeabogdan.binovate.models.GroupChat;
import com.beldeabogdan.binovate.models.User;
import com.beldeabogdan.binovate.models.UserGroupChatMapping;
import com.beldeabogdan.binovate.repositories.UserGroupChatMappingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserGroupChatMappingService {

    private final UserGroupChatMappingRepository repository;

    @Autowired
    public UserGroupChatMappingService(UserGroupChatMappingRepository repository) {
        this.repository = repository;
    }

    public UserGroupChatMapping createMapping(User user, GroupChat groupChat) {
        UserGroupChatMapping userGroupChatMapping = new UserGroupChatMapping()
                .setGroupChat(groupChat)
                .setUser(user);

        return repository.save(userGroupChatMapping);
    }

    public void removeMapping(User user, GroupChat groupChat) {
        UserGroupChatMapping mapping = repository.findByUserAndGroupChat(user.getId(), groupChat.getId());

        repository.delete(mapping);
    }
}
