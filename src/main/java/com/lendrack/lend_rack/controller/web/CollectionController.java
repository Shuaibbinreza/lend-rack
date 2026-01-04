package com.lendrack.lend_rack.controller.web;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.security.core.Authentication;

import com.lendrack.lend_rack.exception.custom.NotFoundException;
import com.lendrack.lend_rack.model.domain.Collection;
import com.lendrack.lend_rack.model.dto.CreateCollectionRequest;
import com.lendrack.lend_rack.model.dto.UpdateCollectionRequest;
import com.lendrack.lend_rack.persistance.entity.User;
import com.lendrack.lend_rack.service.CollectionService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class CollectionController {
    private final CollectionService collectionService;

    @GetMapping("collections/manage-collections/all")
    public String manageCollections(Model model) {
        Pageable pageable = PageRequest.of(0, 5, Sort.Direction.DESC, "id");
        List<Collection> collections = collectionService.getAllCollections(pageable);
        model.addAttribute("collections", collections);
        return "manage_collections";
    }

    @GetMapping("collections/manage-collections/create")
    public String createCollectionForm(Model model) {
        model.addAttribute("collection", new CreateCollectionRequest("", "", null));
        return "create_collection";
    }

    @PostMapping("collections/manage-collections/create")
    public String createCollection(@ModelAttribute("collection") CreateCollectionRequest request, Authentication authentication, RedirectAttributes redirectAttributes) {
        User currentUser = (User) authentication.getPrincipal();
        CreateCollectionRequest req = new CreateCollectionRequest(request.collection_name(), request.location(), currentUser.getId());
        collectionService.create(req);
        redirectAttributes.addFlashAttribute("message", "Collection created successfully");
        return "redirect:/collections/manage-collections/all";
    }

    @GetMapping("collections/manage-collections/edit/{id}")
    public String editCollectionForm(@PathVariable Long id, Model model) throws NotFoundException {
        Collection collection = collectionService.getById(id);
        model.addAttribute("collection", collection);
        return "edit_collection";
    }

    @PostMapping("collections/manage-collections/edit/{id}")
    public String updateCollection(@PathVariable Long id, @ModelAttribute("collection") Collection collection, RedirectAttributes redirectAttributes) throws NotFoundException {
        UpdateCollectionRequest req = new UpdateCollectionRequest(collection.getCollection_name(), collection.getLocation());
        collectionService.update(id, req);
        redirectAttributes.addFlashAttribute("message", "Collection updated successfully");
        return "redirect:/collections/manage-collections/all";
    }

    @PostMapping("collections/manage-collections/delete/{id}")
    public String deleteCollection(@PathVariable Long id, RedirectAttributes redirectAttributes) throws NotFoundException {
        collectionService.delete(id);
        redirectAttributes.addFlashAttribute("message", "Collection deleted successfully");
        return "redirect:/collections/manage-collections/all";
    }
}