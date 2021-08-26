package io.github.hayltondev.agendaapi.api.rest;

import io.github.hayltondev.agendaapi.model.entity.Contato;
import io.github.hayltondev.agendaapi.model.repository.ContatoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/contatos")
@RequiredArgsConstructor
public class ContatoController {

    private final ContatoRepository repository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Contato save(@RequestBody Contato contato){
        return repository.save(contato);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id){
        repository.deleteById(id);
    }

    @GetMapping
    public List<Contato> listarContatos(){
        return repository.findAll();
    }

    @PatchMapping("{id}/favorito")  //o patch mapping faz uma atulização parcial enquanto o putMapping faz uma atualização total
    public void favoritar(@PathVariable Integer id, @RequestBody Boolean favorito){
        Optional<Contato> contato = repository.findById(id);
        contato.ifPresent(c -> {
            c.setFavorito(favorito);
            repository.save(c);
        });
    }
}
