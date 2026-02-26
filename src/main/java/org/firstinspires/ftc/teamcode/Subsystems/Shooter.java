package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Shooter {
    private DcMotor Shooter1;
    private DcMotor Shooter2;

    public Shooter(HardwareMap hwMap) {
        Shooter1 = hwMap.get(DcMotorEx.class, "Shooter1");

        Shooter2 = hwMap.get(DcMotorEx.class, "Shooter2");

        Shooter1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        Shooter1.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        Shooter1.setDirection(DcMotorEx.Direction.FORWARD);

        Shooter2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        Shooter2.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        Shooter2.setDirection(DcMotorEx.Direction.REVERSE);

    }

    public void eject() {
        Shooter1.setPower(-0.1);
        Shooter2.setPower(-0.1);
    }


    public void stop() {
        Shooter1.setPower(0);
        Shooter2.setPower(0);

    }

    public void idle() {
        Shooter1.setPower(-0.1);
        Shooter2.setPower(-0.1);
    }

    public void midFieldShoot() {
        Shooter1.setPower(-0.8);
        Shooter2.setPower(-0.8);
    }

    public void farShoot() {
        Shooter1.setPower(-0.9);
        Shooter2.setPower(-0.9);
    }


}