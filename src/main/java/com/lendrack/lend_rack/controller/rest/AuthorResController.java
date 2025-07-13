package com.lendrack.lend_rack.controller.rest;

import com.lendrack.lend_rack.exception.custom.NotFoundException;
import com.lendrack.lend_rack.model.domain.Author;
import com.lendrack.lend_rack.model.dto.CreateAuthorRequest;
import com.lendrack.lend_rack.model.dto.UpdateAuthorRequest;
import com.lendrack.lend_rack.persistance.entity.AuthorEntity;
import com.lendrack.lend_rack.service.AuthorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Author Resource", description = "Explore Author Crud")
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/authors")
public class AuthorResController {
    private final AuthorService authorService;

    @GetMapping
    @Operation(summary = "Get All authors")
    public List<Author> getAllAuthors(@ParameterObject Pageable pageable) {
        return authorService.findAllAuthors(pageable);
    }

    @Operation(summary = "Create a new author")
    @PostMapping
//    public void createAuthor(@ParameterObject CreateAuthorRequest request) {
    public void createAuthor(CreateAuthorRequest request) {
        authorService.create(request);
    }

    @GetMapping("{id}")
    @Operation(summary = "Get author by id")
    public AuthorEntity getAuthorById(@PathVariable Long id) throws NotFoundException {
        AuthorEntity authorEntity = authorService.findEntityById(id);
        return authorEntity;
    }

    @PutMapping("{id}")
    @Operation(summary = "Update Author id")
    public void updateAuthor(@PathVariable Long id, @RequestBody UpdateAuthorRequest request) throws NotFoundException {
        authorService.update(id, request);
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Delete author by name")
    public void deleteAuthor(@PathVariable Long id) throws NotFoundException {
        authorService.delete(id);
    }
}
