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
        intake.intake(0);
        drive.resetFEC();


        while (opModeIsActive() && rs && drive.odoHeadding()>-36){
            drive.turnforward(0,-36,.8);
            arm.clawcon(false,true,false);

        }
        while (opModeIsActive() && cs && drive.odoHeadding()>-27){
            drive.turnforward(0,-27,.8);
            arm.clawcon(false,true,false);

        }
        while (opModeIsActive() && ls && drive.odoHeadding()>-13){
            drive.turnforward(0,-13,.8);
            arm.clawcon(false,true,false);

        }
        drive.StopMotors();
        drive.resetFEC();


        while (opModeIsActive() && rs && drive.odoForward()<21){
            drive.turnforward(21,-35,.8);

        }
        while (opModeIsActive() && cs && drive.odoForward()<20){
            drive.turnforward(20,-26,.8);

        }
        while (opModeIsActive() && ls && drive.odoForward()<15){
            drive.turnforward(15,-15,.8);

        }
        drive.StopMotors();
        drive.resetFEC();

        while (opModeIsActive()&& drive.odoForward()<12){
            drive.teledrive(.15,0,0);
            intake.intake(1);
        }
        drive.StopMotors();
        drive.resetFEC();

        while (opModeIsActive()&& ls && drive.odoForward()>-6.5){
            drive.teledrive(-.2,0,0);
        }
        while (opModeIsActive()&&cs &&drive.odoForward()>-7.75){
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
        while (opModeIsActive() && drive.odoForward()>-94){
            arm.clawcon(false,false,true);
            drive.turnforward(-94,-90,.8);
            if(lift.echight()>-400){
                lift.manualmanual(-.75);
            }
            else{
                lift.manualmanual(0);
            }

        }
        drive.StopMotors();
        drive.resetFEC();
        while(opModeIsActive()&& drive.odoForward()<0){
            drive.turnforward(0,-90,.66);
        }
        while (opModeIsActive() && drive.odoHeadding()<0){
            arm.clawcon(false,false,true);
            drive.turnforward( 0,0,.8);
            if(lift.echight()>-400){
                lift.manualmanual(-.75);
            }
            else{
                lift.manualmanual(0);
            }
        }
        drive.StopMotors();
        drive.resetFEC();
        while(opModeIsActive()&& drive.odoForward()<4){
            drive.teledrive(.15,0,0);
        }
        drive.StopMotors();
        drive.resetFEC();
        intake.lift(false,false,false);
        while(opModeIsActive() && drive.odoHeadding()<90){
            drive.teledrive(-.67,.67,-.45);
        }
        drive.StopMotors();




    }
}
