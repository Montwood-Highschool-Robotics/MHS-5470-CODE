//package org.firstinspires.ftc.teamcode.Autos; // make sure this aligns with class location
//
//import com.pedropathing.follower.Follower;
//import com.pedropathing.geometry.BezierLine;
//import com.pedropathing.geometry.Pose;
//import com.pedropathing.paths.Path;
//import com.pedropathing.paths.PathChain;
//import com.pedropathing.util.Timer;
//import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
//import com.qualcomm.robotcore.eventloop.opmode.OpMode;
//
//import org.firstinspires.ftc.teamcode.Subsystems.Intake;
//import org.firstinspires.ftc.teamcode.Subsystems.Shooter;
//import org.firstinspires.ftc.teamcode.Subsystems.drivetrain.Spindex;
//import org.firstinspires.ftc.teamcode.pedroPathing.Constants;
//
//
//@Autonomous(name = "BLUE_PEDRO_FAR", group = "Autos")
//public class BLUE_PEDRO_FAR extends OpMode {
//    private Intake intake;
//    private Spindex spin;
//    private Shooter shooter;
//    private Follower follower;
//    private Timer pathTimer, actionTimer, opmodeTimer;
//
//    private int pathState;
//    public PathChain PATHSTART;
//    public PathChain PATHTOLINE;
//    public PathChain PATHTOBALL1;
//    public PathChain PATHTOBALL2;
//    public PathChain PATHTOBALL3;
//    public PathChain PATHTOSHOOT;
//    public PathChain LEAVE;
//
//
//    private final Pose startPose = new Pose(63.744, 10.801, Math.toRadians(90));
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
//        PATHSTART = follower.pathBuilder().addPath(
//                        new BezierLine(
//                                new Pose(61.602, 7.341),
//
//                                new Pose(59.954, 17.382)
//                        )
//                ).setLinearHeadingInterpolation(Math.toRadians(90), Math.toRadians(120))
//
//                .build();
//
//        PATHTOLINE = follower.pathBuilder().addPath(
//                        new BezierLine(
//                                new Pose(59.954, 17.382),
//
//                                new Pose(35.005, 46.166)
//                        )
//                ).setLinearHeadingInterpolation(Math.toRadians(120), Math.toRadians(180))
//
//                .build();
//
//        PATHTOBALL1 = follower.pathBuilder().addPath(
//                        new BezierLine(
//                                new Pose(35.005, 46.166),
//// Past these ones I changed
//                                new Pose(35.826, 46.16)
//                        )
//                ).setLinearHeadingInterpolation(Math.toRadians(180), Math.toRadians(180))
//
//                .build();
//
//        PATHTOBALL2 = follower.pathBuilder().addPath(
//                        new BezierLine(
//                                new Pose(34.991, 36.002),
//
//                                new Pose(29.515, 45.952)
//                        )
//                ).setLinearHeadingInterpolation(Math.toRadians(180), Math.toRadians(180))
//
//                .build();
//
//        PATHTOBALL3 = follower.pathBuilder().addPath(
//                        new BezierLine(
//                                new Pose(29.515, 45.952),
//
//                                new Pose(23.295, 45.826)
//                        )
//                ).setLinearHeadingInterpolation(Math.toRadians(180), Math.toRadians(180))
//
//                .build();
//
//        PATHTOSHOOT = follower.pathBuilder().addPath(
//                        new BezierLine(
//                                new Pose(23.295, 45.826),
//
//                                new Pose(60.014, 17.368)
//                        )
//                ).setLinearHeadingInterpolation(Math.toRadians(180), Math.toRadians(120))
//
//                .build();
//
//        LEAVE = follower.pathBuilder().addPath(
//                        new BezierLine(
//                                new Pose(60.014, 17.368),
//
//                                new Pose(61.346, 44.380)
//                        )
//                ).setLinearHeadingInterpolation(Math.toRadians(120), Math.toRadians(90))
//
//                .build();
//    }
//
//    public void autonomousPathUpdate() {
//
//        switch (pathState) {
//            case 0:
//                //Time More than 3 Less than 3.5
//                if (pathTimer.getElapsedTimeSeconds() > 2 && pathTimer.getElapsedTimeSeconds() < 3) {
//                    /* Score Preload */
//                    /* Since this is a pathChain, we can have Pedro hold the end point while we are grabbing the sample */
//                    follower.followPath(PATHSTART, true);
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
//                if (pathTimer.getElapsedTimeSeconds() > 3 && pathTimer.getElapsedTimeSeconds() < 4) {
//                    /* Score Preload */
//                    /* Since this is a pathChain, we can have Pedro hold the end point while we are grabbing the sample */
//                    follower.followPath(PATHTOLINE, true);
//                    shooter.idle();
//                    spin.SpinStop();
//                    setPathState(2);
//                }
//                break;
//
//
//            case 2:
//                /* This case checks the robot's position and will wait until the robot position is close (1 inch away) from the pickup1Pose's position */
//                if(pathTimer.getElapsedTimeSeconds() > 4 && pathTimer.getElapsedTimeSeconds() < 4.5) {
//                    /* Grab Sample */
//                    intake.custom(1);
//                    spin.CCSpin();
//                    /* Since this is a pathChain, we can have Pedro hold the end point while we are scoring the sample */
//                    follower.followPath(PATHTOBALL1,true);
//                    setPathState(3);
//                }
//                break;
//
//            case 3:
//                /* This case checks the robot's position and will wait until the robot position is close (1 inch away) from the scorePose's position */
//                if(pathTimer.getElapsedTimeSeconds() > 5 && pathTimer.getElapsedTimeSeconds() < 5.5) {
//                    /* Score Sample */
//
//                    /* Since this is a pathChain, we can have Pedro hold the end point while we are grabbing the sample */
//                    follower.followPath(PATHTOBALL2,0.6,true);
//                    setPathState(4);
//                }
//                break;
//            case 4:
//                /* This case checks the robot's position and will wait until the robot position is close (1 inch away) from the pickup2Pose's position */
//                if(pathTimer.getElapsedTimeSeconds() > 6 && pathTimer.getElapsedTimeSeconds() < 6.5) {
//                    /* Grab Sample */
//
//                    /* Since this is a pathChain, we can have Pedro hold the end point while we are scoring the sample */
//                    follower.followPath(PATHTOBALL3,true);
//                    setPathState(5);
//                }
//                break;
//
//
//            case 5:
//                /* This case checks the robot's position and will wait until the robot position is close (1 inch away) from the scorePose's position */
//                if(pathTimer.getElapsedTimeSeconds() > 7 && pathTimer.getElapsedTimeSeconds() < 7.5) {
//                    /* Score Sample */
//
//                    /* Since this is a pathChain, we can have Pedro hold the end point while we are grabbing the sample */
//                    follower.followPath(PATHTOSHOOT,true);
//                    setPathState(6);
//                }
//                break;
//            case 6:
//                /* This case checks the robot's position and will wait until the robot position is close (1 inch away) from the scorePose's position */
//                if(pathTimer.getElapsedTimeSeconds() > 8 && pathTimer.getElapsedTimeSeconds() < 8.5) {
//                    /* Score Sample */
//
//                    /* Since this is a pathChain, we can have Pedro hold the end point while we are grabbing the sample */
//                    follower.followPath(LEAVE,true);
//                    setPathState(7);
//                }
//                break;
//            case 7:
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
//}
//
//
//
//
//
//
