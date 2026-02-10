package org.firstinspires.ftc.teamcode.Subsystems.transfer;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.util.Artifact;

public class Transfer {
    public Kicker OuttakeServo;
    public Sorter Spin;

    public Transfer (HardwareMap hwMap) {
        OuttakeServo = new Kicker(hwMap);
        Spin = new Sorter(hwMap);
    }

    public void start() {
        OuttakeServo.start();
    }

    public void fireSortedArtifacts() {
        OuttakeServo.runFireSequence(Spin.getOrder());
    }

    public void cancelFire() {
        OuttakeServo.cancelSequence();
    }

    public void kickServoUp(int servoIndex) {
        OuttakeServo.kickServoDown(servoIndex);
    }

    public void kickServoDown(int servoIndex) {
        OuttakeServo.kickServoUp(servoIndex);
    }

    public void kickAllServosDown() {
        OuttakeServo.kickAllServosDown();
    }

    public void setMotif(Artifact[] motif) {
        Spin.setMotif(motif);
    }

    public Artifact[] getMotif() {
        return Spin.getMotif();
    }
}
