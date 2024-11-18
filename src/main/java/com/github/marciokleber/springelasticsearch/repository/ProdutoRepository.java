package com.github.marciokleber.springelasticsearch.repository;

import com.github.marciokleber.springelasticsearch.domain.Produto;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepository extends ElasticsearchRepository<Produto, Long> {
    List<Produto> findByNome(String nome);
    List<Produto> findByPrecoLessThan(Double preco);
}