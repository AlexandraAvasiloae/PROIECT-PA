package com.example.demo.dao;

import com.example.demo.model.Player;

import java.util.List;
import java.util.Optional;
import java.util.Random;

public interface PlayerDAO {

    /**
     * this method allows to insert a person with a given id
     * @param id
     * @param name
     * @return
     */
    int insertPlayer(int id, String name);

    /**
     * this method allows to add a player without the id and the id is generated random
     * @param player
     * @return
     */
    default int insertPlayer(Player player){
        Random rand=new Random();
        int upperbound = 1000;
        int id=rand.nextInt(upperbound);
        return insertPlayer(id, player.getName());
    }

    List<Player> selectAllPlayers();
    Optional<Player> selectPlayerById(int id);

    /**
     * this method allows to delete a player knowing the id
     * @param id
     * @return
     */
    int deletePlayerById(int id);

    /**
     * this method allows to update a player knowing the id
     * @param id
     * @param player
     * @return
     */
    int updatePlayerById(int id, Player player);
}
