package io.github.hayltondev.agendaapi.api.rest;

import io.github.hayltondev.agendaapi.model.entity.Contato;
import io.github.hayltondev.agendaapi.model.repository.ContatoRepository;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Part;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/contatos")
@RequiredArgsConstructor
@CrossOrigin("*")
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
    public void favoritar(@PathVariable Integer id){
        Optional<Contato> contato = repository.findById(id);
        contato.ifPresent(c -> {
            Boolean favorito = c.getFavorito() == Boolean.TRUE;
            c.setFavorito(!favorito);
            repository.save(c);
        });
    }

    @PatchMapping("{id}/foto")
    public byte[] adicionarFoto(@PathVariable Integer id, @RequestParam("foto") Part arquivo){
        Optional<Contato> contato = repository.findById(id);
        return contato.map(c ->{
           try {
               InputStream is = arquivo.getInputStream();
               byte[] bytes = new byte[(int) arquivo.getSize()];
               IOUtils.readFully(is, bytes);
               c.setFoto(bytes);
               repository.save(c);
               is.close();
               return bytes;
           }catch (Exception e){
                return null;
           }
        }).orElse(null);
    }
}
