package com.beldeabogdan.binovate.repositories;

import com.beldeabogdan.binovate.models.GroupChat;
import com.beldeabogdan.binovate.models.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Integer> {

    public Page<Message> findAllByGroupChat(GroupChat groupChat, Pageable pageable);
}
