package com.football.transfer.springboot_footballmanager.dao;

import com.football.transfer.springboot_footballmanager.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepo extends JpaRepository<Player, Integer> {

}
