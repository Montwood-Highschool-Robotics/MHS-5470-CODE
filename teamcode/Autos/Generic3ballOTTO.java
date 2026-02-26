package org.firstinspires.ftc.teamcode.Autos;

import android.view.View;

import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.NormalizedColorSensor;
import com.qualcomm.robotcore.util.ElapsedTime;

import com.qualcomm.hardware.limelightvision.Limelight3A;

@Autonomous
public class Generic3ballOTTO extends LinearOpMode {
    private PIDController controller;

    public double p = 0.00000000001, i = 0.1, d = 0.0000135;
    static final double COUNTS_PER_MOTOR_REV = 383.6;
    static final double DRIVE_GEAR_REDUCTION = 1.0;
    static final double WHEEL_DIAMETER_INCHES = 3.937;
    static final double COUNTS_PER_INCH = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) / (WHEEL_DIAMETER_INCHES * 3.1415);

    private ElapsedTime runtime = new ElapsedTime();

    private DcMotor FLW;
    private DcMotor FRW;
    private DcMotor BLW;
    private DcMotor BRW;

    private DcMotorEx Turret;
    private DcMotor Shooter1;
    private DcMotor Shooter2;
    private DcMotor Intake;


    private CRServo AngleFish;




    private CRServo Spin;

    private Limelight3A lightlimeA3;
    NormalizedColorSensor colorSensor;
    View relativeLayout;
    static final int STREAM_WITH = 1920;
    static final double STREAM_HEIGHT = 1080;
    Limelight3A limelight;
    Double Time;
    private static final double POSITION_TOLERANCE = 1.5;
    private static final double MIN_POWER = 0.0;
    private static final double MAX_POWER = 0.5;
    private double targetX = 0.0;
    private double integral = 0.0;
    private double lastError = 0.0;
    public double highTargetPose = 270;
    public double lowTargetPose = 0;
    public double curTargetPose = highTargetPose;
    double var = 79.5;
    @Override
    public void runOpMode() throws InterruptedException {
        FRW = hardwareMap.dcMotor.get("FRW");
        FLW = hardwareMap.dcMotor.get("FLW");
        BRW = hardwareMap.dcMotor.get("BRW");
        BLW = hardwareMap.dcMotor.get("BLW");
        DcMotor Shooter1 = hardwareMap.dcMotor.get("Shooter1");
        DcMotor Shooter2 = hardwareMap.dcMotor.get("Shooter2");
        DcMotor Intake = hardwareMap.dcMotor.get("Intake");
        DcMotor Turret = hardwareMap.dcMotor.get("Turret");
        CRServo AngleFish = hardwareMap.crservo.get("AngleFish");
        CRServo Spin = hardwareMap.crservo.get("Spin");







        FLW.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        BLW.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        FRW.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        BRW.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);










        waitForStart();
        Shooter1.setPower(-.79);
        Shooter1.setPower(-.79);
        sleep(3000);
        Intake.setPower(1);
        if (var == 79.5) {
// shoot ball 1

            Spin.setPower(-1);
            sleep(1650);
// wait
            Spin.setPower(0);
            sleep(2000);
// shoot ball 2
            Spin.setPower(-1);
            sleep(300);
// wait
            Spin.setPower(0);
            sleep(4500);
// shoot ball 3
            Spin.setPower(-1);
            sleep(2000);
// end
            Spin.setPower(0);


            Intake.setPower(0);
            driveForward(.25, 20, 10);

            terminateOpModeNow();

        }














































































































































    }



    public void driveForward(double speed, double dist, double timeout) {
        encoderDrive(speed, dist, dist, timeout);

    }

    public void turnLeft(double angle) {
        encoderDrive(.4, -angle, angle, 10);
    }



    public void turnRight(double angle) {
        encoderDrive(.4, angle, -angle, 10);
    }

    public void turn(double timeout, double angleL, double angleR) {
        encoderDrive(.4, angleL, angleR, timeout);
    }

    public void encoderDrive(double speed, double distL, double distR, double timeoutS) {
        int newLeftTarget;
        int newRightTarget;


        if (opModeIsActive()) {


            newLeftTarget = FLW.getCurrentPosition() + (int) (distL * COUNTS_PER_INCH);
            newRightTarget = FRW.getCurrentPosition() + (int) (distR * COUNTS_PER_INCH);

            FLW.setTargetPosition(-newLeftTarget);
            BLW.setTargetPosition(-newLeftTarget);
            FRW.setTargetPosition(newRightTarget);
            BRW.setTargetPosition(newRightTarget);


            FLW.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            BLW.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            FRW.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            BRW.setMode(DcMotor.RunMode.RUN_TO_POSITION);


            runtime.reset();
            FLW.setPower(Math.abs(speed));
            BLW.setPower(Math.abs(speed));
            FRW.setPower(Math.abs(speed));
            BRW.setPower(Math.abs(speed));

            while (opModeIsActive() &&
                    (runtime.seconds() < timeoutS) &&
                    (FLW.isBusy() && FRW.isBusy()) &&
                    (FLW.isBusy() && FRW.isBusy())) {


                telemetry.addData("Path1", "Running to %7d :%7d", newLeftTarget, newRightTarget);
                telemetry.addData("Path2", "Running at %7d :%7d",
                        FLW.getCurrentPosition(),
                        BLW.getCurrentPosition(),
                        FRW.getCurrentPosition(),
                        BRW.getCurrentPosition());
                telemetry.update();

                //PID :3
                LLResult result = limelight.getLatestResult();
                if (result.isValid() || limelight.getLatestResult() == null) {

                    double turretPosFromTargetInTicks = result.getTx() * -7.35;
                    double pidPower = controller.calculate(turretPosFromTargetInTicks,0.0);

                    Turret.setPower(pidPower);

                }
            }


            FLW.setPower(0);
            BLW.setPower(0);
            FRW.setPower(0);
            BRW.setPower(0);


            FLW.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            BLW.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            FRW.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            BRW.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);


            FLW.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            BLW.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            FRW.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            BRW.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
    }

    public void encoderStrafe(double speed, double dist, double timeoutS) {
        int newLeftTarget;
        int newRightTarget;


        if (opModeIsActive()) {


            newLeftTarget = FLW.getCurrentPosition() + (int) (dist * COUNTS_PER_INCH);
            newRightTarget = FRW.getCurrentPosition() + (int) (dist * COUNTS_PER_INCH);
            FLW.setTargetPosition(-newLeftTarget);
            BLW.setTargetPosition(-newLeftTarget);
            FRW.setTargetPosition(newRightTarget);
            BRW.setTargetPosition(newRightTarget);


            FLW.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            BLW.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            FRW.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            BRW.setMode(DcMotor.RunMode.RUN_TO_POSITION);


            runtime.reset();
            FLW.setPower(-Math.abs(speed));
            BLW.setPower(-Math.abs(speed));
            FRW.setPower(Math.abs(speed));
            BRW.setPower(Math.abs(speed));


            while (opModeIsActive() &&
                    (runtime.seconds() < timeoutS) &&
                    (FLW.isBusy() && FRW.isBusy()) &&
                    (FLW.isBusy() && FRW.isBusy())) {

                // Display it for the driver.
                telemetry.addData("Path1", "Running to %7d :%7d", newLeftTarget, newRightTarget);
                telemetry.addData("Path2", "Running at %7d :%7d",
                        FLW.getCurrentPosition(),
                        BLW.getCurrentPosition(),
                        FRW.getCurrentPosition(),
                        BRW.getCurrentPosition());
                telemetry.update();
            }


            FLW.setPower(0);
            BLW.setPower(0);
            FRW.setPower(0);
            BRW.setPower(0);

            FLW.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            BLW.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            FRW.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            BRW.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);


            FLW.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            BLW.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            FRW.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            BRW.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
        lightlimeA3.stop();
    }
}

