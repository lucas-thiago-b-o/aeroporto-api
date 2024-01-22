package br.com.companhia.aeroporto.model;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ModelMapping<Entity, Dto> {

    private final ModelMapper modelMapper = new ModelMapper();

    public Dto convertToDto(Entity entity, Class<Dto> dtoClass) {
        return modelMapper.map(entity, dtoClass);
    }

    public Entity convertToEntity(Dto dto, Class<Entity> entityClass) { return modelMapper.map(dto, entityClass); }

    public List<Dto> convertToDtoList(List<Entity> entityList, Class<Dto> dtoClass) {
        return entityList.stream()
                .map(entity -> convertToDto(entity, dtoClass))
                .collect(Collectors.toList());
    }

    public List<Entity> convertToEntityList(List<Dto> dtoList, Class<Entity> entityClass) {
        return dtoList.stream()
                .map(dto -> convertToEntity(dto, entityClass))
                .collect(Collectors.toList());
    }

}
