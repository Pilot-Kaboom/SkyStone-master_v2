package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name ="BlueStone", group = "autonomous1")
public class BlueStoneAuto extends AutoBot{
    @Override
    public void run() {
        //go away from the wall
        time.reset();
        while (opModeIsActive() && drive.nextStep(5,0)){
            drive.turnforward(5,0,.8, time.seconds());
            intake.intake(1);
        }
        drive.StopMotors();
        drive.resetFEC();
//turn toward block
        time.reset();
        while (opModeIsActive() && rs && drive.nextStep(0,-36.5)){
            drive.turnforward(0,-36.5,.9, time.seconds());
            arm.clawcon(false,true,false);

        }
        while (opModeIsActive() && cs && drive.nextStep(0,-26.5)){
            drive.turnforward(0,-26.5,.9, time.seconds());
            arm.clawcon(false,true,false);

        }
        while (opModeIsActive() && ls && drive.nextStep(0,-15.5)){
            drive.turnforward(0,-15.5,1, time.seconds());
            arm.clawcon(false,true,false);

        }
        drive.StopMotors();
        drive.resetFEC();

//go towards block
        time.reset();
        while (opModeIsActive() && rs && drive.nextStep(17,-36.5)){
            drive.turnforward(17,-36.5,.7, time.seconds());

        }
        while (opModeIsActive() && cs && drive.nextStep(16,-26.5)){
            drive.turnforward(16,-26.5,.7, time.seconds());

        }
        while (opModeIsActive() && ls && drive.nextStep(12.5,-15.5)){
            drive.turnforward(12.5,-15.5,.7, time.seconds());

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
        while (opModeIsActive()&& ls && drive.odoForward()>-7.5){
            drive.teledrive(-.25,0,0);
        }
        while (opModeIsActive()&&cs &&drive.odoForward()>-8.5){
            drive.teledrive(-.25,0,0);
        }
        while (opModeIsActive()&&rs &&drive.odoForward()>-10){
            drive.teledrive(-.25,0,0);
        }
        drive.StopMotors();
        drive.resetFEC();
//turn towards bridge
        time.reset();
        while (opModeIsActive()&& drive.nextStep(0,-90.5)){
            drive.turnforward(0,-90.5,.8, time.seconds());
            intake.intake(0);
            intake.lift(true,false,false);
        }
        intake.intake(0);
        drive.StopMotors();
        drive.resetFEC();
//drive under bridge
        time.reset();
        while (opModeIsActive() && drive.odoForward()>-65){
            arm.clawcon(false,false,true);
            drive.turnforward(-65,-90,.8, time.seconds());
        }
//different drive distance to foundation
        time.reset();
        while (opModeIsActive() && ls && drive.nextStep(-84,-90)){
            arm.clawcon(false,false,true);
            drive.turnforward(-84,-90,.8, time.seconds());
            if(lift.echight()>-150){
                lift.manualmanual(-.75);
            }
            else{
                lift.manualmanual(0);
            }
            telemetry.addData("lift hight",lift.echight());

        }
        while (opModeIsActive() &&cs&& drive.nextStep(-88,-90)){
            arm.clawcon(false,false,true);
            drive.turnforward(-88,-90,.8, time.seconds());
            if(lift.echight()>-150){
                lift.manualmanual(-.75);
            }
            else{
                lift.manualmanual(0);
            }
            telemetry.addData("lift hight",lift.echight());

        }
        while (opModeIsActive() && rs && drive.nextStep(-92,-90)){
            arm.clawcon(false,false,true);
            drive.turnforward(-92,-90,.8, time.seconds());
            if(lift.echight()>-150){
                lift.manualmanual(-.75);
            }
            else{
                lift.manualmanual(0);
            }
            telemetry.addData("lift hight",lift.echight());

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
        while (opModeIsActive() && drive.nextStep(0,0)){
            arm.clawcon(false,false,true);
            drive.turnforward( 0,0,.8, time.seconds());
            if(lift.echight()>-150){
                lift.manualmanual(-.75);
            }
            else{
                lift.manualmanual(0);
            }
            intake.lift(false,false,false);
            telemetry.addData("lift hight",lift.echight());

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
        while (opModeIsActive()&& drive.nextStep(0,-85.2)) {
            lift.manualmanual(.5);
            arm.elbowcon(false,false,true,false,false);
            drive.turnforward(0, -85.2, .8, time.seconds());
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
        while (opModeIsActive() && ls && drive.nextStep(45,-86)){
            arm.clawcon(false,false,true);
            drive.turnforward(45,-86,.8, time.seconds());

        }
        while (opModeIsActive() &&cs&& drive.nextStep(53,-86)){
            arm.clawcon(false,false,true);
            drive.turnforward(53,-86,.8, time.seconds());
        }
        while (opModeIsActive() && rs && drive.nextStep(63,-86)){
            arm.clawcon(false,false,true);
            drive.turnforward(63,-86,.8, time.seconds());
        }
        drive.StopMotors();
        drive.resetFEC();
//turn at 45 to collect stone 2
        time.reset();
        while (opModeIsActive()&& drive.nextStep(0,-45)) {
            drive.turnforward(0, -45, .9,time.seconds());
            arm.clawcon(false,true,false);

        }
        drive.StopMotors();
        drive.resetFEC();
// go fast towards stone    2nd
        time.reset();
        while (opModeIsActive()&& drive.nextStep(8,-45)) {
            drive.turnforward(8, -45, .8, time.seconds());
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
        while (opModeIsActive()&& drive.nextStep(0,-85)) {
            drive.turnforward(0, -85, 1, time.seconds());
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
        while (opModeIsActive() && ls && drive.nextStep(-60,-85)){
            arm.clawcon(false,false,true);
            drive.turnforward(-60,-85,.8, time.seconds());
            if(lift.echight()>-150){
                lift.manualmanual(-.75);
            }
            else{
                lift.manualmanual(0);
            }

        }while (opModeIsActive() &&cs&& drive.nextStep(-66,-85)){
            arm.clawcon(false,false,true);
            drive.turnforward(-66,-85,.8, time.seconds());
            if(lift.echight()>-150){
                lift.manualmanual(-.75);
            }
            else{
                lift.manualmanual(0);
            }

        }
        while (opModeIsActive() && rs && drive.nextStep(-74,-85)){
            arm.clawcon(false,false,true);
            drive.turnforward(-74,-85,.8, time.seconds());
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
        while (opModeIsActive() && drive.nextStep(0,0)){
            arm.clawcon(false,false,true);
            drive.turnforward( 0,0,.8, time.seconds());
            if(lift.echight()>-150){
                lift.manualmanual(-.75);
            }
            else{
                lift.manualmanual(0);
            }
        }
//slowly approach foundation to grab it
        while(opModeIsActive()&& drive.odoForward()<6){
            drive.teledrive(.15,0,0);
            arm.elbowcon(false,true,false,false,false);
        }
        drive.StopMotors();
        drive.resetFEC();
        intake.lift(false,false,false);
        sleep(250);
//turn with foundation
        while(opModeIsActive() && drive.odoHeadding()<90){
            lift.manualmanual(.0);
            drive.teledrive(-.7,.4,-.65);
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
        while(opModeIsActive() && drive.nextStep(-45,90)){
            if(time.seconds()>.15){
                intake.lift(false,false,false);
            }
            lift.manualmanual(.5);
            drive.turnforward(-45,90,.9,time.seconds());
        }
        drive.StopMotors();
        time.reset();
        sleep(500);
    }
}
