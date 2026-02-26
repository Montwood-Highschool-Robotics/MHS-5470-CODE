package org.firstinspires.ftc.teamcode.Subsystems.drivetrain;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.util.Settings.HardwareNames;
import org.firstinspires.ftc.teamcode.util.Settings.Positions;


public class Spindex {
    public CRServo Spin;
    private boolean isRunning = false;


    /**
     * Initializes the Intake of the robot
     * @param hardwareMap   An OpMode HardwareMap object
     */
    public Spindex (HardwareMap hardwareMap) {
        Spin = hardwareMap.get(CRServo.class, HardwareNames.Spindex.Spin);
    }

    /**
     * Run the intake
     */
    public void Spin() {
        Spin.setPower(1);
        isRunning = true;
    }

    /**
     * Eject an artifact in the robot
     */
    public void CCSpin() {
        Spin.setPower(-1);
        isRunning = true;
    }

    public void SpinStop() {
        Spin.setPower(0);
        isRunning = false;
    }
    public void Spintoggle() {
        isRunning = !isRunning;
        if (isRunning)  this.Spin();
        else            this.SpinStop();
    }
    public void custom(double speed) {
        Spin.setPower(speed);
        isRunning = true;
    }
}