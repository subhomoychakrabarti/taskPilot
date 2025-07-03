package com.taskpilot.feign;


@FeignClient(name = "registrationClient", url = "${taskpilot.registration.url}")
public interface RegistrationClient {
    @PatchMapping("/registrationService/handler/updateTask")
    ResponseEntity<void> updateTask(@ UpdateTaskRequest request);
}
