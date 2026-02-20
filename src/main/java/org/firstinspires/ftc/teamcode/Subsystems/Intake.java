package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.util.Settings.HardwareNames;
import org.firstinspires.ftc.teamcode.util.Settings.Positions;


public class Intake {
    public DcMotorEx Intake;
    private boolean isRunning = false;


    /**
     * Initializes the Intake of the robot
     * @param hardwareMap   An OpMode HardwareMap object
     */
    public Intake (HardwareMap hardwareMap) {
        Intake = hardwareMap.get(DcMotorEx.class, HardwareNames.Intake.INTAKE);
        Intake.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.FLOAT);
        Intake.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    /**
     * Run the intake
     */
    public void run() {
        Intake.setPower(1);
        isRunning = true;
    }

    /**
     * Eject an artifact in the robot
     */
    public void eject() {
        Intake.setPower(-1);
        isRunning = true;
    }

    public void stop() {
        Intake.setPower(0);
        isRunning = false;
    }
    public void toggle() {
        isRunning = !isRunning;
        if (isRunning)  this.run();
        else            this.stop();
    }
    public void custom(double speed) {
        Intake.setPower(speed);
        isRunning = true;
    }
}