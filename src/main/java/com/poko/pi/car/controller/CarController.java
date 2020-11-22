package com.poko.pi.car.controller;

import com.poko.pi.car.service.PiCarService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/carControl")
public class CarController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CarController.class);

    @Autowired
    private PiCarService service;

    @PostMapping("/driveFromTo")
    public void driveBetweenNodes(@RequestParam String from, @RequestParam String to) {
        service.driveBetweenNodes(from, to);
    }

    @PostMapping("/driveToNode")
    public void driveToNode(@RequestParam String destinationNode) {
        service.driveToNode(destinationNode);
    }
}
