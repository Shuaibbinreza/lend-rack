package com.lendrack.lend_rack.mapper;

import com.lendrack.lend_rack.model.domain.Collection;
import com.lendrack.lend_rack.model.dto.CreateCollectionRequest;
import com.lendrack.lend_rack.model.dto.UpdateCollectionRequest;
import com.lendrack.lend_rack.persistance.entity.CollectionEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class CollectionMapper {
    public Collection EntityToDomain(CollectionEntity collectionEntity) {
        Collection collection = new Collection();
        BeanUtils.copyProperties(collectionEntity, collection);
        if (collectionEntity.getCreated_by() != null) {
            collection.setCreatedByName(collectionEntity.getCreated_by().getName());
        }
        return collection;
    }

    public CollectionEntity createRequestToEntity(CreateCollectionRequest request) {
        CollectionEntity collectionEntity = new CollectionEntity();
        collectionEntity.setCollection_name(request.collection_name());
        collectionEntity.setLocation(request.location());
        collectionEntity.setCreated_at(LocalDateTime.now());
        return collectionEntity;
    }

    public CollectionEntity updateRequestToEntity(UpdateCollectionRequest request, CollectionEntity collectionEntity) {
        collectionEntity.setCollection_name(request.collection_name());
        collectionEntity.setLocation(request.location());
        return collectionEntity;
    }
}