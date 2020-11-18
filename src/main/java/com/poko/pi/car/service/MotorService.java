package com.poko.pi.car.service;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import static com.pi4j.io.gpio.PinState.HIGH;
import static com.pi4j.io.gpio.PinState.LOW;
import static com.pi4j.io.gpio.RaspiPin.*;

@Service
public class MotorService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MotorService.class);

    private final GpioController gpioController = GpioFactory.getInstance();
    private final GpioPinDigitalOutput PIN17 = gpioController.provisionDigitalOutputPin(GPIO_00);
    private final GpioPinDigitalOutput PIN22 = gpioController.provisionDigitalOutputPin(GPIO_03);
    private final GpioPinDigitalOutput PIN23 = gpioController.provisionDigitalOutputPin(GPIO_04);
    private final GpioPinDigitalOutput PIN24 = gpioController.provisionDigitalOutputPin(GPIO_05);

    public void forward(long duration) throws InterruptedException {
        gpioController.setState(LOW, PIN17, PIN24);
        gpioController.setState(HIGH, PIN22, PIN23);
        Thread.sleep(duration);
    }

    public void backward(long duration) throws InterruptedException {
        gpioController.setState(LOW, PIN22, PIN23);
        gpioController.setState(HIGH, PIN17, PIN24);
        Thread.sleep(duration);
    }

    public void rotateLeft(long duration) throws InterruptedException {
        gpioController.setState(LOW, PIN17, PIN23);
        gpioController.setState(HIGH, PIN22, PIN24);
        Thread.sleep(duration);
    }

    public void rotateRight(long duration) throws InterruptedException {
        gpioController.setState(LOW, PIN22, PIN24);
        gpioController.setState(HIGH, PIN17, PIN23);
        Thread.sleep(duration);
    }

    public void stop() {
        gpioController.setState(LOW, PIN17, PIN22, PIN23, PIN24);
    }
}
