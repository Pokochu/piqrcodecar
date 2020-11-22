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
    public void moveForward(@RequestParam long duration) throws InterruptedException {
        service.forward(duration);
        service.stop();
    }

    @PostMapping("/moveBackward")
    public void moveBackward(@RequestParam long duration) throws InterruptedException {
        service.backward(duration);
        service.stop();
    }

    @PostMapping("/rotateLeft")
    public void rotateLeft(@RequestParam long duration) throws InterruptedException {
        service.rotateLeft(duration);
        service.stop();
    }

    @PostMapping("/rotateRight")
    public void rotateRight(@RequestParam long duration) throws InterruptedException {
        service.rotateRight(duration);
        service.stop();
    }
}
