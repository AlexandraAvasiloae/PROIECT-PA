package com.example.demo.service;

import com.example.demo.dao.PlayerDAO;
import com.example.demo.model.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlayerService {

    private final PlayerDAO playerDAO;

    @Autowired
    public PlayerService(@Qualifier("playersDao") PlayerDAO playerDAO) {
        this.playerDAO = playerDAO;
    }

    public int addPlayer(Player player){
        return playerDAO.insertPlayer(player);
    }

    public List<Player> getAllPlayers(){
        return playerDAO.selectAllPlayers();
    }

    public Optional<Player> getPlayerById(int id){
        return playerDAO.selectPlayerById(id);
    }

    public int deletePlayer(int id){
        return playerDAO.deletePlayerById(id);
    }

    public int updatePerson(int id, Player newPlayer){
        return playerDAO.updatePlayerById(id, newPlayer);
    }
}
