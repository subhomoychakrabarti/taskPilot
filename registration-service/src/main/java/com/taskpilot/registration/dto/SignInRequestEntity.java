package com.taskpilot.registration.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SignInRequestEntity {
    @JsonProperty("username")
    String userName;
}
