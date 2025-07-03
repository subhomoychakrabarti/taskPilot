package com.taskpilot.registration.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SignUpRequestEntity {
    @JsonProperty("username")
    String userName;
    @JsonProperty("email")
    String email;
}
