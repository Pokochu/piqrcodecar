package com.poko.pi.car.controller;

import com.poko.pi.car.service.MotorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/motorService")
public class MotorController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MotorController.class);

    @Autowired
    private MotorService service;

    @PostMapping("/moveForward")
    public void moveForward(@RequestParam long distance) throws InterruptedException {
        service.forward(distance);
        service.stop();
    }

    @PostMapping("/moveBackward")
    public void moveBackward(@RequestParam long distance) throws InterruptedException {
        service.backward(distance);
        service.stop();
    }

    @PostMapping("/rotateLeft")
    public void rotateLeft(@RequestParam long angle) throws InterruptedException {
        service.rotateLeft(angle);
        service.stop();
    }

    @PostMapping("/rotateRight")
    public void rotateRight(@RequestParam long angle) throws InterruptedException {
        service.rotateRight(angle);
        service.stop();
    }
}
