package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name ="BlueStone", group = "autonomous1")
public class BlueStoneAuto extends AutoBot{
    @Override
    public void run() {
        while (opModeIsActive()){
            webcam.stopStreaming();
            telemetry.addData("colorL", vision.avg1);
            telemetry.addData("colorC", vision.avg2);
            telemetry.addData("colorR", vision.avg3);
            telemetry.addData("StoneL", vision.stoneL());
            telemetry.addData("StoneC", vision.stoneC());
            telemetry.addData("StoneR", vision.stoneR());
            telemetry.update();
        }
    }
}
