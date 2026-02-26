package org.firstinspires.ftc.teamcode.util;

import com.pedropathing.geometry.Pose;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Subsystems.Intake;
import org.firstinspires.ftc.teamcode.Subsystems.drivetrain.Drivetrain;

public class Hardware {
    public static Drivetrain dt;

    public static Intake intake;
    public static void initialize(HardwareMap hwMap, AllianceColor alliance, Pose gamepadReference) {
        dt = new Drivetrain(hwMap, alliance,gamepadReference, new Pose(1.15, 1.15, 1.15));
        dt.follower.setStartingPose(
                alliance.isRed() ?
                        Settings.Positions.Drivetrain.Red.FAR_AUTO_START :
                        Settings.Positions.Drivetrain.Blue.FAR_AUTO_START
        );


        intake = new Intake(hwMap);
    }
}
