package com.faturamentofrete.frete.repository;


import com.faturamentofrete.frete.entity.CustoFixo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustoFixoRepository extends JpaRepository<CustoFixo, Long> {

}
