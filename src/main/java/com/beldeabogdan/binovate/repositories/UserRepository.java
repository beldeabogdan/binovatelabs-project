package com.beldeabogdan.binovate.repositories;

import com.beldeabogdan.binovate.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository  extends JpaRepository<User, Integer> {
}
