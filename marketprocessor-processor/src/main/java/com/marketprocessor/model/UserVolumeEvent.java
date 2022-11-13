package com.marketprocessor.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserVolumeEvent {

    Long userId;
    Long count;

}
