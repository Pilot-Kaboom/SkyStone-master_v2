package org.firstinspires.ftc.teamcode;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvPipeline;

public abstract class AutoBot extends compiler {

    public Arm arm;
    public Lift lift;
    public Drive drive;
    public Intake intake;
    public Sensors sensor;
    public VsionPipeline vision;
    OpenCvCamera webcam;
    @Override
    public void initiate(){
        arm = new Arm(this);
        lift=new Lift(this);
        drive=new Drive(this);
        intake=new Intake(this);
        sensor=new Sensors(this);
        vision = new VsionPipeline();
        while(!isStarted() && !isStopRequested()){
            int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
            webcam = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, "Webcam 1"), cameraMonitorViewId);

            webcam.openCameraDevice();
            webcam.setPipeline(new VsionPipeline());
            webcam.startStreaming(320, 240, OpenCvCameraRotation.UPRIGHT);
            telemetry.addData("colorL", vision.avg1);
            telemetry.addData("colorC", vision.avg2);
            telemetry.addData("colorR", vision.avg3);
            telemetry.addData("StoneL", vision.stoneL());
            telemetry.addData("StoneC", vision.stoneC());
            telemetry.addData("StoneR", vision.stoneR());

            intake.resetEC();
            lift.resetEC();
            drive.resetEC();
            intake.lift(false,false,false);
            arm.clawcon(false, false,true);
            arm.elbowcon(false,false,true, false, false);
            arm.wristcon(false,false,true, false,false);
            sensor.telem();
            arm.telemetry();
            drive.ECtelem();
            lift.telem();
            intake.telemetry();
            telemetry.update();
        }
        if(isStarted()||isStopRequested()){
            webcam.stopStreaming();
            webcam.closeCameraDevice();
        }
    }


}
