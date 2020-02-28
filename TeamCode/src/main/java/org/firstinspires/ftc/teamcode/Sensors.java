package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.opencv.core.Scalar;


public class Sensors {
/*
    private final DistanceSensor rd;
    private final DistanceSensor ld;
    private final DistanceSensor fd;
    private final DistanceSensor bd;*/
    private final DistanceSensor stone;
    private final RevBlinkinLedDriver lights;
    private final ColorSensor rc;
    private final ColorSensor lc;
    private final DigitalChannel block;
    private final LinearOpMode sensor;


    public Sensors (LinearOpMode sensor){
        /*bd = sensor.hardwareMap.get(DistanceSensor.class, "bd");
        fd = sensor.hardwareMap.get(DistanceSensor.class, "bd");
        rd = sensor.hardwareMap.get(DistanceSensor.class, "bd");
        ld = sensor.hardwareMap.get(DistanceSensor.class, "bd");*/
        lights = sensor.hardwareMap.get(RevBlinkinLedDriver.class,"lights");
        stone = sensor.hardwareMap.get(DistanceSensor.class, "stone");
        rc= sensor.hardwareMap.get(ColorSensor.class, "rc");
        lc= sensor.hardwareMap.get(ColorSensor.class, "lc");
        block= sensor.hardwareMap.get(DigitalChannel.class, "block");
        this.sensor=sensor;
    }
    public int rc(){
        return (rc.alpha());
    }
    public int lc(){
        return (lc.alpha());
    }
    public int blockposition(){

        if(lc.red()>0){
            if(rc.red()/lc.red()<.66){
                return 3;
            }
            else if(rc.red()/lc.red()>1.33){
                return 1;
            }
            else{
                return 2;
            }
        }
        else{
            return 2;
        }
    }
    public void touchInit(){
        block.setMode(DigitalChannel.Mode.INPUT);
    }
    public boolean block(){
        return block.getState();
    }
    public void lightColor(boolean range){
        if(range){
            if(stone.getDistance(DistanceUnit.INCH)<7.5 && stone.getDistance(DistanceUnit.INCH)>6){
                lights.setPattern(RevBlinkinLedDriver.BlinkinPattern.GOLD);

            }
            else{
                lights.setPattern(RevBlinkinLedDriver.BlinkinPattern.RED);

            }
        }
        else{
            lights.setPattern(RevBlinkinLedDriver.BlinkinPattern.RED_ORANGE);
        }

    }
    /*
    public double rd(){
        return (rd.getDistance(DistanceUnit.INCH));
    }
    public double ld(){
        return (ld.getDistance(DistanceUnit.INCH));
    }
    public double fd(){
        return (fd.getDistance(DistanceUnit.INCH));
    }
    public double bd(){
        return (bd.getDistance(DistanceUnit.INCH));
    }*/
    public void telem(){
        /*
        sensor.telemetry.addData("front distance", fd());
        sensor.telemetry.addData("back distance", bd());
        sensor.telemetry.addData("right distance", rd());
        sensor.telemetry.addData("left distance", ld());*/
        sensor.telemetry.addData("black block place",blockposition());
        sensor.telemetry.addData("right red color", rc.red());
        sensor.telemetry.addData("left red color", lc.red());
        sensor.telemetry.addData("right color alpha", rc());
        sensor.telemetry.addData("left color alpha", lc());
        sensor.telemetry.addData("block in intake",block());
    }


}
