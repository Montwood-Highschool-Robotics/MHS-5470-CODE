//package org.firstinspires.ftc.teamcode.Autos; // make sure this aligns with class location
//
//import com.pedropathing.follower.Follower;
//import com.pedropathing.geometry.BezierLine;
//import com.pedropathing.geometry.Pose;
//import com.pedropathing.paths.Path;
//import com.pedropathing.paths.PathChain;
//import com.pedropathing.util.Timer;
//import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
//import  com.qualcomm.robotcore.eventloop.opmode.OpMode;
//import com.qualcomm.robotcore.hardware.CRServo;
//import com.qualcomm.robotcore.hardware.DcMotor;
//import com.qualcomm.robotcore.hardware.Servo;
//
//import org.firstinspires.ftc.teamcode.pedroPathing.Constants;
//
//import java.util.concurrent.TimeoutException;
//
//@Autonomous(name = "redAUTOCLOSE_TEST", group = "Autos")
//public class RED_OTTOCLOSE extends OpMode {
//    private DcMotor Turret;
//    private DcMotor Shooter1;
//    private DcMotor Shooter2;
//    private DcMotor Intake;
//
//    private CRServo LTopLift;
//    private CRServo LBotLift;
//
//    private CRServo AngleFish;
//    private CRServo RBotLift;
//
//    private CRServo RTopLift;
//
//    private Servo OuttakeServo;
//
//
//    private CRServo Spin;
//    private Follower follower;
//    private Timer pathTimer, actionTimer, opmodeTimer;
//
//    private int pathState;
//
//    private final Pose startPose = new Pose(122.18077803203661, 123.80091533180779, Math.toRadians(43)); // Start Pose of our robot.
//    private final Pose scorePose = new Pose(107.40503432494279, 108.83295194508005, Math.toRadians(55)); // Scoring Pose of our robot. It is facing the goal at a 55 degree angle.
//    private final Pose pickup1Pose = new Pose(125.19908466819223, 83.62471395881003, Math.toRadians(0)); // Highest (First Set) of Artifacts from the Spike Mark.
//    private final Pose pickup2Pose = new Pose(125.19908466819223, 59.40503432494277, Math.toRadians(0)); // Middle (Second Set) of Artifacts from the Spike Mark.
//    private final Pose pickup3Pose = new Pose(125.19908466819223, 59.40503432494277, Math.toRadians(0)); // Lowest (Third Set) of Artifacts from the Spike Mark.
//
//
//    private Path scorePreload;
//    private PathChain grabPickup1, scorePickup1, grabPickup2, scorePickup2, grabPickup3, scorePickup3;
//
//    public void buildPaths() {
//
//        DcMotor Shooter1 = hardwareMap.dcMotor.get("Shooter1");
//        DcMotor Shooter2 = hardwareMap.dcMotor.get("Shooter2");
//        DcMotor Intake = hardwareMap.dcMotor.get("Intake");
//        DcMotor Turret = hardwareMap.dcMotor.get("Turret");
//
//        CRServo LTopLift = hardwareMap.crservo.get("LTopLift");
//        CRServo LBotLift = hardwareMap.crservo.get("LBotLift");
//        CRServo AngleFish = hardwareMap.crservo.get("AngleFish");
//        CRServo RBotLift = hardwareMap.crservo.get("RBotLift");
//        CRServo RTopLift = hardwareMap.crservo.get("RTopLift");
//        Servo OuttakeServo = hardwareMap.servo.get("OuttakeServo");
//        CRServo Spin = hardwareMap.crservo.get("Spin");
//
//        /* This is our scorePreload path. We are using a BezierLine, which is a straight line. */
//        scorePreload = new Path(new BezierLine(startPose, scorePose));
//        scorePreload.setLinearHeadingInterpolation(startPose.getHeading(), scorePose.getHeading());
//
//    /* Here is an example for Constant Interpolation
//    scorePreload.setConstantInterpolation(startPose.getHeading()); */
//
//        /* This is our grabPickup1 PathChain. We are using a single path with a BezierLine, which is a straight line. */
//        grabPickup1 = follower.pathBuilder()
//                .addPath(new BezierLine(scorePose, pickup1Pose))
//                .setLinearHeadingInterpolation(scorePose.getHeading(), pickup1Pose.getHeading())
//
//                .build();
//
//        /* This is our scorePickup1 PathChain. We are using a single path with a BezierLine, which is a straight line. */
//        scorePickup1 = follower.pathBuilder()
//                .addPath(new BezierLine(pickup1Pose, scorePose))
//                .setLinearHeadingInterpolation(pickup1Pose.getHeading(), scorePose.getHeading())
//                .build();
//
//        /* This is our grabPickup2 PathChain. We are using a single path with a BezierLine, which is a straight line. */
//        grabPickup2 = follower.pathBuilder()
//                .addPath(new BezierLine(scorePose, pickup2Pose))
//                .setLinearHeadingInterpolation(scorePose.getHeading(), pickup2Pose.getHeading())
//                .build();
//
//        /* This is our scorePickup2 PathChain. We are using a single path with a BezierLine, which is a straight line. */
//        scorePickup2 = follower.pathBuilder()
//                .addPath(new BezierLine(pickup2Pose, scorePose))
//                .setLinearHeadingInterpolation(pickup2Pose.getHeading(), scorePose.getHeading())
//                .build();
//
//        /* This is our grabPickup3 PathChain. We are using a single path with a BezierLine, which is a straight line. */
//        grabPickup3 = follower.pathBuilder()
//                .addPath(new BezierLine(scorePose, pickup3Pose))
//                .setLinearHeadingInterpolation(scorePose.getHeading(), pickup3Pose.getHeading())
//                .build();
//
//        /* This is our scorePickup3 PathChain. We are using a single path with a BezierLine, which is a straight line. */
//        scorePickup3 = follower.pathBuilder()
//                .addPath(new BezierLine(pickup3Pose, scorePose))
//                .setLinearHeadingInterpolation(pickup3Pose.getHeading(), scorePose.getHeading())
//                .build();
//    }
//
//    public void autonomousPathUpdate() {
//        switch (pathState) {
//            case 0:
//                follower.followPath(scorePreload);
//                setPathState(1);
//                break;
//            case 1:
//
//            /* You could check for
//            - Follower State: "if(!follower.isBusy()) {}"
//            - Time: "if(pathTimer.getElapsedTimeSeconds() > 1) {}"
//            - Robot Position: "if(follower.getPose().getX() > 36) {}"
//            */
//
//                /* This case checks the robot's position and will wait until the robot position is close (1 inch away) from the scorePose's position */
//                if(!follower.isBusy()) {
//                    /* Score Preload */
//                    /* Since this is a pathChain, we can have Pedro hold the end point while we are grabbing the sample */
//                    follower.followPath(grabPickup1,true);
//                    setPathState(2);
//                }
//                break;
//            case 2:
//                /* This case checks the robot's position and will wait until the robot position is close (1 inch away) from the pickup1Pose's position */
//                if(!follower.isBusy()) {
//                    /* Grab Sample */
//
//                    /* Since this is a pathChain, we can have Pedro hold the end point while we are scoring the sample */
//                    follower.followPath(scorePickup1,true);
//                    setPathState(3);
//                }
//                break;
//            case 3:
//                /* This case checks the robot's position and will wait until the robot position is close (1 inch away) from the scorePose's position */
//                if(!follower.isBusy()) {
//                    /* Score Sample */
//
//                    /* Since this is a pathChain, we can have Pedro hold the end point while we are grabbing the sample */
//                    follower.followPath(grabPickup2,true);
//                    setPathState(4);
//                }
//                break;
//            case 4:
//                /* This case checks the robot's position and will wait until the robot position is close (1 inch away) from the pickup2Pose's position */
//                if(!follower.isBusy()) {
//                    /* Grab Sample */
//
//                    /* Since this is a pathChain, we can have Pedro hold the end point while we are scoring the sample */
//                    follower.followPath(scorePickup2,true);
//                    setPathState(5);
//                }
//                break;
//            case 5:
//                /* This case checks the robot's position and will wait until the robot position is close (1 inch away) from the scorePose's position */
//                if(!follower.isBusy()) {
//                    /* Score Sample */
//
//                    /* Since this is a pathChain, we can have Pedro hold the end point while we are grabbing the sample */
//                    follower.followPath(grabPickup3,true);
//                    setPathState(6);
//                }
//                break;
//            case 6:
//                /* This case checks the robot's position and will wait until the robot position is close (1 inch away) from the pickup3Pose's position */
//                if(!follower.isBusy()) {
//                    /* Grab Sample */
//
////                    /* Since this is a pathChain, we can have Pedro hold the end point while we are scoring the sample */
////                    follower.followPath(scorePickup3, true);
//                    setPathState(7);
//                }
//                break;
//            case 7:
//                /* This case checks the robot's position and will wait until the robot position is close (1 inch away) from the scorePose's position */
//                if(!follower.isBusy()) {
//                    /* Set the state to a Case we won't use or define, so it just stops running an new paths */
//                    setPathState(-1);
//                }
//                break;
//        }
//    }
//
//    /** These change the states of the paths and actions. It will also reset the timers of the individual switches **/
//    public void setPathState(int pState) {
//        pathState = pState;
//        pathTimer.resetTimer();
//    }
//
//    /** This is the main loop of the OpMode, it will run repeatedly after clicking "Play". **/
//    @Override
//    public void loop() {
//
//        // These loop the movements of the robot, these must be called continuously in order to work
//        follower.update();
//        autonomousPathUpdate();
//
//        // Feedback to Driver Hub for debugging
//        telemetry.addData("path state", pathState);
//        telemetry.addData("x", follower.getPose().getX());
//        telemetry.addData("y", follower.getPose().getY());
//        telemetry.addData("heading", follower.getPose().getHeading());
//        telemetry.update();
//    }
//
//    /** This method is called once at the init of the OpMode. **/
//    @Override
//    public void init() {
//        pathTimer = new Timer();
//        opmodeTimer = new Timer();
//        opmodeTimer.resetTimer();
//
//
//        follower = Constants.createFollower(hardwareMap);
//        buildPaths();
//        follower.setStartingPose(startPose);
//
//    }
//
//    /** This method is called continuously after Init while waiting for "play". **/
//    @Override
//    public void init_loop() {}
//
//    /** This method is called once at the start of the OpMode.
//     * It runs all the setup actions, including building paths and starting the path system **/
//    @Override
//    public void start() {
//        opmodeTimer.resetTimer();
//        setPathState(0);
//    }
//
//    /** We do not use this because everything should automatically disable **/
//    @Override
//    public void stop() {}
//
//
//}
//
package org.firstinspires.ftc.teamcode.Autos;

import android.view.View;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.NormalizedColorSensor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import com.qualcomm.hardware.limelightvision.Limelight3A;

@Autonomous
public class REDOTTOFAR extends LinearOpMode {
    static final double COUNTS_PER_MOTOR_REV = 383.6;
    static final double DRIVE_GEAR_REDUCTION = 1.0;
    static final double WHEEL_DIAMETER_INCHES = 3.937;
    static final double COUNTS_PER_INCH = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) / (WHEEL_DIAMETER_INCHES * 3.1415);

    private ElapsedTime runtime = new ElapsedTime();

    private DcMotor FLW;
    private DcMotor FRW;
    private DcMotor BLW;
    private DcMotor BRW;

    private DcMotor Turret;
    private DcMotor Shooter1;
    private DcMotor Shooter2;
    private DcMotor Intake;

    private CRServo LTopLift;
    private CRServo LBotLift;

    private CRServo AngleFish;
    private CRServo RBotLift;

    private CRServo RTopLift;

    private Servo OuttakeServo;

    private CRServo Spin;

    private Limelight3A lightlimeA3;
    NormalizedColorSensor colorSensor;
    View relativeLayout;
    static final int STREAM_WITH = 1920;
    static final double STREAM_HEIGHT = 1080;
    Limelight3A limelight;
    Double Time;
    double var = 0;
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

        CRServo LTopLift = hardwareMap.crservo.get("LTopLift");
        CRServo LBotLift = hardwareMap.crservo.get("LBotLift");
        CRServo AngleFish = hardwareMap.crservo.get("AngleFish");
        CRServo RBotLift = hardwareMap.crservo.get("RBotLift");
        CRServo RTopLift = hardwareMap.crservo.get("RTopLift");
        Servo OuttakeServo = hardwareMap.servo.get("OuttakeServo");
        CRServo Spin = hardwareMap.crservo.get("Spin");







        FLW.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        BLW.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        FRW.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        BRW.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);










        waitForStart();
        Shooter1.setPower(-.7);
        Shooter1.setPower(-.7);
        sleep(2000);
        if (var == 0) {
            //Shoot ball 1
            OuttakeServo.setPosition(1);
            sleep(500);
            OuttakeServo.setPosition(0);
            sleep(500);
//            //Spindexer Ball 2
            OuttakeServo.setPosition(1);
            sleep(1000);
            Spin.setPower(-1);
            sleep(675);
//            // Sh00t Ball 2
            Spin.setPower(0);
            sleep(100);
            OuttakeServo.setPosition(1);
            sleep(1000);
            OuttakeServo.setPosition(0);
            sleep(1000);
            //Spindexer Ball 3
            OuttakeServo.setPosition(1);
            sleep(1000);
            Spin.setPower(-1);
            sleep(200); // changing
//            // Sh00t Ball 3
            Spin.setPower(0);
            sleep(100);
            OuttakeServo.setPosition(1);
            sleep(1000);
            OuttakeServo.setPosition(0);
            sleep(1000);


            driveForward(.25, 40, 10);
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

