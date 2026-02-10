package org.firstinspires.ftc.teamcode.Subsystems.shooter;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.util.AllianceColor;

public class Shooter {
    public Flywheel flywheel;
    public Tilt AngleFish;
    public Turret Turret;
    private AllianceColor alliance;

    public Shooter(HardwareMap hwMap) {
        flywheel = new Flywheel(hwMap);
        AngleFish = new Tilt(hwMap);
        Turret = new Turret(hwMap);
    }

    public void runLoop(double x, double y, double heading) {
        Turret.loop(x, y, heading);
        flywheel.adaptive(x, y, alliance);
        AngleFish.auto(flywheel.getDistance(x, y, alliance));
    }

    public Tilt getTilt() {
        return AngleFish;
    }
    public Flywheel getFlywheel() {
        return flywheel;
    }
    public Turret getTurret() {
        return Turret;
    }

    public void setAlliance(AllianceColor alliance) {

        Turret.setAlliance(alliance);
        this.alliance = alliance;
    }
}
