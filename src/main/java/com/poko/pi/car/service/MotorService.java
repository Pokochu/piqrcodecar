package com.poko.pi.car.service;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.GpioPinPwmOutput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import static com.pi4j.io.gpio.PinState.HIGH;
import static com.pi4j.io.gpio.PinState.LOW;
import static com.pi4j.io.gpio.RaspiPin.*;

@Service
public class MotorService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MotorService.class);

    private final GpioController gpioController;
    private final GpioPinPwmOutput pin25;
    private final GpioPinPwmOutput pin26;
    private final GpioPinDigitalOutput pin17;
    private final GpioPinDigitalOutput pin22;
    private final GpioPinDigitalOutput pin23;
    private final GpioPinDigitalOutput pin24;

    public MotorService() {
        gpioController = GpioFactory.getInstance();
        pin25 = gpioController.provisionPwmOutputPin(GPIO_01, "enA", 0);
        pin26 = gpioController.provisionPwmOutputPin(GPIO_24, "enB", 0);
        pin17 = gpioController.provisionDigitalOutputPin(GPIO_00, "in1"); //PIN17
        pin22 = gpioController.provisionDigitalOutputPin(GPIO_03, "in2"); //PIN22
        pin23 = gpioController.provisionDigitalOutputPin(GPIO_04, "in3"); //PIN23
        pin24 = gpioController.provisionDigitalOutputPin(GPIO_05, "in4"); //PIN24
        pin25.setPwmRange(1000);
        pin26.setPwmRange(1000);
    }

    public void forward(long distance) throws InterruptedException {
        gpioController.setState(LOW, pin17, pin24);
        gpioController.setState(HIGH, pin22, pin23);
        setPwm();
        Thread.sleep(distance);
    }

    public void backward(long distance) throws InterruptedException {
        gpioController.setState(LOW, pin22, pin23);
        gpioController.setState(HIGH, pin17, pin24);
        setPwm();
        Thread.sleep(distance);
    }

    public void rotateRight(long angle) throws InterruptedException {
        gpioController.setState(LOW, pin17, pin23);
        gpioController.setState(HIGH, pin22, pin24);
        setPwm();
        Thread.sleep(angle);
    }

    public void rotateLeft(long angle) throws InterruptedException {
        gpioController.setState(LOW, pin22, pin24);
        gpioController.setState(HIGH, pin17, pin23);
        setPwm();
        Thread.sleep(angle);
    }

    public void stop() {
        gpioController.setState(LOW, pin17, pin22, pin23, pin24);
    }

    private void setPwm() {
        pin25.setPwm(Math.toIntExact(500));
        pin26.setPwm(Math.toIntExact(500));
    }
}
