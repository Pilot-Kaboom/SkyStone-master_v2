package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.AnalogSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

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
    private double prev_dt;
    private double prev_df;
    private double dControlT;
    private double dControlF;
    private double deltaTime;
    private double lastTime;

    private double turnPow;
    private double disY;
    private double disX;
    private double disH;
    private double distance2;
    private double idealHeading;
    private double rightPow;
    private double forePow;

    private double distance;
    private double turnDifference;
    private double forwardreset;
    private ElapsedTime time = new ElapsedTime();
    private ElapsedTime timeDF = new ElapsedTime();
    private ElapsedTime timeDT = new ElapsedTime();

    private final LinearOpMode adrive;


    public double x = 0;    // The approximated x position of the robot relative to where it started
    public double y = 0;    // The approximated y position of the robot relative to where it started
    public double a = 0;    // The approximated heading of the robot relative to its initial heading in radians
    public double h = 0; //The approximated heading of the robot relative to its initial heading in degrees

    public double prev_le;
    public double prev_re;
    public double prev_ce;

    private double WHEEL_DIAMETER       = 1.49420962888;    //1.48982939421;    // Diameter of the omniwheels
    private double ENCODER_CPR          = 8192;             // Counts per full rotation of an encoder
    private double ROBOT_DIAMETER       = 12.901019222778256571485812396865;    //12.523979096482822332390518662171; //15.7075609922;    //15.74735 //15.53           // Distance between the left and right encoder (diameter) in inches
    private double CENTER_WHEEL_OFFSET  = 6.283185307179586476925286766559;      //7.725136416;      //7.719 //7.375 Distance of the center encoder to the line made between the left and right encoders (radius) in inches

    private double WHEEL_CIRCUMFERENCE  = WHEEL_DIAMETER * Math.PI;
    private double INCHES_PER_COUNT     = WHEEL_CIRCUMFERENCE / ENCODER_CPR;



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
        FLM.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        FRM.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        BLM.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        BRM.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
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


    public void update(double lee, double ree, double cee) {

        // Calculate encoder deltas
        double ld = lee - prev_le;
        double rd = ree - prev_re;
        double cd = cee - prev_ce;

        // Calculate phi, or the delta of our angle
        double ph = (rd * INCHES_PER_COUNT - ld * INCHES_PER_COUNT) / ROBOT_DIAMETER;

        // The arclength of movement forward/backward
        double dc = (rd * INCHES_PER_COUNT + ld * INCHES_PER_COUNT) / 2;

        // The arclength of movement left/right
        double sc = (cd * INCHES_PER_COUNT) + (ph * CENTER_WHEEL_OFFSET);

        // Calculate the new angle of the robot using the difference between the left and right encoder
        a = (ree * INCHES_PER_COUNT - lee * INCHES_PER_COUNT) / ROBOT_DIAMETER;
        h = a * (-180/Math.PI);

        // Calculate the new position of the robot by adding the arc vector to the absolute pos
        double sinph = Math.sin(ph);
        double cosph = Math.cos(ph);

        double s;
        double c;

        // If the arc turn is small enough, do this instead to avoid a div by zero error
        if(Math.abs(ph) < 1E-9) {
            s = 1.0 - 1.0 / 6.0 * ph * ph;
            c = 0.5 * ph;
        } else {
            s = sinph / ph;
            c = (1 - cosph) / ph;
        }

        // Find our x and y translations relative to the origin pose (0,0,0)
        double rel_x = sc * s - dc * c;
        double rel_y = sc * c - dc * s;

        // Transform those x and y translations to the actual rotation of our robot, and translate our robots positions to the new spot
        x += rel_x * Math.cos(a) - rel_y * Math.sin(a);
        y += rel_x * Math.sin(a) + rel_y * Math.cos(a);

        /* OLD BAD BAD BAD CODE THAT DOESN'T REALLY WORK AT ALL REALLY
        y += (dc * Math.cos(a + (ph / 2))) - (sc * Math.sin(a + (ph / 2)));
        x -= (dc * Math.sin(a + (ph / 2))) + (sc * Math.cos(a + (ph / 2)));
        */

        // Used to calculate deltas for next loop
        prev_le = lee;
        prev_re = ree;
        prev_ce = cee;

    }


    public void tryToGoBad(double xgo, double ygo, double hgo, double speed){
        disY = ygo-y;
        disX = xgo-x;
        disH = (hgo/(-180/Math.PI))-a;

        distance2 = Math.hypot(disX,disY);

        idealHeading = Math.atan2(disY,disX);

        rightPow = distance2 * Math.cos(idealHeading-a) * 1 * speed;
        forePow = distance2 * Math.sin(idealHeading-a) * 1 * speed;

        if (distance2>10){
            turnPow = (idealHeading-a) * 1 * speed;
        }
        else{
            turnPow = disH * 1 * speed;
        }

        //teledrive(forePow,rightPow,turnPow);


    }

    public boolean newNextStep(double xgo, double ygo, double hgo){
        if(!(((xgo-x)<1&&(xgo-x)>-1) && ((ygo-y)>-1&&(ygo-y)<1) && ((hgo-h)<1&&(hgo-h)>-1))){
            time.reset();
        }
        if (time.seconds()>.1&&(((xgo-x)<1&&(xgo-x)>-1) && ((ygo-y)>-1&&(ygo-y)<1) && ((hgo-h)<1&&(hgo-h)>-1))){
            return false;
        }
        else{
            return true;
        }
    }

    public void driveControl(double xgo, double ygo, double hgo, double preferredAngle, double turnSpeed){
        update(-le.getCurrentPosition(), -re.getCurrentPosition(),-be.getCurrentPosition());

        double disY = ygo-y;
        double disX = xgo-x;
        double disH = hgo-h;

        double distanceToTarget = Math.hypot(disX,disY);

        double absoluteAngleToTarget = Math.atan2(disY,disX);
        double relativeAngleToTarget = angleWrap(absoluteAngleToTarget-a);

        double relativeXToTarget = Math.cos(relativeAngleToTarget) * distanceToTarget;
        double relativeYToTarget = Math.sin(relativeAngleToTarget) * distanceToTarget;

        double movementXPower = relativeXToTarget / (Math.abs(relativeXToTarget) + Math.abs(relativeYToTarget));
        double movementYPower = relativeYToTarget / (Math.abs(relativeYToTarget) + Math.abs(relativeXToTarget));

        double relativeTurnAngle = relativeAngleToTarget - Math.toRadians(180) + preferredAngle;

        teledrive(movementYPower,movementXPower, Range.clip(relativeTurnAngle/Math.toRadians(30),-1,1)*turnSpeed);





    }

    public static double angleWrap(double angle){
        while(angle<-Math.PI){
            angle += 2*Math.PI;
        }
        while(angle > Math.PI){
            angle -= 2*Math.PI;
        }

        return angle;
    }




    public double odoHeadding(){
        return((re.getCurrentPosition()-le.getCurrentPosition())*.0025393278688);
    }
    public double odoForward(){
        return(((re.getCurrentPosition()+le.getCurrentPosition())*.5)*.0005737)-forwardreset;
    }
    public void resetFEC(){
        forwardreset=forwardreset+odoForward();
    }
    public double odoRight(){
        return(((be.getCurrentPosition())*.0005737)-(odoHeadding()*.5));
    }


    /*public double odoX(){
        odoR2 = odoRight()-odoR;
        odoF2=odoForward()-odoF;
        odoR = odoRight();
        odoF=odoForward();
        return ((odoR2*Math.cos(odoHeadding()))+(odoR2*Math.sin(odoHeadding())))+startx;
    }
    public double odoY(){
        return ((odoF2*Math.cos(odoHeadding()))+(odoR2*Math.sin(odoHeadding())));
    }*/
    public void turnforward(double distanceForward, double turn, double speed, double time){
//forward
        dControlF();
        /*if(((distanceForward-odoForward())*.15)>1){
            distance=1;
        }
        else if(((distanceForward-odoForward())*.15)<-1){
            distance=-1;
        }
        /*else if(Math.abs((distanceForward-odoForward())*.09)<.2){
            distance=((distanceForward-odoForward())*.05);
        }
        else if((distanceForward-odoForward())<10 && distanceForward-odoForward()>-10){
            distance=(distanceForward-odoForward())*.05;
        }
        else{

        }*/
        distance=(distanceForward-odoForward())*.15;
//turn
        dControlT();
        /*if(((turn-odoHeadding())*-.03)>1){
            turnDifference=1;
        }
        else if(((turn-odoHeadding())*-.03)<-1){
            turnDifference=-1;
        }
        /*else if(Math.abs(turn-odoHeadding()*-.017)<.1){
            turnDifference=(turn-odoHeadding())*-.04;
        }
        else if((turn-odoHeadding())<23 && turn-odoHeadding()>-23){
            turnDifference=(turn-odoHeadding())*-.01;
        }
        else{

        }*/
        turnDifference=(turn-odoHeadding())*-.043;
//time and drive
        if(time<.3333333){
            teledrive(((distance+dControlF)*speed*(time*3)),0,((turnDifference+dControlT)*speed*(time*3)));

        }
        else{
            teledrive(((distance+dControlF)*speed),0,((turnDifference+dControlT)*speed));
        }

    }
    public boolean nextStep(double fore,double turn){
        if(!((fore-odoForward())>-.5 && (fore-odoForward())<.5&&(turn-odoHeadding())<1.5&&(turn-odoHeadding())>-1.5)){
            time.reset();
        }
        if((time.seconds()>.12)&&((fore-odoForward())>-.5 && (fore-odoForward())<.5&&(turn-odoHeadding())<1.5&&(turn-odoHeadding())>-1.5)){
            return (false);
        }
        else{
            return true;
        }
    }
    public void dControlF(){
        while(timeDF.seconds()>.0333333333333){
            double delta = (odoForward()-prev_df);
            dControlF=delta*-.44;
            prev_df=odoForward();
            timeDF.reset();
        }
    }
    public void dControlT(){
        while(timeDT.seconds()>.0333333333333){
            double delta = (odoHeadding()-prev_dt);
            dControlT=delta*.047;
            prev_dt=odoHeadding();
            timeDT.reset();
        }
    }
    /*public void deltaTime(){
        double delta = timeD.seconds()-lastTime;
        deltaTime=delta*1000000;
        lastTime=timeD.seconds();
    }*/
    public void odoDrive(double x, double y,double headding){
        //distance=((Math.sqrt((x*x)+(y*y)))-(Math.sqrt((odoX()*odoX())+(odoY()*odoY()))));

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
        update(-le.getCurrentPosition(),-re.getCurrentPosition(),-be.getCurrentPosition());
        tryToGoBad(-10,10,-45,.5);

        adrive.telemetry.addData("odoForward",odoForward());
        adrive.telemetry.addData("odoRight",odoRight());
        adrive.telemetry.addData("odoHeading",odoHeadding());
        //adrive.telemetry.addData("odoX",odoX());
        //adrive.telemetry.addData("odoY",odoY());
        adrive.telemetry.addData("odoDifference",distance);
        adrive.telemetry.addData("odoF",odoF);
        adrive.telemetry.addData("odoR",odoR);
        adrive.telemetry.addData("odoF2",odoF2);
        adrive.telemetry.addData("odoF2",odoR2);

        adrive.telemetry.addData("right module", re.getCurrentPosition());
        adrive.telemetry.addData("left module", le.getCurrentPosition());
        adrive.telemetry.addData("back module", be.getCurrentPosition());

        adrive.telemetry.addData("new X",x);
        adrive.telemetry.addData("new Y",y);
        adrive.telemetry.addData("new H r",a);
        adrive.telemetry.addData("new H d",h);

        adrive.telemetry.addData("#DisY",disY);
        adrive.telemetry.addData("#DisX",disX);
        adrive.telemetry.addData("#DisH",disH);
        adrive.telemetry.addData("#distance2",distance2);
        adrive.telemetry.addData("#idealHeading",idealHeading);
        adrive.telemetry.addData("#RightPow",rightPow);
        adrive.telemetry.addData("#forePow",forePow);
        adrive.telemetry.addData("#turnPow",turnPow);



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
    }
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
