package com.example.demo.dao;

import com.example.demo.model.Player;

import java.util.List;
import java.util.Optional;
import java.util.Random;

public interface PlayerDAO {

    int insertPlayer(int id, String name);

    default int insertPlayer(Player player){
        Random rand=new Random();
        int upperbound = 1000;
        int id=rand.nextInt(upperbound);
        return insertPlayer(id, player.getName());
    }

    List<Player> selectAllPlayers();
    Optional<Player> selectPlayerById(int id);

    int deletePlayerById(int id);

    int updatePlayerById(int id, Player player);
}
