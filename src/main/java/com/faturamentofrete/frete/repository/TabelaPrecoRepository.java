package com.faturamentofrete.frete.repository;

import com.faturamentofrete.frete.entity.TabelaPreco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TabelaPrecoRepository extends JpaRepository<TabelaPreco, Long> {

}
