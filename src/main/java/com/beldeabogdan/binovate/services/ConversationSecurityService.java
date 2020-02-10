package com.beldeabogdan.binovate.services;

import com.beldeabogdan.binovate.factories.ErrorResponseFactory;
import com.beldeabogdan.binovate.models.GroupChat;
import com.beldeabogdan.binovate.models.User;
import com.beldeabogdan.binovate.models.UserGroupChatMapping;
import com.beldeabogdan.binovate.repositories.UserGroupChatMappingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

@Service
public class ConversationSecurityService {

    private final UserGroupChatMappingRepository userGroupChatMappingRepository;

    @Autowired
    public ConversationSecurityService(UserGroupChatMappingRepository userGroupChatMappingRepository) {
        this.userGroupChatMappingRepository = userGroupChatMappingRepository;
    }

    public void assertUserInGroup(@Nullable User user, @Nullable GroupChat groupChat) {
        assertUserInGroup(
                (null != user) ? user.getId() : null,
                (null != groupChat) ? groupChat.getId() : null
        );
    }

    public void assertUserInGroup(@Nullable Integer userId, @Nullable Integer groupChatId) {
        if (null == userId || null == groupChatId) {
            throw ErrorResponseFactory.genericForbidden();
        }

        UserGroupChatMapping mapping = userGroupChatMappingRepository.findByUserAndGroupChat(userId, groupChatId);

        if (null == mapping) {
            throw ErrorResponseFactory.genericForbidden();
        }
    }
}
