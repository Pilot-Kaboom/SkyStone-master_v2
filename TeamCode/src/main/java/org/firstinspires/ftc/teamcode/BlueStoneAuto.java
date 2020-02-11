package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name ="BlueStone", group = "autonomous1")
public class BlueStoneAuto extends AutoBot{
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
        while (opModeIsActive() && rs && drive.odoHeadding()>-36.5){
            drive.turnforward(0,-36.5,.9, time.seconds());
            arm.clawcon(false,true,false);

        }
        while (opModeIsActive() && cs && drive.odoHeadding()>-27){
            drive.turnforward(0,-27,.9, time.seconds());
            arm.clawcon(false,true,false);

        }
        while (opModeIsActive() && ls && drive.odoHeadding()>-15.5){
            drive.turnforward(0,-16.5,1, time.seconds());
            arm.clawcon(false,true,false);

        }
        drive.StopMotors();
        drive.resetFEC();

//go towards block
        time.reset();
        while (opModeIsActive() && rs && drive.odoForward()<18){
            drive.turnforward(21,-35,.8, time.seconds());

        }
        while (opModeIsActive() && cs && drive.odoForward()<15.5){
            drive.turnforward(20,-26,.8, time.seconds());

        }
        while (opModeIsActive() && ls && drive.odoForward()<12.5){
            drive.turnforward(15,-15,.8, time.seconds());

        }
        drive.StopMotors();
        drive.resetFEC();
//slowly colect block
        while (opModeIsActive()&& drive.odoForward()<12.5){
            drive.teledrive(.175,0,0);
            intake.intake(1);
        }
        drive.StopMotors();
        drive.resetFEC();
//slowly back away
        while (opModeIsActive()&& ls && drive.odoForward()>-6.5){
            drive.teledrive(-.25,0,0);
        }
        while (opModeIsActive()&&cs &&drive.odoForward()>-8.5){
            drive.teledrive(-.25,0,0);
        }
        while (opModeIsActive()&&rs &&drive.odoForward()>-9){
            drive.teledrive(-.25,0,0);
        }
        drive.StopMotors();
        drive.resetFEC();
//turn towards bridge
        time.reset();
        while (opModeIsActive()&& drive.odoHeadding()>-90){
            drive.turnforward(0,-90,.8, time.seconds());
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
            drive.turnforward(-55,-90,.8, time.seconds());
        }
//different drive distance to foundation
        time.reset();
        while (opModeIsActive() && ls && drive.odoForward()>-83){
            arm.clawcon(false,false,true);
            drive.turnforward(-84,-90,.8, time.seconds());
            if(lift.echight()>-400){
                lift.manualmanual(-.75);
            }
            else{
                lift.manualmanual(0);
            }

        }
        while (opModeIsActive() &&cs&& drive.odoForward()>-87){
            arm.clawcon(false,false,true);
            drive.turnforward(-88,-90,.8, time.seconds());
            if(lift.echight()>-400){
                lift.manualmanual(-.75);
            }
            else{
                lift.manualmanual(0);
            }

        }
        while (opModeIsActive() && rs && drive.odoForward()>-90){
            arm.clawcon(false,false,true);
            drive.turnforward(-92,-90,.8, time.seconds());
            if(lift.echight()>-400){
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
        while (opModeIsActive() && drive.odoHeadding()<0){
            arm.clawcon(false,false,true);
            drive.turnforward( 0,0,.8, time.seconds());
            if(lift.echight()>-400){
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
        while(opModeIsActive()&& drive.odoForward()<2){
            drive.teledrive(.15,0,0);
            intake.intake(-.25);
            arm.elbowcon(true,false,false,false,false);
        }
        drive.StopMotors();
        drive.resetFEC();
        sleep(500);
        time.reset();
//drop first stone
        while(opModeIsActive() && time.seconds()<.25){
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
        while (opModeIsActive()&& drive.odoHeadding()>-86.2) {
            lift.manualmanual(.5);
            arm.elbowcon(false,false,true,false,false);
            drive.turnforward(0, -86.2, .7, time.seconds());
        }
        drive.StopMotors();
        drive.resetFEC();
        time.reset();
        /*while (opModeIsActive()&& drive.odoHeadding()<-85) {
            drive.turnforward(0, -85, 1, time.seconds());
        }
        drive.StopMotors();
        drive.resetFEC();*/
//do to the quarry for each stone
        time.reset();
        while (opModeIsActive() && ls && drive.odoForward()<45){
            arm.clawcon(false,false,true);
            drive.turnforward(48,-86,.8, time.seconds());

        }
        while (opModeIsActive() &&cs&& drive.odoForward()<51){
            arm.clawcon(false,false,true);
            drive.turnforward(54,-86,.8, time.seconds());
        }
        while (opModeIsActive() && rs && drive.odoForward()<59){
            arm.clawcon(false,false,true);
            drive.turnforward(60,-86,.8, time.seconds());
        }
        drive.StopMotors();
        drive.resetFEC();
//turn at 45 to collect stone 2
        time.reset();
        while (opModeIsActive()&& drive.odoHeadding()<-45) {
            drive.turnforward(0, -46, .8,time.seconds());
            arm.clawcon(false,true,false);

        }
        drive.StopMotors();
        drive.resetFEC();
// go fast towards stone    2nd
        time.reset();
        while (opModeIsActive()&& drive.odoForward()<8) {
            drive.turnforward(10, -45, .8, time.seconds());
            intake.intake(1);
        }
        drive.StopMotors();
        drive.resetFEC();
// go slow to stone and intake it  2nd
        while (opModeIsActive()&& drive.odoForward()<12){
            drive.teledrive(.175,0,0);
            intake.intake(1);
        }
        drive.StopMotors();
        drive.resetFEC();
// back away with stone        2nd
        while (opModeIsActive()&& drive.odoForward()>-11){
            drive.teledrive(-.2,0,0);
            intake.intake(1);
        }
        drive.StopMotors();
        drive.resetFEC();
//turn to face bridge        2nd
        time.reset();
        while (opModeIsActive()&& drive.odoHeadding()>-85) {
            drive.turnforward(0, -85, .9, time.seconds());
            intake.intake(0);
            intake.lift(true,false,false);
        }
        drive.StopMotors();
        drive.resetFEC();
//back under bridge        2nd
        time.reset();
        while (opModeIsActive()&& drive.odoForward()>-40) {
            drive.turnforward(-40, -85, .8, time.seconds());
            arm.clawcon(false,false,true);
        }
//distance to foundation per stone position 2nd
        time.reset();
        while (opModeIsActive() && ls && drive.odoForward()>-62){
            arm.clawcon(false,false,true);
            drive.turnforward(-60,-85,.8, time.seconds());
            if(lift.echight()>-500){
                lift.manualmanual(-.75);
            }
            else{
                lift.manualmanual(0);
            }

        }while (opModeIsActive() &&cs&& drive.odoForward()>-68){
            arm.clawcon(false,false,true);
            drive.turnforward(-66,-85,.8, time.seconds());
            if(lift.echight()>-500){
                lift.manualmanual(-.75);
            }
            else{
                lift.manualmanual(0);
            }

        }
        while (opModeIsActive() && rs && drive.odoForward()>-76){
            arm.clawcon(false,false,true);
            drive.turnforward(-74,-85,.8, time.seconds());
            if(lift.echight()>-500){
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
        while (opModeIsActive() && drive.odoHeadding()<0){
            arm.clawcon(false,false,true);
            drive.turnforward( 0,0,.8, time.seconds());
            if(lift.echight()>-500){
                lift.manualmanual(-.75);
            }
            else{
                lift.manualmanual(0);
            }
        }
//slowly approach foundation to grab it
        while(opModeIsActive()&& drive.odoForward()<6){
            drive.teledrive(.15,0,0);
            arm.elbowcon(true,false,false,false,false);
        }
        drive.StopMotors();
        drive.resetFEC();
        intake.lift(false,false,false);
        sleep(250);
//turn with foundation
        while(opModeIsActive() && drive.odoHeadding()<90){
            lift.manualmanual(.25);
            drive.teledrive(-.7,.4,-.65);
        }
        drive.StopMotors();
        drive.resetFEC();
        time.reset();
// plow foundation into the wall
        while(opModeIsActive()&& time.seconds()<1.25){
            drive.teledrive(.45,0,0);
            arm.clawcon(false,true,false);
            intake.lift(true,false,false);
        }
        drive.StopMotors();
        drive.resetFEC();
        time.reset();
//go to park
        while(opModeIsActive() && drive.odoForward()>-54){
            if(time.seconds()>.15){
                intake.lift(false,false,false);
            }
            lift.manualmanual(.5);
            arm.elbowcon(false, false,true,false,false);
            drive.turnforward(-55,90,.9,time.seconds());
        }
        drive.StopMotors();
        time.reset();
    }
}
