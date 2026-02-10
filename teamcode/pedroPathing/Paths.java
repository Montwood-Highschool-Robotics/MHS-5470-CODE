//package org.firstinspires.ftc.teamcode.pedroPathing;
//
//import com.pedropathing.follower.Follower;
//import com.pedropathing.follower.FollowerConstants;
//import com.pedropathing.ftc.FollowerBuilder;
//import com.pedropathing.ftc.drivetrains.MecanumConstants;
//import com.pedropathing.ftc.localization.constants.PinpointConstants;
//import com.pedropathing.geometry.BezierLine;
//import com.pedropathing.geometry.Pose;
//import com.pedropathing.paths.PathChain;
//import com.pedropathing.paths.PathConstraints;
//import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
//import com.qualcomm.robotcore.hardware.DcMotorSimple;
//import com.qualcomm.robotcore.hardware.HardwareMap;
//
//import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
//
//public class Paths {
//
//    public PathChain ToShootingrange;
//    public PathChain ToFirstlane;
//    public PathChain Walkingtothe1stball;
//    public PathChain Walkingtothe2ndball;
//    public PathChain Walkingto3rd;
//    public PathChain Shootingagain;
//    public PathChain toLane2;
//    public PathChain another1stball;
//    public PathChain another2ndball;
//    public PathChain Finalball;
//    public PathChain Readynoahandarmondo;
//
//    public Paths(Follower follower) {
//        class paths {
//
//
//            public paths(Follower follower) {
//
//                ToShootingrange = follower.pathBuilder().addPath(
//                                new BezierLine(
//                                        new Pose(123.222, 122.014),
//
//                                        new Pose(104.934, 103.716)
//                                )
//                        ).setLinearHeadingInterpolation(Math.toRadians(44.5), Math.toRadians(44.5))
//
//                        .build();
//
//                ToFirstlane = follower.pathBuilder().addPath(
//                                new BezierLine(
//                                        new Pose(104.934, 103.716),
//
//                                        new Pose(99.645, 83.572)
//                                )
//                        ).setLinearHeadingInterpolation(Math.toRadians(44.5), Math.toRadians(0))
//
//                        .build();
//
//                Walkingtothe1stball = follower.pathBuilder().addPath(
//                                new BezierLine(
//                                        new Pose(99.645, 83.572),
//
//                                        new Pose(109.382, 83.268)
//                                )
//                        ).setLinearHeadingInterpolation(Math.toRadians(0), Math.toRadians(0))
//
//                        .build();
//
//                Walkingtothe2ndball = follower.pathBuilder().addPath(
//                                new BezierLine(
//                                        new Pose(109.382, 83.268),
//
//                                        new Pose(116.632, 83.103)
//                                )
//                        ).setLinearHeadingInterpolation(Math.toRadians(0), Math.toRadians(0))
//
//                        .build();
//
//                Walkingto3rd = follower.pathBuilder().addPath(
//                                new BezierLine(
//                                        new Pose(116.632, 83.103),
//
//                                        new Pose(122.787, 82.787)
//                                )
//                        ).setLinearHeadingInterpolation(Math.toRadians(0), Math.toRadians(0))
//
//                        .build();
//
//                Shootingagain = follower.pathBuilder().addPath(
//                                new BezierLine(
//                                        new Pose(122.787, 82.787),
//
//                                        new Pose(105.018, 103.842)
//                                )
//                        ).setLinearHeadingInterpolation(Math.toRadians(0), Math.toRadians(44.5))
//
//                        .build();
//
//                toLane2 = follower.pathBuilder().addPath(
//                                new BezierLine(
//                                        new Pose(105.018, 103.842),
//
//                                        new Pose(97.703, 59.973)
//                                )
//                        ).setLinearHeadingInterpolation(Math.toRadians(44.5), Math.toRadians(0))
//
//                        .build();
//
//                another1stball = follower.pathBuilder().addPath(
//                                new BezierLine(
//                                        new Pose(97.703, 59.973),
//
//                                        new Pose(109.808, 60.160)
//                                )
//                        ).setLinearHeadingInterpolation(Math.toRadians(0), Math.toRadians(0))
//
//                        .build();
//
//                another2ndball = follower.pathBuilder().addPath(
//                                new BezierLine(
//                                        new Pose(109.808, 60.160),
//
//                                        new Pose(114.117, 60.066)
//                                )
//                        ).setLinearHeadingInterpolation(Math.toRadians(0), Math.toRadians(0))
//
//                        .build();
//
//                Finalball = follower.pathBuilder().addPath(
//                                new BezierLine(
//                                        new Pose(114.117, 60.066),
//
//                                        new Pose(119.705, 59.879)
//                                )
//                        ).setLinearHeadingInterpolation(Math.toRadians(0), Math.toRadians(0))
//
//                        .build();
//
//                Readynoahandarmondo = follower.pathBuilder().addPath(
//                                new BezierLine(
//                                        new Pose(119.705, 59.879),
//
//                                        new Pose(103.764, 60.014)
//                                )
//                        ).setLinearHeadingInterpolation(Math.toRadians(0), Math.toRadians(0))
//
//                        .build();
//            }
//        }
//    }
//}
