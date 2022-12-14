package com.marketprocessor.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.time.Instant;

@Data
@Builder
@Jacksonized
@AllArgsConstructor
public class UserVolumeEvent {

    Instant timestamp;
    Long userId;
    Long count;

}
