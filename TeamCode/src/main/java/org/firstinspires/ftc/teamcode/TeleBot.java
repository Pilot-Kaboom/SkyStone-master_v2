package org.firstinspires.ftc.teamcode;

public abstract class TeleBot extends compiler {
    public Arm arm;
    public Lift lift;
    public Drive drive;
    public Intake intake;
    public Sensors sensor;
    @Override
    public void initiate(){
        arm = new Arm(this);
        lift=new Lift(this);
        drive=new Drive(this);
        intake=new Intake(this);
        sensor=new Sensors(this);
        while(!isStarted() && !isStopRequested() && !opModeIsActive()){
            lift.resetEC();
            intake.capper(false);
            time.reset();

            sensor.touchInit();
            arm.clawcon(false,true,false);
            arm.elbowcon(true,false,true, false, false);
            //arm.wristcon(false,false,true, false,false);
            arm.telemetry();
            lift.telem();
            drive.resetEC();
            drive.ECtelem();
            intake.telemetry();
            sensor.telem();
            telemetry.update();
        }
    }
}
