package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name ="RedStone", group = "autonomous1")
public class RedStoneAuto extends AutoBot{
    @Override
    public void run() {
        //go away from the wall
        time.reset();
        while (opModeIsActive() && drive.odoForward()<5){
            drive.turnforward(5,0,.8, time.seconds());
            intake.intake(1);
        }
        drive.StopMotors();
        drive.resetFEC();
//turn toward block
        time.reset();
        while (opModeIsActive() && ls && drive.odoHeadding()<38){
            drive.turnforward(0,38.5,.9, time.seconds());
            arm.clawcon(false,true,false);

        }
        while (opModeIsActive() && cs && drive.odoHeadding()<29.25){
            drive.turnforward(0,29.25,.9, time.seconds());
            arm.clawcon(false,true,false);

        }
        while (opModeIsActive() && rs && drive.odoHeadding()<18){
            drive.turnforward(0,18,1, time.seconds());
            arm.clawcon(false,true,false);

        }
        drive.StopMotors();
        drive.resetFEC();

//go towards block
        time.reset();
        while (opModeIsActive() && ls && drive.odoForward()<18.5){
            drive.turnforward(18.5,38.5,.5, time.seconds());

        }
        while (opModeIsActive() && cs && drive.odoForward()<15.5){
            drive.turnforward(15.5,29.25, .5, time.seconds());

        }
        while (opModeIsActive() && rs && drive.odoForward()<12.5){
            drive.turnforward(12.5,18,.5, time.seconds());

        }
        drive.StopMotors();
        drive.resetFEC();
//slowly colect block
        while (opModeIsActive()&& drive.odoForward()<13.5){
            drive.teledrive(.175,0,0);
            intake.intake(1);
        }
        drive.StopMotors();
        drive.resetFEC();
//slowly back away
        while (opModeIsActive()&& rs && drive.odoForward()>-7.5){
            drive.teledrive(-.25,0,0);
        }
        while (opModeIsActive()&&cs &&drive.odoForward()>-9.5){
            drive.teledrive(-.25,0,0);
        }
        while (opModeIsActive()&&ls &&drive.odoForward()>-10.5){
            drive.teledrive(-.25,0,0);
        }
        drive.StopMotors();
        drive.resetFEC();
//turn towards bridge
        time.reset();
        while (opModeIsActive()&& drive.odoHeadding()<90){
            drive.turnforward(0,90,.8, time.seconds());
            intake.intake(0);
            intake.lift(true,false,false);
        }
        intake.intake(0);
        drive.StopMotors();
        drive.resetFEC();
//drive under bridge
        time.reset();
        while (opModeIsActive() && drive.odoForward()>-55){
            arm.clawcon(false,false,true);
            drive.turnforward(-85,90,.7,time.seconds());
        }
//different drive distance to foundation
        while (opModeIsActive() && rs && drive.odoForward()>-81){
            arm.clawcon(false,false,true);
            drive.turnforward(-81,90,.7, time.seconds());
            if(lift.echight()>-150){
                lift.manualmanual(-.75);
            }
            else{
                lift.manualmanual(0);
            }

        }
        while (opModeIsActive() &&cs&& drive.odoForward()>-84){
            arm.clawcon(false,false,true);
            drive.turnforward(-84,90,.7, time.seconds());
            if(lift.echight()>-150){
                lift.manualmanual(-.75);
            }
            else{
                lift.manualmanual(0);
            }

        }
        while (opModeIsActive() && ls && drive.odoForward()>-88){
            arm.clawcon(false,false,true);
            drive.turnforward(-88,90,.7, time.seconds());
            if(lift.echight()>-150){
                lift.manualmanual(-.75);
            }
            else{
                lift.manualmanual(0);
            }

        }
        drive.StopMotors();
        drive.resetFEC();
//go forward for overshoot
        /*time.reset();
        while(opModeIsActive()&& drive.odoForward()<0){
            drive.turnforward(0,-88,.75,time.seconds());
            if(lift.echight()>-500){
                lift.manualmanual(-.75);
            }
            else{
                lift.manualmanual(0);
            }
        }
        drive.StopMotors();*/
//turn to face foundation
        time.reset();
        while (opModeIsActive() && drive.odoHeadding()>0){
            arm.clawcon(false,false,true);
            drive.turnforward( 0,0,.8, time.seconds());
            if(lift.echight()>-150){
                lift.manualmanual(-.75);
            }
            else{
                lift.manualmanual(0);
            }
            intake.lift(false,false,false);
        }
        drive.StopMotors();
        drive.resetFEC();
        lift.manualmanual(0);
//drive slowly forward to place the first stone
        while(opModeIsActive()&& drive.odoForward()<.5){
            drive.teledrive(.15,0,0);
            intake.intake(-.25);
            arm.elbowcon(false,true,false,false,false);
        }
        drive.StopMotors();
        drive.resetFEC();
        sleep(750);
        time.reset();
//drop first stone
        while(opModeIsActive() && time.seconds()<.05){
            arm.clawcon(false,true,false);
        }
//back away from foundation
        while(opModeIsActive()&& drive.odoForward()>-2){
            drive.teledrive(-.25,0,0);
            arm.elbowcon(false,false,true,false,false);
        }
        drive.StopMotors();
        drive.resetFEC();
//turn towards quarry
        time.reset();
        while (opModeIsActive()&& drive.odoHeadding()<95.5) {
            lift.manualmanual(.5);
            arm.elbowcon(false,false,true,false,false);
            drive.turnforward(0, 96, .8, time.seconds());
        }
        drive.StopMotors();
        drive.resetFEC();
        time.reset();
        sleep(100);
        /*while (opModeIsActive()&& drive.odoHeadding()<-85) {
            drive.turnforward(0, -85, 1, time.seconds());
        }
        drive.StopMotors();
        drive.resetFEC();*/
//do to the quarry for each stone
        time.reset();
        while (opModeIsActive() && rs && drive.odoForward()<49){
            arm.clawcon(false,false,true);
            drive.turnforward(45,86,.8, time.seconds());

        }
        while (opModeIsActive() &&cs&& drive.odoForward()<58){
            arm.clawcon(false,false,true);
            drive.turnforward(58,86,.8, time.seconds());
        }
        while (opModeIsActive() && ls && drive.odoForward()<61){
            arm.clawcon(false,false,true);
            drive.turnforward(63,86,.8, time.seconds());
        }
        drive.StopMotors();
        drive.resetFEC();
//turn at 45 to collect stone 2
        time.reset();
        while (opModeIsActive()&& drive.odoHeadding()>45) {
            drive.turnforward(0, 45, .9,time.seconds());
            arm.clawcon(false,true,false);

        }
        drive.StopMotors();
        drive.resetFEC();
// go fast towards stone    2nd
        time.reset();
        while (opModeIsActive()&& drive.odoForward()<6) {
            drive.turnforward(6, 45, .8, time.seconds());
            intake.intake(1);
        }
        drive.StopMotors();
        drive.resetFEC();
// go slow to stone and intake it  2nd
        while (opModeIsActive()&& drive.odoForward()<10){
            drive.teledrive(.175,0,0);
            intake.intake(1);
        }
        drive.StopMotors();
        drive.resetFEC();
// back away with stone        2nd
        while (opModeIsActive()&& drive.odoForward()>-10){
            drive.teledrive(-.2,0,0);
            intake.intake(1);
        }
        drive.StopMotors();
        drive.resetFEC();
//turn to face bridge        2nd
        time.reset();
        while (opModeIsActive()&& drive.odoHeadding()<89) {
            drive.turnforward(0, 89, .9, time.seconds());
            intake.intake(0);
            intake.lift(true,false,false);
        }
        drive.StopMotors();
        drive.resetFEC();
//back under bridge        2nd
        time.reset();
        while (opModeIsActive()&& drive.odoForward()>-40) {
            drive.turnforward(-62,90,.8,time.seconds());
            arm.clawcon(false,false,true);
        }
//distance to foundation per stone position 2nd
        while (opModeIsActive() && rs && drive.odoForward()>-60){
            arm.clawcon(false,false,true);
            drive.turnforward(-60,90,.8, time.seconds());
            if(lift.echight()>-150){
                lift.manualmanual(-.75);
            }
            else{
                lift.manualmanual(0);
            }

        }while (opModeIsActive() &&cs&& drive.odoForward()>-68){
            arm.clawcon(false,false,true);
            drive.turnforward(-66,90,.8, time.seconds());
            if(lift.echight()>-150){
                lift.manualmanual(-.75);
            }
            else{
                lift.manualmanual(0);
            }

        }
        while (opModeIsActive() && ls && drive.odoForward()>-76){
            arm.clawcon(false,false,true);
            drive.turnforward(-78,90,.8, time.seconds());
            if(lift.echight()>-150){
                lift.manualmanual(-.75);
            }
            else{
                lift.manualmanual(0);
            }

        }
        drive.StopMotors();
        drive.resetFEC();
//go forward for overshoot  2nd
        /*time.reset();
        while(opModeIsActive()&& drive.odoForward()<0){
            drive.turnforward(0,-85,.75, time.seconds());
            if(lift.echight()>-500){
                lift.manualmanual(-.75);
            }
            else{
                lift.manualmanual(0);
            }
        }
        drive.StopMotors();*/
//tun towards foundation 2nd
        time.reset();
        while (opModeIsActive() && drive.odoHeadding()>3){
            arm.clawcon(false,false,true);
            drive.turnforward( 0,3,.9, time.seconds());
            if(lift.echight()>-150){
                lift.manualmanual(-.75);
            }
            else{
                lift.manualmanual(0);
            }
        }
//slowly approach foundation to grab it
        while(opModeIsActive()&& drive.odoForward()<5.5){
            drive.teledrive(.15,0,0);
            arm.elbowcon(false,true,false,false,false);
        }
        drive.StopMotors();
        drive.resetFEC();
        intake.lift(false,false,false);
        sleep(250);
//turn with foundation
        while(opModeIsActive() && drive.odoHeadding()>-90){
            lift.manualmanual(.0);
            drive.teledrive(-.7,-.6,.65);
        }
        drive.StopMotors();
        drive.resetFEC();
        time.reset();
// plow foundation into the wall
        while(opModeIsActive()&& time.seconds()<1.15){
            drive.teledrive(.45,0,0);
            arm.clawcon(false,true,false);
            intake.lift(true,false,false);
            arm.elbowcon(false,false,true, false, false);
        }
        drive.StopMotors();
        drive.resetFEC();
        time.reset();
//go to park
        while(opModeIsActive() && drive.odoForward()>-43){
            if(time.seconds()>.15){
                intake.lift(false,false,false);
            }
            lift.manualmanual(.5);
            drive.turnforward(-43,-90,.9,time.seconds());
        }
        drive.StopMotors();
        time.reset();
        sleep(500);
    }
}
