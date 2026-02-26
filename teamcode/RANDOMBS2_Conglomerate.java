package org.firstinspires.ftc.teamcode;

import android.app.Activity;
import android.view.View;

import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.NormalizedColorSensor;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.teamcode.util.AllianceColor;
import org.firstinspires.ftc.teamcode.util.Artifact;
import static org.firstinspires.ftc.teamcode.util.Hardware.dt;
import static org.firstinspires.ftc.teamcode.util.Hardware.intake;

//Word of the day: Besmirch - To besmirch the reputation, name, honor, etc. of someone or something is to cause harm or damage to it.

@TeleOp
public class
RANDOMBS2_Conglomerate extends LinearOpMode {
    private PIDController controller;

    public double p = 0.00000000001, i = 0.1, d = 0.0000135;
    // p = 0.0011,H, i = 0000008,L, d = 0.00135,L,
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
    private DcMotorEx Turret;
    private DcMotor Shooter1;
    private DcMotor Shooter2;
    private DcMotor Intake;
    private CRServo AngleFish;
    private CRServo Spin;
    private Limelight3A limelight;
    private ElapsedTime runtime = null;
    NormalizedColorSensor colorSensor;
    View relativeLayout;
    static final int STREAM_WITH = 1920;
    static final double STREAM_HEIGHT = 1080;

    private ElapsedTime timer = new ElapsedTime();
    private ElapsedTime targetLostTimer = new ElapsedTime();

    private static final double POSITION_TOLERANCE = 1.5;
    private static final double MIN_POWER = 0.0;
    private static final double MAX_POWER = 0.5;
    private double targetX = 0.0;
    private double integral = 0.0;
    private double lastError = 0.0;
    public double highTargetPose = 270;
    public double lowTargetPose = 0;
    public double curTargetPose = highTargetPose;
    private final AllianceColor alliance = new AllianceColor(AllianceColor.Selection.BLUE);


    private void stopTurret() {
        Turret.setPower(0);
        resetPID();
    }

    private void resetPID() {
        integral = 0;
        lastError = 0;
    }

    @Override
    public void runOpMode() throws InterruptedException {


        Turret = hardwareMap.get(DcMotorEx.class,"Turret");
        limelight = hardwareMap.get(Limelight3A.class,"limelight");
        controller = new PIDController(p, i, d);
        limelight.start();
        limelight.pipelineSwitch(0);
        limelight.setPollRateHz(50);

        DcMotor FRW = hardwareMap.dcMotor.get("FRW");
        DcMotor FLW = hardwareMap.dcMotor.get("FLW");
        DcMotor BRW = hardwareMap.dcMotor.get("BRW");
        DcMotor BLW = hardwareMap.dcMotor.get("BLW");
        DcMotor Shooter1 = hardwareMap.dcMotor.get("Shooter1");
        DcMotor Shooter2 = hardwareMap.dcMotor.get("Shooter2");
        DcMotor Intake = hardwareMap.dcMotor.get("Intake");


        CRServo AngleFish = hardwareMap.crservo.get("AngleFish");
        CRServo Spin = hardwareMap.crservo.get("Spin");

        telemetry.setMsTransmissionInterval(53);
        int relativeLayoutID = hardwareMap.appContext.getResources().getIdentifier("RelativeLayout", "ID", hardwareMap.appContext.getPackageName());
        relativeLayout = ((Activity) hardwareMap.appContext).findViewById(relativeLayoutID);




        waitForStart();
        while (opModeIsActive()) {


            FLW.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            BLW.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            FRW.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            BRW.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


            BRW.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            FRW.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            BLW.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            FLW.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            BRW.setDirection(DcMotorSimple.Direction.FORWARD);

            


            double y = -gamepad1.left_stick_y; // Remember, this is reversed
            double x = -gamepad1.left_stick_x; // Counteract imperfect strafing
            double rx = .8 * (gamepad1.right_stick_x);



            double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), .65);
            double frontLeftPower = (y - x + rx) / denominator;
            double backLeftPower = (y + x + rx) / denominator;
            double frontRightPower = (y + x - rx) / denominator;
            double backRightPower = (y - x - rx) / denominator;


            FLW.setPower(frontLeftPower * -1);
            BLW.setPower(backLeftPower * -1);
            FRW.setPower(frontRightPower * 1);
            BRW.setPower(backRightPower * 1);




            //PID :3
            LLResult result = limelight.getLatestResult();
            if (result.isValid() || limelight.getLatestResult() == null) {

                double turretPosFromTargetInTicks = result.getTx() * -7.35;
                double pidPower = controller.calculate(turretPosFromTargetInTicks,0.0);

                Turret.setPower(pidPower);

            }



            if (gamepad2.right_bumper) {
                Shooter1.setPower(-0.9);
                Shooter2.setPower(0.9);
            } else if (gamepad2.left_bumper) {
                Shooter1.setPower(-0.7);
                Shooter2.setPower(0.7);
            } else {
                Shooter1.setPower(0);
                Shooter2.setPower(0);
            }



            if (gamepad2.right_trigger > 0.1) {
                Intake.setPower(1);
            } else if (gamepad2.left_trigger > 0.1) {
                Intake.setPower(-1);
            } else {
                Intake.setPower(0);
            }


            while (gamepad2.touchpad) { //gamepad2.dpad_left
                Turret.setTargetPosition(0);
            }




            if (gamepad2.dpad_up) {
                AngleFish.setPower(0.5);
            } else if (gamepad2.dpad_down) {
                AngleFish.setPower(-0.5)  ;
            }
            else {
                AngleFish.setPower(0);
            }


            if (gamepad2.square) {
                Spin.setPower(-1);


            } else if (gamepad2.triangle) {
                Spin.setPower(1);


            } else {
                Spin.setPower(0);
            }







            if (gamepad2.ps) {
                Turret.setTargetPosition(180);
            }
            if (gamepad1.ps) {
                Turret.setTargetPosition(180);
            }






        }

    }
}


