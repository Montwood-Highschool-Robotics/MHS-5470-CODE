//package org.firstinspires.ftc.teamcode.Autos;
//
//import com.pedropathing.follower.Follower;
//import com.pedropathing.geometry.BezierLine;
//import com.pedropathing.geometry.Pose;
//import com.pedropathing.paths.Path;
//import com.pedropathing.paths.PathChain;
//import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
//import com.qualcomm.robotcore.eventloop.opmode.OpMode;
//
//import org.firstinspires.ftc.teamcode.Subsystems.Shooter;
//import org.firstinspires.ftc.teamcode.Subsystems.drivetrain.Spindex;
//import org.firstinspires.ftc.teamcode.pedroPathing.Constants;
//import org.firstinspires.ftc.teamcode.Subsystems.Intake;
//import org.firstinspires.ftc.teamcode.Subsystems.drivetrain.autonomous.Actions;
//import org.firstinspires.ftc.teamcode.util.AllianceColor;
//
//@Autonomous(name="Preload Park")
//public class PreloadPark extends OpMode {
//    public static final Actions paths = new Actions(AllianceColor.Selection.BLUE);
//    private Follower follower;
//    private boolean hasShotFirst, shoot;
//
//    private Intake intake;
//    private Spindex spin;
//    private Shooter shooter;
//    private double savedTime;
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
//    private final Pose startPose = new Pose(21.40045766590389, 123.16704805491987, Math.toRadians(145));
//
//
//
//    @Override
//    public void init() {
//        follower = Constants.createFollower(hardwareMap);
//        follower.setStartingPose(Constants.blueStartPose);
//        hasShotFirst = false;
//        shoot= false;
//
//        intake = new Intake(hardwareMap);
//        spin = new Spindex(hardwareMap);
//        shooter = new Shooter(hardwareMap);
//        //time = new ElapsedTime();
//
//
//        savedTime = 0.0;
//    }
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
//    @Override
//    public void start() {
//        resetRuntime();
//
//
//    }
//
//    @Override
//    public void loop() {
//        follower.update();
//
//        if (time > 3 && time < 3.5) {
//            shooter.midFieldShoot();
//            intake.custom(0);
//            spin.CCSpin();
//            follower.setPose(startPose);
//        } else if (time > 3.5 && time < 4.5) {
//            shooter.midFieldShoot();
//            intake.stop();
//            spin.Spin();
//            follower.followPath(Path1);
//        } else if (time > 4.5 && time < 5) {
//            shooter.idle();
//            intake.custom(0.85);
//            spin.SpinStop();
//            follower.followPath(Path2);
//        } else if (time > 5 && time < 6) {
//            shooter.midFieldShoot();
//            intake.stop();
//            spin.Spin();
//            follower.followPath(Path3);
//        } else if (time > 6 && time < 6.5) {
//            shooter.idle();
//            intake.custom(0.85);
//            spin.SpinStop();
//            follower.followPath(Path4);
//        } else if (time > 6.5 && time < 7.5) {
//            shooter.midFieldShoot();
//            intake.stop();
//            spin.Spin();
//            follower.followPath(Path5);
//        } else if (time > 7.5 && time < 7.65) {
//            intake.run();
//            shooter.idle();
//            spin.SpinStop();
//            follower.followPath(Path7);
//        }
//    }
//}
