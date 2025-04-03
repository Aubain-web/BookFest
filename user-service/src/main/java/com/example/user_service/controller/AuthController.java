package com.example.user_service.controller;

import com.example.user_service.dto.AuthenticationRequest;
import com.example.user_service.dto.AuthenticationResponse;
import com.example.user_service.dto.TicketDTO;
import com.example.user_service.service.AuthService;
import com.example.user_service.service.TicketPublisher;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest request) {
        AuthenticationResponse response = authService.authenticate(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{userId}/buy-ticket")
    public ResponseEntity<TicketDTO> buyTicket(
            @PathVariable Long userId,
            @RequestBody TicketDTO request,
            @RequestHeader("Authorization") String token // 🔥 Récupérer le JWT
    ) {
        request.setId(userId);

        String eventServiceUrl = "http://event-service/api/events/buy-ticket";
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token); // 🔥 Passer le JWT
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<TicketDTO> entity = new HttpEntity<>(request, headers);

        ResponseEntity<TicketDTO> response = restTemplate.exchange(
                eventServiceUrl, HttpMethod.POST, entity, TicketDTO.class
        );

        return ResponseEntity.ok(response.getBody());
    }

}