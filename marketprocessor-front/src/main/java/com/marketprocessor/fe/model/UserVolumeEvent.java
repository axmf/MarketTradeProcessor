package com.marketprocessor.fe.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.time.Instant;

@Data
@Jacksonized
@Builder
public class UserVolumeEvent {

    Instant timestamp;
    Long userId;
    Long count;

}
