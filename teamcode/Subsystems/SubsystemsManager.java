package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Subsystems.transfer.Transfer;

public class SubsystemsManager {
    private Transfer transfer;
    private Intake intake;

    public SubsystemsManager(HardwareMap hwMap) {
        transfer = new Transfer(hwMap);
        intake = new Intake(hwMap);
    }
}
