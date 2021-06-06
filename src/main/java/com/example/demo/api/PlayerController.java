package com.example.demo.api;

import com.example.demo.game.Game;
import com.example.demo.game.GenerateAnagrams;
import com.example.demo.game.RandomLetters;
import com.example.demo.model.Player;
import com.example.demo.service.PlayerService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/player")
@RestController
public class PlayerController {

    private PlayerService playerService;
    @Autowired
    private PlayerRepository playerRepository;
    private List<String> letters;
    private String word;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @PostMapping
    public @ResponseBody
    Player addPerson(@NonNull @RequestBody Player player) {
        playerService.addPlayer(player);
        playerRepository.save(player);
        return player;
    }

    @GetMapping
    public List<Player> getAllPlayers() {
        return (List<Player>) playerRepository.findAll();
    }

    @GetMapping(path = "{id}")
    public Optional<Player> getPlayerById(@PathVariable("id") int id) {
        return playerRepository.findById(id);
    }

    @GetMapping(path="{id}/score")
    public String getPlayerScore(@PathVariable("id") int id){
        Optional<Player> player = playerRepository.findById(id);
        if (player.isPresent()) {
            int score=player.get().getScor();
            String playerName=player.get().getName();
            return "Scorul pentru "+playerName+" este "+score;
        }else{
            String message="Acest jucator nu exista!";
            return message;
        }
    }

    @GetMapping(path="{id}/play")
    public String playGame(@PathVariable("id") int id) {
        Optional<Player> player = playerRepository.findById(id);
        if (player.isPresent()) {
            RandomLetters choice = new RandomLetters();
            this.letters = choice.getChosenLetters();
            return "Literele sunt: "+letters;
        }else{
            String message="Acest jucator nu exista!";
            return message;
        }
    }

    public void scor(String word, int id){
        Optional<Player> player=playerRepository.findById(id);
        int oldScore=player.get().getScor();
        player.get().setScor(oldScore+word.length());
        playerRepository.save(player.get());
    }

    public String verifyWord(String word, List<String> letters) throws IOException {
        GenerateAnagrams anagram = new GenerateAnagrams();
        String possibleLetters = anagram.generateWord(letters);
        String verificareStatus="nu";
        if(verificareStatus!="Felicitari!"){
            Game game = new Game();
            verificareStatus=game.Game(possibleLetters, word);
        }
        return verificareStatus;
    }

    @GetMapping(path="{id}/play/{word}")
    public String chooseWord(@PathVariable("id") int id, @PathVariable("word") String word) throws IOException {
        Optional<Player> player=playerRepository.findById(id);
        playerRepository.save(player.get());
        this.word=word;
        String message=verifyWord(this.word,this.letters);

        if(message=="Felicitari!"){
            RandomLetters choice = new RandomLetters();
            this.letters = choice.getChosenLetters();
            scor(word,id);
            int score=player.get().getScor();
            return message+" Scorul tau este "+score+"! Literele sunt: "+this.letters;
        }else {
            return message+"! Incearca din nou! "+this.letters;
        }
    }

    @DeleteMapping(path = "{id}")
    public void deletePlayerById(@PathVariable("id") int id) {
        playerService.deletePlayer(id);
        if(playerRepository.existsById(id)){
            Player player = playerRepository.findById(id).get();
            playerRepository.deleteById(id);
        }
    }

    @PutMapping(path = "{id}")
    public void updatePlayer(@PathVariable("id") int id, @NonNull @RequestBody Player playerToUpdate) {
        playerService.updatePerson(id, playerToUpdate);
        if(playerRepository.existsById(id)){
            Player person = playerRepository.findById(id).get();
            person.setName(playerToUpdate.getName());
            person.setScor(5);
            playerRepository.save(person);
        }
    }
}
