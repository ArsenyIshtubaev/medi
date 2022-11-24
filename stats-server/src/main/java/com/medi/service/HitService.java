package com.medi.service;

import com.medi.dto.EndpointHit;
import com.medi.dto.Stats;
import com.medi.dto.ViewStats;

import java.util.List;
import java.util.Set;

public interface HitService {

    EndpointHit save(EndpointHit endpointHit);

    Set<ViewStats> findStats(List<String> uris, String start, String end, Boolean unique);

    Stats findViews(List<String> uris, String start, String end, Boolean unique);
}
