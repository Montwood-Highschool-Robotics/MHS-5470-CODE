package org.firstinspires.ftc.teamcode.Subsystems.transfer;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.util.Settings;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@SuppressWarnings("all")
//TODO: Add limiting so only one fire sequence can run at a time
public class Kicker {
    private Servo[] OuttakeServo;
    private ElapsedTime servoTimer;
    private Integer[] order;
    private ExecutorService executor = Executors.newSingleThreadExecutor();
    private Future<?> future = null;
    private boolean isBusy = false;

    public Kicker(HardwareMap hwMap) {
        OuttakeServo = new Servo[3];

            for (int i = 0; i < OuttakeServo.length; i++) {
                OuttakeServo[i] = hwMap.get(Servo.class, Settings.HardwareNames.Transfer.KICKERS[i]);
            }

            servoTimer = new ElapsedTime(ElapsedTime.Resolution.SECONDS);
            order = new Integer[3];
    }

    public void start() {
        this.kickAllServosDown();
    }
    public void setFireSequence(Integer[] order) {
        this.order = order;
    }

    public void runFireSequence (Integer[] order)  {
        this.order = order;
        this.cancelSequence();
        this.future = this.executor.submit(this::createFireSequence);
    }
    private void createFireSequence() {
            for (int i : this.order) {
                servoTimer.reset();
                while (servoTimer.time() < Settings.Positions.Transfer.RUN_TO_POS_TIME) {
                    kickServoUp(i);
                }

                servoTimer.reset();
                while (servoTimer.time() < Settings.Positions.Transfer.RUN_TO_POS_TIME) {
                    kickServoDown(i);
                }
        }
        isBusy = false;
    }

    public void cancelSequence() {
        if (future != null && !future.isDone()) {
            future.cancel(true);
            this.kickAllServosDown();
            this.isBusy = false;
        }
    }

    public void kickServoUp(int servoIndex) {
        OuttakeServo[servoIndex].setPosition(Settings.Positions.Transfer.upPos[servoIndex]);
    }

    public void kickServoDown(int servoIndex) {
        OuttakeServo[servoIndex].setPosition(Settings.Positions.Transfer.downPos[servoIndex]);
    }

    public void kickAllServosDown() {
        this.cancelSequence();
            for (int i = 0; i < OuttakeServo.length; i++) {
                this.kickServoDown(i);
            }
    }

    public Double[] getServoPositions() {
        return new Double[] {OuttakeServo[0].getPosition(), OuttakeServo[1].getPosition(), OuttakeServo[2].getPosition()};
    }

    public boolean isBusy() {
        return false;
    }

    public void stop() {
        this.cancelSequence();
        executor.shutdown();
        executor = null;
        future = null;
        order = null;
        servoTimer.reset();
    }
}