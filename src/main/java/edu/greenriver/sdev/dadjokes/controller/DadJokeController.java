package edu.greenriver.sdev.dadjokes.controller;


import edu.greenriver.sdev.dadjokes.domain.DadJoke;
import edu.greenriver.sdev.dadjokes.service.DadJokeService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;

@RestController
public class DadJokeController {
    private DadJokeService service;

    public DadJokeController(DadJokeService service) {
        this.service = service;
    }

    @GetMapping("/jokes")
    public List<DadJoke> getAll() {
        return service.getAll();
    }

    @PostMapping("/jokes")
    public void addJoke(@RequestBody DadJoke joke) {
        service.add(joke);
    }

    @PutMapping("/jokes/{id}")
    public void updateJoke(@PathVariable int id, @RequestBody DadJoke joke) {
        service.update(id, joke.getJokeText());
    }

    @DeleteMapping("/jokes/{id}")
    public void deleteJoke(@PathVariable int id) {
        service.delete(id);
    }


    @GetMapping("/jokes/{id}")
    public DadJoke getAll(@PathVariable int id, HttpServletResponse response) {
        try {
            return service.byId(id);
        } catch (NoSuchElementException exception) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return null;
        }
    }

    @GetMapping("/jokes/random")
    public DadJoke getRandom() {
        Random rand = new Random();
        List<DadJoke> jokes = service.getAll();
        int index = rand.nextInt(jokes.size());
        return jokes.get(index);
    }
}
