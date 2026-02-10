package org.firstinspires.ftc.teamcode.Subsystems.shooter;

import com.pedropathing.math.MathFunctions;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.util.Settings;

public class Tilt {
    private Servo AngleFish;
    public Tilt(HardwareMap hwMap) {
        AngleFish = hwMap.get(Servo.class, Settings.HardwareNames.Shooter.TILT_SERVO);
    }

    public void setTilt(double tiltAngle) {
        tiltAngle = MathFunctions.clamp(tiltAngle, 0.05, 0.5);
        this.AngleFish.setPosition(tiltAngle);
    }

    public double auto(double distance) {
        return -0.0000121382*Math.pow(distance, 2) + 0.00269935*distance + 0.00144725;
    }
}
