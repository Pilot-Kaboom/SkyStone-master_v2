package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="testAuto",group = "autonomous1")
public class TestAuto extends AutoBot{

    @Override
    public void run() {
        while(opModeIsActive() && drive.newNextStep(-10,10,-45)){
            drive.tryToGoBad(-10,10,-45,.5);
        }
        drive.StopMotors();

    }
}
