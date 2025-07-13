package com.lendrack.lend_rack.mapper;

import com.lendrack.lend_rack.model.domain.Author;
import com.lendrack.lend_rack.model.dto.CreateAuthorRequest;
import com.lendrack.lend_rack.model.dto.UpdateAuthorRequest;
import com.lendrack.lend_rack.persistance.entity.AuthorEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class AuthorMapper {
    public Author EntityToDomain(AuthorEntity authorEntity) {
        Author author = new Author();
        BeanUtils.copyProperties(authorEntity, author);
        return author;
    }

    public AuthorEntity createRequestToEntity(CreateAuthorRequest request) {
        AuthorEntity authorEntity = new AuthorEntity();
        BeanUtils.copyProperties(request, authorEntity);
        return authorEntity;
    }

    public AuthorEntity updateRequestToEntity(UpdateAuthorRequest request, AuthorEntity authorEntity) {
        authorEntity.setFullName(request.fullName());
        authorEntity.setBio(request.bio());
        authorEntity.setNationality(request.nationality());
        authorEntity.setBirthDate(request.birthDate());
        authorEntity.setNationality(request.nationality());
        return authorEntity;
    }
}
