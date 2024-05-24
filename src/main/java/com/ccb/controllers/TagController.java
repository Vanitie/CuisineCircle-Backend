package com.ccb.controllers;

import com.ccb.model.pojo.Tag;
import com.ccb.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tag")
public class TagController {
    @Autowired
    private TagService tagService;

    @GetMapping("/{id}")
    public Tag getById(@PathVariable("id") Integer id) {
        return tagService.getById(id);
    }

    @GetMapping("/list")
    public List<Tag> list() {
        return tagService.list();
    }

    @PostMapping("/saveTag")
    public boolean save(@RequestBody Tag tag) {
        return tagService.save(tag);
    }

    @PutMapping("/update")
    public boolean updateById(@RequestBody Tag tag) {
        return tagService.updateById(tag);
    }

    @DeleteMapping("/remove/{id}")
    public boolean removeById(@PathVariable("id") Integer id) {
        return tagService.removeById(id);
    }
}
