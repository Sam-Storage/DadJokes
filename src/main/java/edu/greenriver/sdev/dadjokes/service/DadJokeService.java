package edu.greenriver.sdev.dadjokes.service;

import edu.greenriver.sdev.dadjokes.data.DadJokeRepository;
import edu.greenriver.sdev.dadjokes.domain.DadJoke;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DadJokeService {
    private DadJokeRepository repo;

    public DadJokeService(DadJokeRepository repo) {
        this.repo = repo;
    }

    public List<DadJoke> getAll() {
        return repo.findAll();
    }

    public void add(DadJoke joke) {
        repo.save(joke);
    }

    public void update(int id, String newJokeText) {
        DadJoke joke = repo.findById(id).orElse(null);
        if (joke == null) return;

        joke.setJokeText(newJokeText);
        repo.save(joke);
    }

    public void delete(int id) {
//        DadJoke joke = repo.findById(id).orElse(null);
//        if (joke == null) return;
//
        repo.deleteById(id);
    }

    public DadJoke byId(int id) {
        return repo.findById(id).orElseThrow();
    }
}
