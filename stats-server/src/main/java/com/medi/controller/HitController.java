package com.medi.controller;

import com.medi.dto.EndpointHit;
import com.medi.dto.Stats;
import com.medi.dto.ViewStats;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import com.medi.service.HitService;

import java.util.List;
import java.util.Set;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping
public class HitController {

    private final HitService hitService;

    @GetMapping("/stats")
    public Set<ViewStats> findStats(@RequestParam(required = true) List<String> uris,
                                    @RequestParam(required = true) String start,
                                    @RequestParam(required = true) String end,
                                    @RequestParam(defaultValue = "false") Boolean unique) {
        log.info("Получен запрос к эндпоинту: GET /stats, Uris: {} и unique: {}",
                uris, unique);
        return hitService.findStats(uris, start, end, unique);
    }

    @GetMapping("/views")
    public Stats findViews(@RequestParam(required = true) List<String> uris,
                           @RequestParam(required = true) String start,
                           @RequestParam(required = true) String end,
                           @RequestParam(defaultValue = "false") Boolean unique) {
        log.info("Получен запрос к эндпоинту: GET /stats, Uris: {} и unique: {}",
                uris, unique);
        return hitService.findViews(uris, start, end, unique);
    }

    @PostMapping("/hit")
    public EndpointHit create(@RequestBody EndpointHit endpointHit) {
        log.info("Получен запрос к эндпоинту: '{} {}', App: {} и Uri: {}", "POST", "/hit",
                endpointHit.getApp(), endpointHit.getUri());
        return hitService.save(endpointHit);
    }
}
