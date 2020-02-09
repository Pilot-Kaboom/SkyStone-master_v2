package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name ="BlueStone", group = "autonomous1")
public class BlueStoneAuto extends AutoBot{
    @Override
    public void run() {
        while (opModeIsActive() && drive.odoForward()<5){
            drive.turnforward(5,0,.5);
            intake.intake(1);
        }
        drive.StopMotors();
        drive.resetFEC();

        while (opModeIsActive() && rs && drive.odoHeadding()>-36.5){
            drive.turnforward(0,-36,.8);
            arm.clawcon(false,true,false);

        }
        while (opModeIsActive() && cs && drive.odoHeadding()>-27){
            drive.turnforward(0,-27,.8);
            arm.clawcon(false,true,false);

        }
        while (opModeIsActive() && ls && drive.odoHeadding()>-14){
            drive.turnforward(0,-13,1);
            arm.clawcon(false,true,false);

        }
        drive.StopMotors();
        drive.resetFEC();


        while (opModeIsActive() && rs && drive.odoForward()<21){
            drive.turnforward(21,-35,.8);

        }
        while (opModeIsActive() && cs && drive.odoForward()<17){
            drive.turnforward(20,-26,.8);

        }
        while (opModeIsActive() && ls && drive.odoForward()<13.5){
            drive.turnforward(15,-15,.8);

        }
        drive.StopMotors();
        drive.resetFEC();

        while (opModeIsActive()&& drive.odoForward()<12.5){
            drive.teledrive(.175,0,0);
            intake.intake(1);
        }
        drive.StopMotors();
        drive.resetFEC();

        while (opModeIsActive()&& ls && drive.odoForward()>-6.5){
            drive.teledrive(-.2,0,0);
        }
        while (opModeIsActive()&&cs &&drive.odoForward()>-8.5){
            drive.teledrive(-.2,0,0);
        }
        while (opModeIsActive()&&rs &&drive.odoForward()>-9){
            drive.teledrive(-.2,0,0);
        }
        drive.StopMotors();
        drive.resetFEC();

        while (opModeIsActive()&& drive.odoHeadding()>-90){
            drive.turnforward(0,-90,.8);
            intake.intake(0);
            intake.lift(true,false,false);
        }
        intake.intake(0);
        drive.StopMotors();
        drive.resetFEC();
        while (opModeIsActive() && drive.odoForward()>-55){
            arm.clawcon(false,false,true);
            drive.turnforward(-55,-90,.8);
        }
        while (opModeIsActive() && ls && drive.odoForward()>-86){
            arm.clawcon(false,false,true);
            drive.turnforward(-85,-90,.8);
            if(lift.echight()>-500){
                lift.manualmanual(-.75);
            }
            else{
                lift.manualmanual(0);
            }

        }while (opModeIsActive() &&cs&& drive.odoForward()>-90){
            arm.clawcon(false,false,true);
            drive.turnforward(-85,-90,.8);
            if(lift.echight()>-500){
                lift.manualmanual(-.75);
            }
            else{
                lift.manualmanual(0);
            }

        }
        while (opModeIsActive() && rs && drive.odoForward()>-94){
            arm.clawcon(false,false,true);
            drive.turnforward(-85,-90,.8);
            if(lift.echight()>-500){
                lift.manualmanual(-.75);
            }
            else{
                lift.manualmanual(0);
            }

        }
        drive.StopMotors();
        drive.resetFEC();
        while(opModeIsActive()&& drive.odoForward()<0){
            drive.turnforward(0,-90,.75);
            if(lift.echight()>-500){
                lift.manualmanual(-.75);
            }
            else{
                lift.manualmanual(0);
            }
        }
        drive.StopMotors();
        while (opModeIsActive() && drive.odoHeadding()<0){
            arm.clawcon(false,false,true);
            drive.turnforward( 0,0,.8);
            if(lift.echight()>-500){
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
        while(opModeIsActive()&& drive.odoForward()<5){
            drive.teledrive(.15,0,0);
            arm.elbowcon(true,false,false,false,false);
        }
        drive.StopMotors();
        drive.resetFEC();
        time.reset();
        while(opModeIsActive() && time.seconds()<.5){
            arm.clawcon(false,true,false);
        }
        while(opModeIsActive()&& drive.odoForward()>-1){
            drive.teledrive(-.25,0,0);
            lift.manualmanual(.5);
            arm.elbowcon(false,false,true,false,false);
        }
        drive.StopMotors();
        drive.resetFEC();
        while (opModeIsActive()&& drive.odoHeadding()>-85) {
            drive.turnforward(0, -85, .7);
        }
        drive.StopMotors();
        drive.resetFEC();


        while (opModeIsActive() && ls && drive.odoForward()<43){
            arm.clawcon(false,false,true);
            drive.turnforward(45,-86,.8);

        }
        while (opModeIsActive() &&cs&& drive.odoForward()<45){
            arm.clawcon(false,false,true);
            drive.turnforward(50,-86,.8);
        }
        while (opModeIsActive() && rs && drive.odoForward()<52){
            arm.clawcon(false,false,true);
            drive.turnforward(55,-86,.8);
        }
        drive.StopMotors();
        drive.resetFEC();

        while (opModeIsActive()&& drive.odoHeadding()<-45) {
            drive.turnforward(0, -45, .8);
            arm.clawcon(false,true,false);

        }
        drive.StopMotors();
        drive.resetFEC();
        while (opModeIsActive()&& drive.odoForward()<15) {
            drive.turnforward(15, -45, .8);
            intake.intake(1);
        }
        drive.StopMotors();
        drive.resetFEC();
        while (opModeIsActive()&& drive.odoForward()<12){
            drive.teledrive(.175,0,0);
            intake.intake(1);
        }
        drive.StopMotors();
        drive.resetFEC();
        while (opModeIsActive()&& ls && drive.odoForward()>-10){
            drive.teledrive(-.2,0,0);
        }
        drive.StopMotors();
        drive.resetFEC();
        while (opModeIsActive()&& drive.odoHeadding()>-85) {
            drive.turnforward(0, -85, .7);
            intake.intake(0);
            intake.lift(true,false,false);
        }
        drive.StopMotors();
        drive.resetFEC();
        while (opModeIsActive()&& drive.odoForward()>-15) {
            drive.turnforward(-15, -85, .8);
            arm.clawcon(false,false,true);
        }
        while (opModeIsActive() && ls && drive.odoForward()>-40){
            arm.clawcon(false,false,true);
            drive.turnforward(-40,-85,.8);
            if(lift.echight()>-500){
                lift.manualmanual(-.75);
            }
            else{
                lift.manualmanual(0);
            }

        }while (opModeIsActive() &&cs&& drive.odoForward()>-45){
            arm.clawcon(false,false,true);
            drive.turnforward(-45,-85,.8);
            if(lift.echight()>-500){
                lift.manualmanual(-.75);
            }
            else{
                lift.manualmanual(0);
            }

        }
        while (opModeIsActive() && rs && drive.odoForward()>-50){
            arm.clawcon(false,false,true);
            drive.turnforward(-50,-90,.8);
            if(lift.echight()>-500){
                lift.manualmanual(-.75);
            }
            else{
                lift.manualmanual(0);
            }

        }
        drive.StopMotors();
        drive.resetFEC();
        while(opModeIsActive()&& drive.odoForward()<0){
            drive.turnforward(0,-90,.75);
            if(lift.echight()>-500){
                lift.manualmanual(-.75);
            }
            else{
                lift.manualmanual(0);
            }
        }
        drive.StopMotors();
        while (opModeIsActive() && drive.odoHeadding()<0){
            arm.clawcon(false,false,true);
            drive.turnforward( 0,0,.8);
            if(lift.echight()>-500){
                lift.manualmanual(-.75);
            }
            else{
                lift.manualmanual(0);
            }
        }

        while(opModeIsActive()&& drive.odoForward()<10){
            drive.teledrive(.15,0,0);
            arm.elbowcon(true,false,false,false,false);
        }
        drive.StopMotors();
        drive.resetFEC();
        intake.lift(false,false,false);
        sleep(250);
        while(opModeIsActive() && drive.odoHeadding()<90){
            lift.manualmanual(-.25);
            drive.teledrive(-.67,.5,-.65);
        }
        drive.StopMotors();
        drive.resetFEC();
        time.reset();
        while(opModeIsActive()&& time.seconds()<1){
            drive.teledrive(.45,0,0);
            arm.clawcon(false,true,false);
        }
        drive.StopMotors();
        drive.resetFEC();
        time.reset();



    }
}
