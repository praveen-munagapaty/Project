package com.customdatasource.repository;

import java.util.List;

import com.customdatasource.domain.Tag;


public interface TagRepository extends Repository<Tag, Long> {

    List<Tag> findAll();

}
