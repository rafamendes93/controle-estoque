package br.com.rafael.controleestoque.mappers;

public interface Mapper<ENTITY, DTO> {

    ENTITY toEntity(DTO dto) throws Throwable;

    DTO toDto(ENTITY entity);
}
