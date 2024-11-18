package com.github.marciokleber.springelasticsearch.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Data
@Document(indexName = "produtos")
public class Produto {
    @Id
    private Long id;

    @Field(type = FieldType.Text)
    private String nome;

    @Field(type = FieldType.Double)
    private Double preco;

}