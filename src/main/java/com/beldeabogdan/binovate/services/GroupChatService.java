package com.beldeabogdan.binovate.services;

import com.beldeabogdan.binovate.factories.ErrorResponseFactory;
import com.beldeabogdan.binovate.models.GroupChat;
import com.beldeabogdan.binovate.repositories.GroupChatRepository;
import com.beldeabogdan.binovate.utils.StringIdParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GroupChatService {

    private final GroupChatRepository groupChatRepository;

    @Autowired
    public GroupChatService(GroupChatRepository groupChatRepository) {
        this.groupChatRepository = groupChatRepository;
    }

    public GroupChat createOne(GroupChat groupChat) {
        return groupChatRepository.save(groupChat);
    }

    public GroupChat findById(@NonNull Integer id) {
        Optional<GroupChat> byId = groupChatRepository.findById(id);

        if (!byId.isPresent()) {
            throw ErrorResponseFactory.genericUnprocessableEntity();
        }

        return byId.get();
    }

    public GroupChat findById(@NonNull String idString) {
        final int id = StringIdParser.parse(idString);

        return findById(id);
    }
}
