package com.lendrack.lend_rack.service;

import com.lendrack.lend_rack.exception.custom.NotFoundException;
import com.lendrack.lend_rack.mapper.CollectionMapper;
import com.lendrack.lend_rack.model.domain.Collection;
import com.lendrack.lend_rack.model.dto.CreateCollectionRequest;
import com.lendrack.lend_rack.model.dto.UpdateCollectionRequest;
import com.lendrack.lend_rack.persistance.entity.CollectionEntity;
import com.lendrack.lend_rack.persistance.entity.User;
import com.lendrack.lend_rack.persistance.repository.CollectionRepository;
import com.lendrack.lend_rack.persistance.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CollectionService {
    private final CollectionRepository collectionRepository;
    private final CollectionMapper collectionMapper;
    private final UserRepository userRepository;

    public List<Collection> getAllCollections(Pageable pageable) {
        List<CollectionEntity> collectionEntities = collectionRepository.findAll(pageable).getContent();
        return collectionEntities.stream().map(collectionMapper::EntityToDomain).toList();
    }

    public List<Collection> getAllCollections() {
        List<CollectionEntity> collectionEntities = collectionRepository.findAll();
        return collectionEntities.stream().map(collectionMapper::EntityToDomain).toList();
    }

    public Long create(CreateCollectionRequest createCollectionRequest) {
        if (createCollectionRequest.created_by() == null) {
            throw new IllegalArgumentException("created_by must not be null");
        }

        // Fetch UserEntity from DB
        User user = userRepository.findById(createCollectionRequest.created_by())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Map request to CollectionEntity (without user)
        CollectionEntity collectionToSave = collectionMapper.createRequestToEntity(createCollectionRequest);

        // Manually set the user entity
        collectionToSave.setCreated_by(user);

        // Save collection with user
        CollectionEntity savedCollection = collectionRepository.save(collectionToSave);
        return savedCollection.getId();
    }

    public Collection getById(Long id) throws NotFoundException {
        var entity = collectionRepository.findById(id).orElse(null);
        return collectionMapper.EntityToDomain(entity);
    }

    public void update(Long id, UpdateCollectionRequest updateCollectionRequest) throws NotFoundException {
        CollectionEntity entity = this.findEntityById(id);
        CollectionEntity updateCollectionEntity = collectionMapper.updateRequestToEntity(updateCollectionRequest, entity);
        collectionRepository.save(updateCollectionEntity);
    }

    public CollectionEntity findEntityById(Long id) throws NotFoundException {
        var entity = collectionRepository.findById(id).orElseThrow(() -> new NotFoundException("Collection not found"));
        return entity;
    }

    public void delete(Long id) throws NotFoundException {
        this.findEntityById(id);
        collectionRepository.deleteById(id);
    }
}