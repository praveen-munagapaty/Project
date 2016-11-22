package com.customdatasource.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.customdatasource.domain.Note;
import com.customdatasource.repository.NoteRepository;

@Controller
public class IndexController {

    @Autowired
    private NoteRepository noteRepository;

    @RequestMapping("/")
    @Transactional(readOnly = true)
    public ModelAndView index() {
        List<Note> notes = this.noteRepository.findAll();
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("notes", notes);
        return modelAndView;
    }

}
