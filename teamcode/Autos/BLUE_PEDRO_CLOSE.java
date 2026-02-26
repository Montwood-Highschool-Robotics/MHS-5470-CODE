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
//import org.firstinspires.ftc.teamcode.Subsystems.Intake;
//import org.firstinspires.ftc.teamcode.Subsystems.Shooter;
//import org.firstinspires.ftc.teamcode.Subsystems.drivetrain.Spindex;
//import org.firstinspires.ftc.teamcode.pedroPathing.Constants;
//
//@Autonomous(name = "BLUE_PEDRO_CLOSE", group = "Autos")
//public class BLUE_PEDRO_CLOSE extends OpMode {
//    private Intake intake;
//    private Spindex spin;
//    private Shooter shooter;
//    private Follower follower;
//    private Timer pathTimer, actionTimer, opmodeTimer;
//
//    private int pathState;
//    public PathChain Path1;
//    public PathChain Path2;
//    public PathChain Path3;
//    public PathChain Path4;
//    public PathChain Path5;
//    public PathChain Path6;
//    public PathChain Path7;
//    public PathChain Path8;
//    public PathChain Path9;
//    public PathChain Path10;
//    public PathChain Path11;
//
//    private final Pose startPose = new Pose(21.40045766590389, 123.16704805491987, Math.toRadians(145));
//    private final Pose scorePose = new Pose(36.558352402745996, 107.8443935926773, Math.toRadians(135)); // Scoring Pose of our robot. It is facing the goal at a 135 degree angle.
//    private final Pose pickup1Pose = new Pose(20.741418764302058, 83.78947368421049, Math.toRadians(180)); // Highest (First Set) of Artifacts from the Spike Mark.
//    private final Pose pickup2Pose = new Pose(18.76430205949657, 59.73455377574366, Math.toRadians(180)); // Middle (Second Set) of Artifacts from the Spike Mark.
//    private final Pose pickup3Pose = new Pose(18.929061784897026, 35.35011441647594, Math.toRadians(180)); // Lowest (Third Set) of Artifacts from the Spike Mark.
//
//
//    private Path scorePreload;
//    private PathChain grabPickup1, scorePickup1, grabPickup2, scorePickup2, grabPickup3, scorePickup3;
//
//    public void buildPaths() {
//        Path11 = follower.pathBuilder().addPath(
//                        new BezierLine(
//                                new Pose(23.213, 122.178),
//
//                                new Pose(23.213, 122.178)
//                        )
//                ).setLinearHeadingInterpolation(Math.toRadians(120), Math.toRadians(120))
//
//                .build();
//
//        Path1 = follower.pathBuilder().addPath(
//                        new BezierLine(
//                                new Pose(23.213, 122.178),
//
//                                new Pose(44.137, 83.121)
//                        )
//                ).setLinearHeadingInterpolation(Math.toRadians(120), Math.toRadians(180))
//
//                .build();
//
//        Path2 = follower.pathBuilder().addPath(
//                        new BezierLine(
//                                new Pose(44.137, 83.121),
//
//                                new Pose(34.709, 83.172)
//                        )
//                ).setLinearHeadingInterpolation(Math.toRadians(180), Math.toRadians(180))
//
//                .build();
//
//        Path3 = follower.pathBuilder().addPath(
//                        new BezierLine(
//                                new Pose(34.709, 83.172),
//
//                                new Pose(28.998, 83.368)
//                        )
//                ).setLinearHeadingInterpolation(Math.toRadians(180), Math.toRadians(180))
//
//                .build();
//
//        Path4 = follower.pathBuilder().addPath(
//                        new BezierLine(
//                                new Pose(28.998, 83.368),
//
//                                new Pose(24.059, 82.966)
//                        )
//                ).setLinearHeadingInterpolation(Math.toRadians(180), Math.toRadians(180))
//
//                .build();
//
//        Path5 = follower.pathBuilder().addPath(
//                        new BezierLine(
//                                new Pose(24.059, 82.966),
//
//                                new Pose(23.465, 122.222)
//                        )
//                ).setLinearHeadingInterpolation(Math.toRadians(180), Math.toRadians(138))
//
//                .build();
//
//        Path6 = follower.pathBuilder().addPath(
//                        new BezierLine(
//                                new Pose(23.465, 122.222),
//
//                                new Pose(41.666, 59.336)
//                        )
//                ).setLinearHeadingInterpolation(Math.toRadians(138), Math.toRadians(180))
//
//                .build();
//
//        Path7 = follower.pathBuilder().addPath(
//                        new BezierLine(
//                                new Pose(41.666, 59.336),
//
//                                new Pose(35.071, 59.108)
//                        )
//                ).setLinearHeadingInterpolation(Math.toRadians(180), Math.toRadians(180))
//
//                .build();
//
//        Path8 = follower.pathBuilder().addPath(
//                        new BezierLine(
//                                new Pose(35.071, 59.108),
//
//                                new Pose(28.986, 59.641)
//                        )
//                ).setLinearHeadingInterpolation(Math.toRadians(180), Math.toRadians(180))
//
//                .build();
//
//        Path9 = follower.pathBuilder().addPath(
//                        new BezierLine(
//                                new Pose(28.986, 59.641),
//
//                                new Pose(23.789, 58.952)
//                        )
//                ).setLinearHeadingInterpolation(Math.toRadians(180), Math.toRadians(180))
//
//                .build();
//
//        Path10 = follower.pathBuilder().addPath(
//                        new BezierLine(
//                                new Pose(23.789, 58.952),
//
//                                new Pose(23.375, 122.002)
//                        )
//                ).setLinearHeadingInterpolation(Math.toRadians(180), Math.toRadians(138))
//
//                .build();
//    }
//
//
//    public void autonomousPathUpdate() {
//
//        switch (pathState) {
//            case 0:
//                //Time More than 3 Less than 3.5
//                if (pathTimer.getElapsedTimeSeconds() > 2 && pathTimer.getElapsedTimeSeconds() < 3) {
//                    /* Score Preload */
//                    /* Since this is a pathChain, we can have Pedro hold the end point while we are grabbing the sample */
//                    follower.setPose(startPose);
//                    shooter.midFieldShoot();
//                    intake.custom(0);
//                    spin.CCSpin();
//                    setPathState(1);
//                }
//                break;
//
//
//            case 1:
//                //Time More than 3 Less than 3.5
//                if (pathTimer.getElapsedTimeSeconds() > 3 && pathTimer.getElapsedTimeSeconds() < 3.5) {
//                    /* Score Preload */
//                    /* Since this is a pathChain, we can have Pedro hold the end point while we are grabbing the sample */
//                    follower.followPath(Path2, 0.6, true);
//                    shooter.idle();
//                    intake.custom(1);
//                    spin.SpinStop();
//                    setPathState(2);
//                }
//                break;
//
//
////            case 2:
////                /* This case checks the robot's position and will wait until the robot position is close (1 inch away) from the pickup1Pose's position */
////                if(!follower.isBusy()) {
////                    /* Grab Sample */
////
////                    /* Since this is a pathChain, we can have Pedro hold the end point while we are scoring the sample */
////                    follower.followPath(Path3,true);
////                    setPathState(3);
////                }
////                break;
////            case 3:
////                /* This case checks the robot's position and will wait until the robot position is close (1 inch away) from the scorePose's position */
////                if(!follower.isBusy()) {
////                    /* Score Sample */
////
////                    /* Since this is a pathChain, we can have Pedro hold the end point while we are grabbing the sample */
////                    follower.followPath(Path4,true);
////                    setPathState(4);
////                }
////                break;
////            case 4:
////                /* This case checks the robot's position and will wait until the robot position is close (1 inch away) from the pickup2Pose's position */
////                if(!follower.isBusy()) {
////                    /* Grab Sample */
////
////                    /* Since this is a pathChain, we can have Pedro hold the end point while we are scoring the sample */
////                    follower.followPath(Path5,true);
////                    setPathState(5);
////                }
////                break;
////            case 5:
////                /* This case checks the robot's position and will wait until the robot position is close (1 inch away) from the scorePose's position */
////                if(!follower.isBusy()) {
////                    /* Score Sample */
////
////                    /* Since this is a pathChain, we can have Pedro hold the end point while we are grabbing the sample */
////                    follower.followPath(Path6,true);
////                    setPathState(6);
////                }
////                break;
////            case 6:
////                /* This case checks the robot's position and will wait until the robot position is close (1 inch away) from the pickup3Pose's position */
////                if(!follower.isBusy()) {
////                    /* Grab Sample */
////
////                    /* Since this is a pathChain, we can have Pedro hold the end point while we are scoring the sample */
////                    follower.followPath(Path7, true);
////                    setPathState(7);
////                }
////                break;
////            case 7:
////                /* This case checks the robot's position and will wait until the robot position is close (1 inch away) from the pickup3Pose's position */
////                if(!follower.isBusy()) {
////                    /* Grab Sample */
////
////                    /* Since this is a pathChain, we can have Pedro hold the end point while we are scoring the sample */
////                    follower.followPath(Path8, true);
////                    setPathState(8);
////                }
////                break;
////            case 8:
////                /* This case checks the robot's position and will wait until the robot position is close (1 inch away) from the pickup3Pose's position */
////                if(!follower.isBusy()) {
////                    /* Grab Sample */
////
////                    /* Since this is a pathChain, we can have Pedro hold the end point while we are scoring the sample */
////                    follower.followPath(Path9, true);
////                    setPathState(9);
////                }
////                break;
////            case 9:
////                /* This case checks the robot's position and will wait until the robot position is close (1 inch away) from the pickup3Pose's position */
////                if(!follower.isBusy()) {
////                    /* Grab Sample */
////
////                    /* Since this is a pathChain, we can have Pedro hold the end point while we are scoring the sample */
////                    follower.followPath(Path10, true);
////                    setPathState(10);
////                }
////                break;
////            case 10:
////                /* This case checks the robot's position and will wait until the robot position is close (1 inch away) from the pickup3Pose's position */
////                if(!follower.isBusy()) {
////                    /* Grab Sample */
////
////                    /* Since this is a pathChain, we can have Pedro hold the end point while we are scoring the sample */
////                    follower.followPath(Path11, true);
////                    setPathState(11);
////                }
////                break;
//            case 2:
//                /* This case checks the robot's position and will wait until the robot position is close (1 inch away) from the scorePose's position */
//                if (!follower.isBusy()) {
//                    /* Set the state to a Case we won't use or define, so it just stops running an new paths */
//                    setPathState(-1);
//                }
//                break;
//
//        }
//    }
//
//    /**
//     * These change the states of the paths and actions. It will also reset the timers of the individual switches
//     **/
//    public void setPathState(int pState) {
//        pathState = pState;
//        pathTimer.resetTimer();
//    }
//
//    /**
//     * This is the main loop of the OpMode, it will run repeatedly after clicking "Play".
//     **/
//    @Override
//    public void loop() {
//
//        // These loop the movements of the robot, these must be called continuously in order to work
//        autonomousPathUpdate();
//
//        // Feedback to Driver Hub for debugging
//        telemetry.addData("path state", pathState);
//        telemetry.addData("x", follower.getPose().getX());
//        telemetry.addData("y", follower.getPose().getY());
//        telemetry.addData("heading", follower.getPose().getHeading());
//        telemetry.update();
//
//
//        follower.update();
//    }
//
//    /**
//     * This method is called once at the init of the OpMode.
//     **/
//    @Override
//    public void init() {
//        pathTimer = new Timer();
//        opmodeTimer = new Timer();
//        opmodeTimer.resetTimer();
//        intake = new Intake(hardwareMap);
//        shooter = new Shooter(hardwareMap);
//        spin = new Spindex(hardwareMap);
//
//
//        follower = Constants.createFollower(hardwareMap);
//        buildPaths();
//        follower.setStartingPose(startPose);
//
//    }
//
//    /**
//     * This method is called continuously after Init while waiting for "play".
//     **/
//    @Override
//    public void init_loop() {
//    }
//
//    /**
//     * This method is called once at the start of the OpMode.
//     * It runs all the setup actions, including building paths and starting the path system
//     **/
//    @Override
//    public void start() {
//        opmodeTimer.resetTimer();
//        setPathState(0);
//    }
//
//    /**
//     * We do not use this because everything should automatically disable
//     **/
//    @Override
//    public void stop() {
//    }
//
//}
//
//
//
