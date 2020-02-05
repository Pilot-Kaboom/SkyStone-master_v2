package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;

public class VsionPipeline extends OpenCvPipeline{
    int avg1;
    int avg2;
    int avg3;
    Mat workingMat= new Mat();
    Mat submat1 = new Mat();
    Mat submat2 = new Mat();
    Mat submat3 = new Mat();
    Point point1 = new Point(10,100);
    Point point2 = new Point(50,180);
    Point point3 = new Point(120,100);
    Point point4 = new Point(160,180);
    Point point5 = new Point(270,100);
    Point point6 = new Point(310,180);
    @Override
    public Mat processFrame(Mat input)
    {
        /*
         * IMPORTANT NOTE: the input Mat that is passed in as a parameter to this method
         * will only dereference to the same image for the duration of this particular
         * invocation of this method. That is, if for some reason you'd like to save a copy
         * of this particular frame for later use, you will need to either clone it or copy
         * it to another Mat.
         */

        /*
         * Draw a simple box around the middle 1/2 of the entire frame
         */
        Imgproc.rectangle(submat1,point1,point2, new Scalar(100,0,0),1);
        Imgproc.rectangle(submat2,point3,point4, new Scalar(100,0,0),1);
        Imgproc.rectangle(submat3,point5,point6, new Scalar(100,0,0),1);

        workingMat = input;

        submat1 = workingMat.submat(new Rect(point1,point2));
        submat2 = workingMat.submat(new Rect(point3,point4));
        submat3 = workingMat.submat(new Rect(point5,point6));

        avg1 = (int) Core.mean(submat1).val[0]+(int) Core.mean(submat1).val[1];
        avg2 = (int)Core.mean(submat2).val[0]+(int) Core.mean(submat2).val[1];
        avg3 = (int)Core.mean(submat3).val[0]+(int) Core.mean(submat3).val[1];

        /**
         * NOTE: to see how to get data from your pipeline to your OpMode as well as how
         * to change which stage of the pipeline is rendered to the viewport when it is
         * tapped, please see {@link PipelineStageSwitchingExample}
         */

        return input;
    }
    public boolean stoneL(){
        return (avg1<avg2 && avg1<avg3);
    }
    public boolean stoneC(){
        return (avg2<avg1 && avg1<avg3);
    }
    public boolean stoneR(){
        return (avg3<avg2 && avg3<avg1);
    }
}



