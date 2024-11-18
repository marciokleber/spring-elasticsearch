package com.github.marciokleber.springelasticsearch.service;

import com.github.marciokleber.springelasticsearch.domain.Produto;
import com.github.marciokleber.springelasticsearch.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProdutoService {
    private final ElasticsearchOperations elasticsearchOperations;
    private final ProdutoRepository produtoRepository;

    // Salvar produto
    public Produto salvar(Produto produto) {
        return produtoRepository.save(produto);
    }

    // Buscar por nome usando ElasticsearchOperations
    public List<Produto> buscarPorNome(String nome) {
        Criteria criteria = new Criteria("nome").contains(nome);
        Query query = new CriteriaQuery(criteria);

        SearchHits<Produto> searchHits = elasticsearchOperations.search(query, Produto.class);
        return searchHits.stream()
                .map(SearchHit::getContent)
                .collect(Collectors.toList());
    }

    // Buscar todos com paginação
    public Page<Produto> buscarTodos(int pagina, int tamanho) {
        Pageable pageable = PageRequest.of(pagina, tamanho);
        return produtoRepository.findAll(pageable);
    }
}
