package com.example.demo.dao;

import com.example.demo.model.Player;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Repository("playersDao")
public class PlayersDataAccessServer implements PlayerDAO {
    private static List<Player> DB = new ArrayList<>();

    @Override
    public int insertPlayer(@Param("id") int id, @Param("name") String name) {
        DB.add(new Player(id, name));
        return 1;
    }

    @Override
    public List<Player> selectAllPlayers() {
        return DB;
    }

    @Override
    public Optional<Player> selectPlayerById(int id) {
        return DB.stream()
                .filter(friend->friend.getId()==id)
                .findFirst();
    }

    @Override
    public int deletePlayerById(int id) {
        Optional<Player> friendsMaybe = selectPlayerById(id);
        if(friendsMaybe.isPresent()){
            DB.remove(friendsMaybe.get());
            return 1;
        }
        return 0;
    }

    @Override
    public int updatePlayerById(int id, Player update) {
        return selectPlayerById(id)
                .map(person->{
                    int indexOfPersonUpdate = DB.indexOf(person);
                    if(indexOfPersonUpdate>=0){
                        DB.set(indexOfPersonUpdate, new Player(id,update.getName()));
                        return 1;
                    }
                    return 0;
                })
                .orElse(0);
    }
}