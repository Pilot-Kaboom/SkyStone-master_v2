package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

public class Intake {
    private final LinearOpMode intake;
    private final DcMotor right;
    private final DcMotor left;
    private final Servo rightup;
    private final Servo leftup;
    private final Servo cap;

    public Intake (LinearOpMode intake){
        right = intake.hardwareMap.dcMotor.get("rightintake");
        left = intake.hardwareMap.dcMotor.get("leftintake");
        leftup = intake.hardwareMap.servo.get("leftservo");
        rightup = intake.hardwareMap.servo.get("rightservo");
        cap = intake.hardwareMap.servo.get("cap");

        this.intake=intake;
    }
    public void intake(double in){
        right.setPower(-in);
        left.setPower(in);
    }
    public void lift(boolean up,boolean left, boolean right){
        if(up){
            rightup.setPosition(.253);
            leftup.setPosition(.255);

        }
        else if(left){
            leftup.setPosition(0);
            rightup.setPosition(0);
        }
        else if(right){
            rightup.setPosition(.225);
            leftup.setPosition(.5);
        }
        else{
            rightup.setPosition(.05);
            leftup.setPosition(.52);
        }
    }
    public void capper(boolean place){
        if(place){
            cap.setPosition(.97);
        }
        else{
            cap.setPosition(.5);
        }
    }
    /*
    public double odoHeadding(){
        return((right.getCurrentPosition()-left.getCurrentPosition())*0.0012696639344);
    }
    public double odoForward(){
        return(((right.getCurrentPosition()+left.getCurrentPosition())*.5)*.0005737);
    }
    public void resetEC(){
        right.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        left.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        intake.idle();
        right.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        left.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        intake.idle();
    }*/

    public void telemetry(){
        /*intake.telemetry.addData("odoForward",odoForward());
        intake.telemetry.addData("odoHeading",odoHeadding());
*/
        intake.telemetry.addData("right up position",rightup.getPosition());
        intake.telemetry.addData("left up position",leftup.getPosition());
    }
}
