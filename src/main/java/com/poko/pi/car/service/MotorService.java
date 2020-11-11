package com.poko.pi.car.service;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import org.springframework.stereotype.Service;

import static com.pi4j.io.gpio.PinState.HIGH;
import static com.pi4j.io.gpio.PinState.LOW;
import static com.pi4j.io.gpio.RaspiPin.*;

@Service
public class MotorService {

    private GpioController gpioController; // = GpioFactory.getInstance();
    private GpioPinDigitalOutput PIN17; // = gpioController.provisionDigitalOutputPin(GPIO_00);
    private GpioPinDigitalOutput PIN22; // = gpioController.provisionDigitalOutputPin(GPIO_03);
    private GpioPinDigitalOutput PIN23; // = gpioController.provisionDigitalOutputPin(GPIO_04);
    private GpioPinDigitalOutput PIN24; // = gpioController.provisionDigitalOutputPin(GPIO_05);

    public MotorService() {
        gpioController = GpioFactory.getInstance();
        PIN17 = gpioController.provisionDigitalOutputPin(GPIO_00);
        PIN22 = gpioController.provisionDigitalOutputPin(GPIO_03);
        PIN23 = gpioController.provisionDigitalOutputPin(GPIO_04);
        PIN24 = gpioController.provisionDigitalOutputPin(GPIO_05);
    }

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
