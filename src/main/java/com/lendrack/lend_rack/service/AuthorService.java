package com.lendrack.lend_rack.service;

import com.lendrack.lend_rack.exception.custom.NotFoundException;
import com.lendrack.lend_rack.mapper.AuthorMapper;
import com.lendrack.lend_rack.model.domain.Author;
import com.lendrack.lend_rack.model.dto.CreateAuthorRequest;
import com.lendrack.lend_rack.model.dto.UpdateAuthorRequest;
import com.lendrack.lend_rack.persistance.entity.AuthorEntity;
import com.lendrack.lend_rack.persistance.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorService {
    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;

    public List<Author> findAllAuthors(Pageable pageable) {
        List<AuthorEntity> authorEntities = authorRepository.findAll();
        return authorEntities.stream().map(authorMapper::EntityToDomain).toList();
    }

    public Long create(CreateAuthorRequest request) {
        var authorToSave = authorMapper.createRequestToEntity(request);
        var savedAuthor = authorRepository.save(authorToSave);
        return savedAuthor.getId();
    }

    public AuthorEntity findEntityById(Long id) throws NotFoundException {
        return authorRepository.findById(id).orElseThrow(()-> new NotFoundException("Author not found"));
    }

    public void update(Long id, UpdateAuthorRequest request) throws NotFoundException {
        AuthorEntity authorToUpdate = findEntityById(id);
        AuthorEntity updatedAuthor = authorMapper.updateRequestToEntity(request, authorToUpdate);
        authorRepository.save(updatedAuthor);
    }

    public void delete(Long id) throws NotFoundException {
        AuthorEntity authorToDelete = findEntityById(id);
        authorRepository.delete(authorToDelete);
    }
}
