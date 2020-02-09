package com.beldeabogdan.binovate.repositories;

import com.beldeabogdan.binovate.models.UserGroupChatMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;

public interface UserGroupChatMappingRepository extends JpaRepository<UserGroupChatMapping, Integer> {

    @Query("SELECT item FROM UserGroupChatMapping item WHERE item.user.id = :x_user AND item.groupChat.id = :x_group_chat")
    public UserGroupChatMapping findByUserAndGroupChat(
            @NonNull @Param("x_user") Integer userId,
            @NonNull @Param("x_group_chat") Integer groupChatId
    );
}
