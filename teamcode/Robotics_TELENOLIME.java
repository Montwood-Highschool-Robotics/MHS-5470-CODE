package org.firstinspires.ftc.teamcode;

import android.app.Activity;
import android.view.View;

import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.NormalizedColorSensor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import static org.firstinspires.ftc.robotcore.external.navigation.AngleUnit.normalizeDegrees;
import static org.firstinspires.ftc.robotcore.external.navigation.AngleUnit.normalizeRadians;
//word of the day: roblophodia- Fear of words.

@TeleOp
public class
Robotics_TELENOLIME extends LinearOpMode {
    private DcMotor FLW;
    private DcMotor FRW;
    private DcMotor BLW;
    private DcMotor BRW;
    /*
    private DcMotor Shooter;
    private DcMotor Lift1;
    private DcMotor Lift2;
    private DcMotor Intake;
     */
    private DcMotor Turret;
    private DcMotor Shooter1;
    private DcMotor Shooter2;
    private DcMotor Intake;

    private CRServo LTopLift;
    private CRServo LBotLift;

    private CRServo AngleFish;
    private CRServo RBotLift;

    private CRServo RTopLift;

    private Servo OuttakeServo;

    private CRServo Spin;

    private Limelight3A limelight;
    private ElapsedTime runtime = null;
    NormalizedColorSensor colorSensor;
    View relativeLayout;
    static final int STREAM_WITH = 1920;
    static final double STREAM_HEIGHT = 1080;

    @Override
    public void runOpMode() throws InterruptedException {


        DcMotor FRW = hardwareMap.dcMotor.get("FRW");
        DcMotor FLW = hardwareMap.dcMotor.get("FLW");
        DcMotor BRW = hardwareMap.dcMotor.get("BRW");
        DcMotor BLW = hardwareMap.dcMotor.get("BLW");
        DcMotor Shooter1 = hardwareMap.dcMotor.get("Shooter1");
        DcMotor Shooter2 = hardwareMap.dcMotor.get("Shooter2");
        DcMotor Intake = hardwareMap.dcMotor.get("Intake");
        DcMotor Turret = hardwareMap.dcMotor.get("Turret");

        CRServo LTopLift = hardwareMap.crservo.get("LTopLift");
        CRServo LBotLift = hardwareMap.crservo.get("LBotLift");
        CRServo AngleFish = hardwareMap.crservo.get("AngleFish");
        CRServo RBotLift = hardwareMap.crservo.get("RBotLift");
        CRServo RTopLift = hardwareMap.crservo.get("RTopLift");
        Servo OuttakeServo = hardwareMap.servo.get("OuttakeServo");
        CRServo Spin = hardwareMap.crservo.get("Spin");


        telemetry.setMsTransmissionInterval(53);
        int relativeLayoutID = hardwareMap.appContext.getResources().getIdentifier("RelativeLayout", "ID", hardwareMap.appContext.getPackageName());
        relativeLayout = ((Activity) hardwareMap.appContext).findViewById(relativeLayoutID);


        waitForStart();
        while (opModeIsActive()) {
            if (gamepad1.circle) {
                telemetry.addData("Init Successful", 28);
                telemetry.update();
            }


            FLW.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            BLW.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            FRW.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            BRW.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            Turret.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

            BRW.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            FRW.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            BLW.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            FLW.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            Turret.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//            CRServo.setDirection(DcMotorSimple.Direction.FORWARD);


//            LLResult result = lightlimeA3.getLatestResult();
//            if (result != null && result.isValid()) {
//                double tx = result.getTx(); // How far left or right the target is (degrees)
//                double ty = result.getTy(); // How far up or down the target is (degrees)
//                double ta = result.getTa(); // How big the target looks (0%-100% of the image)
//
//                telemetry.addData("Target X", tx);
//                telemetry.addData("Target Y", ty);
//                telemetry.addData("Target Area", ta);
//            } else {
//                telemetry.addData("Limelight", "No Targets");
//            }


            double y = -gamepad1.left_stick_y; // Remember, this is reversed
            double x = -gamepad1.left_stick_x; // Counteract imperfect strafing
            double rx = .8 * (gamepad1.right_stick_x);


            double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), .65);
            double frontLeftPower = (y - x + rx) / denominator;
            double backLeftPower = (y + x + rx) / denominator;
            double frontRightPower = (y - x - rx) / denominator;
            double backRightPower = (y + x - rx) / denominator;
            double turretPower = (0.3);


            FLW.setPower(frontLeftPower * -1);
            BLW.setPower(backLeftPower * -1);
            FRW.setPower(frontRightPower * 1);
            BRW.setPower(backRightPower * 1);
            //Turret.setPower(turretPower);


            if (gamepad2.right_bumper) {
                Shooter1.setPower(-1);
                Shooter2.setPower(1);
            } else if (gamepad2.left_bumper) {
                Shooter1.setPower(-0.3);
                Shooter2.setPower(0.3);
            } else {
                Shooter1.setPower(0);
                Shooter2.setPower(0);
            }

            if (gamepad2.cross) {
                OuttakeServo.setPosition(0);//These are the angles that philip said were good if this explodes its all on him
            } else {
                OuttakeServo.setPosition(1);
            }


            if (gamepad2.right_trigger > 0.1) {
                Intake.setPower(1);
            } else if (gamepad2.left_trigger > 0.1) {
                Intake.setPower(-1);
            } else {
                Intake.setPower(0);
            }


            if (gamepad2.dpad_left) { //gamepad2.dpad_left
                Turret.setPower(.6);
            } else if (gamepad2.dpad_right) {
                Turret.setPower(-.6);
            } else {
                Turret.setPower(0);
            }


            if (gamepad1.dpad_up) {
                LTopLift.setPower(-1);
                LBotLift.setPower(1);
                RBotLift.setPower(-1);
                RTopLift.setPower(1);
            } else if (gamepad1.dpad_down) {
                LTopLift.setPower(1);
                LBotLift.setPower(-1);
                RBotLift.setPower(1);
                RTopLift.setPower(-1);
            } else {
                LTopLift.setPower(0);
                LBotLift.setPower(0);
                RBotLift.setPower(0);
                RTopLift.setPower(0);
            }

            if (gamepad2.dpad_up) {
                AngleFish.setPower(0.5);
            } else if (gamepad2.dpad_down) {
                AngleFish.setPower(-0.5);
            }else{
                AngleFish.setPower(0);
            }


            if (gamepad2.square) {
                Spin.setPower(1);
            } else if (gamepad2.triangle) {
                Spin.setPower(-1);
            }else{
                Spin.setPower(0);
            }
            }
        }
    }

