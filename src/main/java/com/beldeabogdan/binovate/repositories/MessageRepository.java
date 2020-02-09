package com.beldeabogdan.binovate.repositories;

import com.beldeabogdan.binovate.models.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Integer> {
}
