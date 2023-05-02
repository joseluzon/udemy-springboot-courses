package com.github.joseluzon.udemy.springbootdevbootcamp.backenddev.model.mappers;

import org.mapstruct.Mapper;
import com.github.joseluzon.udemy.springbootdevbootcamp.backenddev.model.dao.ContactDao;
import com.github.joseluzon.udemy.springbootdevbootcamp.backenddev.model.dto.ContactDto;

@Mapper(componentModel = "spring")
public interface ContactMapper {
    ContactDto daoToDto(ContactDao dao);
    ContactDao dtoTodDao(ContactDto dto);
}
