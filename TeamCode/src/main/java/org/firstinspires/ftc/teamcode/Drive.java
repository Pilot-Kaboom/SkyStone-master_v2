package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.AnalogSensor;
import com.qualcomm.robotcore.hardware.DcMotor;

import java.util.Base64;

public class Drive {

    private final DcMotor BRM;
    private final DcMotor FLM;
    private final DcMotor BLM;
    private final DcMotor FRM;
    private final DcMotor re;
    private final DcMotor le;
    private final DcMotor be;

    private double odoR;
    private double odoF;
    private double odoR2;
    private double odoF2;
    private double startx;
    private double distance;
    private final LinearOpMode adrive;

    public Drive(LinearOpMode adrive){
        BRM = adrive.hardwareMap.dcMotor.get("brm");
        BLM = adrive.hardwareMap.dcMotor.get("blm");
        FRM = adrive.hardwareMap.dcMotor.get("frm");
        FLM = adrive.hardwareMap.dcMotor.get("flm");
        re = adrive.hardwareMap.dcMotor.get("rightintake");
        le = adrive.hardwareMap.dcMotor.get("leftintake");
        be = adrive.hardwareMap.dcMotor.get("leftlift");

        this.adrive=adrive;

    }

    public void resetEC(){
        FLM.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        BLM.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        BRM.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        FRM.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        re.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        le.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        be.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        adrive.idle();
        FLM.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        BLM.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        BRM.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        FRM.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        re.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        le.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        be.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        adrive.idle();
    }
    public void RunInPower(){
        FLM.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        BLM.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        BRM.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        FRM.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        adrive.idle();
    }
    public void StopMotors(){
        FLM.setPower(0);
        FRM.setPower(0);
        BLM.setPower(0);
        BRM.setPower(0);
    }

    public double odoHeadding(){
        return((re.getCurrentPosition()-le.getCurrentPosition())*0.0012696639344);
    }
    public double odoForward(){
        return(((re.getCurrentPosition()+le.getCurrentPosition())*.5)*.0005737);
    }
    public double odoRight(){
        return(((be.getCurrentPosition())*.0005737)-(odoHeadding()*.5));
    }
    public double odoX(){
        odoR2 = odoRight()-odoR;
        odoF2=odoForward()-odoF;
        odoR = odoRight();
        odoF=odoForward();
        return ((odoR2*Math.cos(odoHeadding()))+(odoR2*Math.sin(odoHeadding())))+startx;
    }
    public double odoY(){
        return((odoF2*Math.cos(odoHeadding()))+(odoR2*Math.sin(odoHeadding())));
    }
    public void odoDrive(double x, double y,double headding){
        distance=((Math.sqrt((x*x)+(y*y)))-(Math.sqrt((odoX()*odoX())+(odoY()*odoY()))));
        if(distance<10){

        }
        else{

        }
    }
    public void teledrive(double forward, double right, double turnC){
        FLM.setPower(forward + right + turnC);
        FRM.setPower(-forward + right + turnC);
        BLM.setPower(forward - right  +turnC);
        BRM.setPower(-forward - right + turnC);
    }
    public void ECtelem() {

        adrive.telemetry.addData("odoForward",odoForward());
        adrive.telemetry.addData("odoRight",odoRight());
        adrive.telemetry.addData("odoHeading",odoHeadding());
        adrive.telemetry.addData("odoX",odoX());
        adrive.telemetry.addData("odoY",odoY());
        adrive.telemetry.addData("odoDifference",distance);
        adrive.telemetry.addData("odoF",odoF);
        adrive.telemetry.addData("odoR",odoR);
        adrive.telemetry.addData("odoF2",odoF2);
        adrive.telemetry.addData("odoF2",odoR2);

        adrive.telemetry.addData("right module", FRM.getCurrentPosition());
        adrive.telemetry.addData("left module", FLM.getCurrentPosition());
        adrive.telemetry.addData("back module", BRM.getCurrentPosition());
        adrive.telemetry.addData("N/A", BLM.getCurrentPosition());
        /*adrive.telemetry.addData("FEC", fect());
        adrive.telemetry.addData("BEC", bect());
        adrive.telemetry.addData("REC", rect());
        adrive.telemetry.addData("LEC", lect());
        adrive.telemetry.addData("TEC",tect());*/
        //telemetry.addData("ods", ODS.getLightDetected());
    }
    /*
     FLM.setPower(gamepad1.left_stick_y + -gamepad1.left_stick_x +(gamepad1.right_stick_y*.35 + -gamepad1.right_stick_x*.35)+ (gamepad1.left_trigger + -gamepad1.right_trigger* .5));
     FRM.setPower(-gamepad1.left_stick_y + -gamepad1.left_stick_x +(-gamepad1.right_stick_y*.35 + -gamepad1.right_stick_x*.35)+ (gamepad1.left_trigger + -gamepad1.right_trigger* .5));
     BRM.setPower(-gamepad1.left_stick_y + gamepad1.left_stick_x +(-gamepad1.right_stick_y*.35 + gamepad1.right_stick_x*.35)+ (gamepad1.left_trigger + -gamepad1.right_trigger* .5));
     BLM.setPower(gamepad1.left_stick_y + gamepad1.left_stick_x +(gamepad1.right_stick_y*.35 + gamepad1.right_stick_x*.35)+ (gamepad1.left_trigger + -gamepad1.right_trigger* .5));

    */
/*
    public int bect(){
        return( FRM.getCurrentPosition()/4 + BRM.getCurrentPosition()/4 + -FLM.getCurrentPosition()/4 +  -BLM.getCurrentPosition() / 4);
    }
    public int fect(){
        return( FLM.getCurrentPosition()/4 +  BLM.getCurrentPosition()/4 + -FRM.getCurrentPosition()/4 + -BRM.getCurrentPosition() / 4);
    }
    public int lect(){
        return( -FLM.getCurrentPosition()/4 +  BLM.getCurrentPosition()/4 + -FRM.getCurrentPosition()/4 + BRM.getCurrentPosition() / 4);
    }
    public int rect(){
        return( FLM.getCurrentPosition()/4 +  -BLM.getCurrentPosition()/4 + FRM.getCurrentPosition()/4 + -BRM.getCurrentPosition() / 4);
    }
    public int tect(){
        return( (FLM.getCurrentPosition()/4 +  BLM.getCurrentPosition()/4) + (FRM.getCurrentPosition()/4 +BRM.getCurrentPosition() / 4));

    }
    public double fcontrolp(double goal, double tune){
        if(goal-(rect())*tune>1){
            return 1;
        }
        else if(goal-(rect())*tune<-1){
            return -1;
        }
        else{
            return ((goal-rect())*tune);
        }
    }
    public double rcontrolp(double goal, double tune){
        if((goal-fect())*tune>1){
            return 1;
        }
        else if((goal-fect())*tune<-1){
            return -1;
        }
        else{
            return ((goal-fect())*tune);
        }
    }*/
    /*
    public void goForward(double power){
        FLM.setPower(-power);
        FRM.setPower(power);
        BLM.setPower(-power);
        BRM.setPower(power);
    }
    public void goRight(double power){
        FLM.setPower(-power);
        FRM.setPower(-power);
        BLM.setPower(power);
        BRM.setPower(power);
    }
    public void turnClockwise(double power){
        FLM.setPower(-power);
        FRM.setPower(-power);
        BLM.setPower(-power);
        BRM.setPower(-power);
    }
    public void diaginalFRtoBL(double power){
        FRM.setPower(power);
        BLM.setPower(power);
        FLM.setPower(0);
        BRM.setPower(0);
    }
    public void diaginalFLtoBR(double power){
        FLM.setPower(power);
        BRM.setPower(power);
        FRM.setPower(0);
        BLM.setPower(0);
    }
    public void ECforward(double distance, double power){
        while(fect() < distance){
            goForward(power);
        }
    }
    public void ECbackward(double distance, double power){
        while(bect() < distance){
            goForward(-power);
        }
    }
    public void ECright(double distance, double power){
        while(rect() < distance){
            goRight(power);
        }
    }
    public void ECleft(double distance, double power){
        while(lect() < distance){
            goRight(-power);
        }
    }*/

}
