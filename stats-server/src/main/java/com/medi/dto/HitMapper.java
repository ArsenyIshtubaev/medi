package com.medi.dto;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import com.medi.model.Hit;
import com.medi.utills.DateTimeMapper;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class HitMapper {

    public static EndpointHit toEndpointHi(Hit hit) {
        return new EndpointHit(hit.getId(),
                hit.getApp(),
                hit.getUri(),
                hit.getIp(),
                DateTimeMapper.toString(hit.getTimestamp()));
    }

    public static Hit toHit(EndpointHit endpointHit) {
        return new Hit(endpointHit.getId(),
                endpointHit.getApp(),
                endpointHit.getUri(),
                endpointHit.getIp(),
                DateTimeMapper.toDateTime(endpointHit.getTimestamp())
        );
    }

}
