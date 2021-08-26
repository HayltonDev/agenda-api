package io.github.hayltondev.agendaapi.model.repository;

import io.github.hayltondev.agendaapi.model.entity.Contato;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContatoRepository extends JpaRepository<Contato, Integer> {
}
