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
    public boolean rs;
    public boolean cs;
    public boolean ls;
    OpenCvCamera webcam;
    @Override
    public void initiate(){
        arm = new Arm(this);
        lift=new Lift(this);
        drive=new Drive(this);
        intake=new Intake(this);
        sensor=new Sensors(this);
        vision = new VsionPipeline();
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        webcam = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, "Webcam 1"), cameraMonitorViewId);

        webcam.openCameraDevice();
        webcam.setPipeline(vision);
        webcam.startStreaming(320, 240, OpenCvCameraRotation.UPRIGHT);
        while(!isStarted() && !isStopRequested() && !opModeIsActive()){

            cs=vision.stoneC();
            ls=vision.stoneL();
            rs=vision.stoneR();

            telemetry.addData("colorL", vision.avg1);
            telemetry.addData("colorC", vision.avg2);
            telemetry.addData("colorR", vision.avg3);
            telemetry.addData("StoneL", ls);
            telemetry.addData("StoneC", cs);
            telemetry.addData("StoneR", rs);

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
    }


}
