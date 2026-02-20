package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;


public class SubsystemsManager {
    private Intake intake;

    public SubsystemsManager(HardwareMap hwMap) {
        intake = new Intake(hwMap);
    }
}
