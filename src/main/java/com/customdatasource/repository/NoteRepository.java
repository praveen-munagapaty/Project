package com.customdatasource.repository;

import java.util.List;

import com.customdatasource.domain.Note;


public interface NoteRepository extends Repository<Note, Long> {

    List<Note> findAll();

}
