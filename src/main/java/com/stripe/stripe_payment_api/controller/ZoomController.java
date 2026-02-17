package com.stripe.stripe_payment_api.controller;


import com.stripe.stripe_payment_api.service.ZoomMeetingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/zoom")
@RequiredArgsConstructor
public class ZoomController {

    private final ZoomMeetingService service;

    @PostMapping("/create-meeting")
    public String createMeeting() {
        return service.createMeeting();
    }
}
