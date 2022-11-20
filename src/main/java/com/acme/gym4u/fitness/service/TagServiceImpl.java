package com.acme.gym4u.fitness.service;

import com.acme.gym4u.fitness.domain.model.entity.Tag;
import com.acme.gym4u.fitness.domain.persistence.TagRepository;
import com.acme.gym4u.fitness.domain.service.TagService;
import com.acme.gym4u.shared.exception.ResourceNotFoundException;
import com.acme.gym4u.shared.exception.ResourceValidationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

@Service
public class TagServiceImpl implements TagService {

    private static final String ENTITY = "Tag";
    private final TagRepository tagRepository;
    private final Validator validator;

    public TagServiceImpl(TagRepository tagRepository, Validator validator) {
        this.tagRepository = tagRepository;
        this.validator = validator;
    }


    @Override
    public List<Tag> getAll() {
        return tagRepository.findAll();
    }

    @Override
    public Page<Tag> getAll(Pageable pageable) {
        return tagRepository.findAll(pageable);
    }

    @Override
    public Tag getById(Long tagId) {
        return tagRepository.findById(tagId)
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, tagId));
    }

    @Override
    public Tag create(Tag tag) {

        Set<ConstraintViolation<Tag>> violations = validator.validate(tag);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        Tag tagWithName = tagRepository.findByName(tag.getName());

        if (tagWithName != null)
            throw new ResourceValidationException(ENTITY,
                    "An tag with the same name already exists.");

        return tagRepository.save(tag);
    }

    @Override
    public Tag update(Long tagId, Tag tag) {

        Set<ConstraintViolation<Tag>> violations = validator.validate(tag);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        Tag tagWithName = tagRepository.findByName(tag.getName());

        if (tagWithName != null)
            throw new ResourceValidationException(ENTITY,
                    "An tag with the same name already exists.");

        return tagRepository.findById(tagId).map(element ->
                        tagRepository.save(element
                                .withName(tag.getName())))
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, tagId));

    }

    @Override
    public ResponseEntity<?> delete(Long tagId) {
        return tagRepository.findById(tagId).map(tag -> {
            tagRepository.delete(tag);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, tagId));
    }
}
