
package com.projeto_casaCultural.cinema.data;

import com.projeto_casaCultural.cinema.model.Filme;
import com.projeto_casaCultural.cinema.repositorio.FilmeRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


//Classe criada apenas para fazer requisicoes no Postman, sem fazer conflito com a de thymeleaf.
@RestController
@RequestMapping("/api/filmes")
public class FilmeApiController {

    @Autowired
    private FilmeRepository filmeRepository;

    // GET /api/filmes - lista todos os filmes
    @GetMapping
    public List<Filme> listarFilmes() {
        return filmeRepository.findAll();
    }

    // GET /api/filmes/{id} - retorna filme por id
    @GetMapping("/{id}")
    public Filme buscarFilme(@PathVariable Long id) {
        return filmeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Filme não encontrado com id " + id));
    }

    // POST /api/filmes - cria um novo filme
    @PostMapping
    public Filme criarFilme(@RequestBody Filme filme) {
        return filmeRepository.save(filme);
    }

    // PUT /api/filmes/{id} - atualiza um filme existente
    @PutMapping("/{id}")
    public Filme atualizarFilme(@PathVariable Long id, @RequestBody Filme filmeAtualizado) {
        return filmeRepository.findById(id).map(filme -> {
            filme.setTitulo(filmeAtualizado.getTitulo());
            filme.setGenero(filmeAtualizado.getGenero());
            filme.setAnoLancamento(filmeAtualizado.getAnoLancamento());
            return filmeRepository.save(filme);
        }).orElseThrow(() -> new RuntimeException("Filme não encontrado com id " + id));
    }

    // DELETE /api/filmes/{id} - deleta um filme
    @DeleteMapping("/{id}")
    public String deletarFilme(@PathVariable Long id) {
        Optional<Filme> filme = filmeRepository.findById(id);
        if (filme.isPresent()) {
            filmeRepository.delete(filme.get());
            return "Filme deletado com sucesso";
        } else {
            throw new RuntimeException("Filme não encontrado com id " + id);
        }
    }
}
