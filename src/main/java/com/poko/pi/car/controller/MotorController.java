package com.poko.pi.car.controller;

import com.poko.pi.car.service.MotorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/motorService")
public class MotorController {

    @Autowired
    private MotorService service;

    @PostMapping("/moveForward")
    public void moveForward(@RequestBody long duration) throws InterruptedException {
        service.forward(duration);
        service.stop();
    }

    @PostMapping("/moveBackward")
    public void moveBackward(@RequestBody long duration) throws InterruptedException {
        service.backward(duration);
        service.stop();
    }

    @PostMapping("/rotateLeft")
    public void rotateLeft(@RequestBody long duration) throws InterruptedException {
        service.rotateLeft(duration);
        service.stop();
    }

    @PostMapping("/rotateRight")
    public void rotateRight(@RequestBody long duration) throws InterruptedException {
        service.rotateRight(duration);
        service.stop();
    }
}
