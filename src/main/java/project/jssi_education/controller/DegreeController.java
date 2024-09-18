package project.jssi_education.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import project.jssi_education.entity.Degree;
import project.jssi_education.entity.User;
import project.jssi_education.service.IDegreeService;
import project.jssi_education.service.impl.DegreeService;

import java.util.List;

@RestController
@RequestMapping("/degrees")
public class DegreeController {
    @Autowired
    private DegreeService degreeService;

    @GetMapping("/")
    public List<Degree> findAll(){
        return degreeService.FindAll();
    }


    @GetMapping("/{id}")
    public Degree findOne(@PathVariable Long id){
        return degreeService.FindbyId(id);
    }

    @PostMapping("/")
    public void insert(@RequestBody Degree degree){
        degreeService.Insert(degree);
    }
}
