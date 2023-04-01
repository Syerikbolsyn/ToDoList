package org.example.controllers;

import jakarta.validation.Valid;
import org.example.dao.ToDoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.example.models.*;

@Controller
@RequestMapping("/toDoList")
public class ToDoListController {
    private final ToDoDao toDoDao;

    @Autowired
    public ToDoListController(ToDoDao toDoDao) {
        this.toDoDao = toDoDao;
    }
    @GetMapping()
    public String index(Model model) {
        model.addAttribute("toDoList", toDoDao.index());
        return "toDoList/index";
    }
    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("toDo", toDoDao.show(id));
        return "toDoList/show";
    }
    @GetMapping("/new")
    public String newTask(@ModelAttribute("toDo") @Valid ToDo toDo, BindingResult bindingResult) {
        return "toDoList/new";
    }
    @PostMapping()
    public String create(@ModelAttribute("toDo") @Valid ToDo toDo, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "toDoList/new";
        toDoDao.save(toDo);
        return "redirect: /toDoList";
    }
    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id){
        model.addAttribute("toDo", toDoDao.show(id));
        return "toDoList/edit";
    }
    @PatchMapping("/{id}")
    public String update(@ModelAttribute("toDo") @Valid ToDo toDo, BindingResult bindingResult, @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "toDoList/edit";
        toDoDao.update(id, toDo);
        return "redirect:/toDoList";
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        toDoDao.delete(id);
        return "redirect: /toDoList";
    }






}
