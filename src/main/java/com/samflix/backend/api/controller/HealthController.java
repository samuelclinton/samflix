package com.samflix.backend.api.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Status")
@RestController
@RequestMapping("/status")
public class HealthController {



}
