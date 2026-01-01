package com.lendrack.lend_rack.controller.rest;

import com.lendrack.lend_rack.exception.custom.NotFoundException;
import com.lendrack.lend_rack.model.domain.Collection;
import com.lendrack.lend_rack.model.dto.CreateCollectionRequest;
import com.lendrack.lend_rack.model.dto.UpdateCollectionRequest;
import com.lendrack.lend_rack.persistance.entity.CollectionEntity;
import com.lendrack.lend_rack.service.CollectionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Collection Resource", description = "API for managing collections")
@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/collections")
public class CollectionRestController {
    private final CollectionService collectionService;

    @Operation(summary = "Get all collections")
    @GetMapping
    public List<Collection> getAllCollections(@ParameterObject Pageable pageable) {
        return collectionService.getAllCollections(pageable);
    }

    @Operation(summary = "Create a new collection")
    @PostMapping
    public void createCollection(CreateCollectionRequest createCollectionRequest) {
        collectionService.create(createCollectionRequest);
    }

    @Operation(summary = "Get Collection by ID")
    @GetMapping("{id}")
    public CollectionEntity getCollectionById(@PathVariable Long id) throws NotFoundException {
        CollectionEntity collectionEntity = collectionService.findEntityById(id);
        return collectionEntity;
    }

    @Operation(summary = "Update a collection by id")
    @PutMapping("{id}")
    public void updateCollection(@PathVariable Long id, @RequestBody UpdateCollectionRequest request) throws NotFoundException {
        collectionService.update(id, request);
    }

    @Operation(summary = "Delete Collection by id")
    @DeleteMapping("{id}")
    public void deleteCollection(@PathVariable Long id) throws NotFoundException {
        collectionService.delete(id);
    }
}