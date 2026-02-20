package org.firstinspires.ftc.teamcode.Subsystems;

//import com.pedropathing.math.MathFunctions;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.util.Settings;

public class Tilt {
    private CRServo AngleFish;
    public Tilt(HardwareMap hwMap) {
        AngleFish = hwMap.get(CRServo.class, Settings.HardwareNames.Shooter.TILT_SERVO);
    }

    public void AngleUp() {
        AngleFish.setPower(-0.5);
    }


    public void AngleDown() {
        AngleFish.setPower(0.5);

    }
    public void AngleStop() {
        AngleFish.setPower(0);

    }
}
