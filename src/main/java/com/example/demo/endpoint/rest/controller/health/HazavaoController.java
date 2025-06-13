package com.example.demo.endpoint.rest.controller.health;

import com.example.demo.service.HazavaoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@PojaGenerated
@RestController
@AllArgsConstructor

@RequestMapping("/hazavao")
public class HazavaoController {
    private final HazavaoService hazavaoService;

    public HazavaoController(HazavaoService hazavaoService) {
        this.hazavaoService = hazavaoService;
    }

    @GetMapping
    public ResponseEntity<String> getDefinition(@RequestParam("teny") String word) {
        String prompt = "Ahoana no famaritana ny teny \"" + word + "\" amin'ny fiteny Malagasy?";
        String definition = hazavaoService.getDefinition(prompt);
        return ResponseEntity.ok(definition);
    }
}
