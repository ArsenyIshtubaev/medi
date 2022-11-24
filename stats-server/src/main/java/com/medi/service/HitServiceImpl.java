package com.medi.service;

import com.medi.dto.EndpointHit;
import com.medi.dto.HitMapper;
import com.medi.dto.Stats;
import com.medi.dto.ViewStats;
import com.medi.model.QHit;
import com.querydsl.core.types.dsl.BooleanExpression;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.medi.model.Hit;
import com.medi.repository.HitRepository;
import com.medi.utills.DateTimeMapper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class HitServiceImpl implements HitService {

    private final HitRepository hitRepository;

    @Override
    public EndpointHit save(EndpointHit endpointHit) {
        Hit hit = HitMapper.toHit(endpointHit);
        return HitMapper.toEndpointHi(hitRepository.save(hit));
    }

    @Override
    public Set<ViewStats> findStats(List<String> uris, String start, String end, Boolean unique) {

        Set<ViewStats> stats = new HashSet<>();

        QHit hit = QHit.hit;
        List<BooleanExpression> conditions = new ArrayList<>();

        if (uris != null && !uris.isEmpty()) {
            conditions.add(hit.uri.in(uris));
        }
        if (start != null && end != null) {
            conditions.add(hit.timestamp.between(DateTimeMapper.toDateTime(start), DateTimeMapper.toDateTime(end)));
        }

        BooleanExpression expression = conditions.stream()
                .reduce(BooleanExpression::and)
                .get();

        List<Hit> hitList = hitRepository.findAll(expression, Pageable.unpaged()).toList();

        for (Hit h : hitList) {
            Integer hits;
            if (unique) {
                hits = hitRepository.findHitsWithUniqueIp(h.getUri(), h.getApp());
            } else {
                hits = hitRepository.findHits(h.getUri(), h.getApp());
            }
            stats.add(new ViewStats(h.getApp(), h.getUri(), hits));
        }
        return stats;
    }

    @Override
    public Stats findViews(List<String> uris, String start, String end, Boolean unique) {
        Stats viewStats = new Stats();
        viewStats.setStats(findStats(uris, start, end, unique));
        return viewStats;
    }

}
