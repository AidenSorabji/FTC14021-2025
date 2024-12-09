// FTCTeamCode Package (Holds Everything)
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import java.util.List;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Blinker;
import com.qualcomm.robotcore.hardware.HardwareDevice;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

// Specify Drive-Mode
@TeleOp

public class GenisisRB2 extends LinearOpMode {
    // Classifies Motor Variables
    private DcMotor frontRight;
    private DcMotor frontLeft;
    private DcMotor backRight;
    private DcMotor backLeft;

    // Classifies ViperSlide Variables
    private DcMotor viperLeft;
    private DcMotor viperRight;

    double ViperPower;

    private CRServo beltServo;
    private CRServo clawSpin;
    private Servo tilt1;

    private Servo push;

    public void runOpMode() {
        // Correspond Motor Classification With Robot Controller (RC)
        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        backRight = hardwareMap.get(DcMotor.class, "backRight");

        // Correspond Viper Classification With Robot Controller (RC)
        viperLeft = hardwareMap.get(DcMotor.class, "viperLeft");
        viperRight = hardwareMap.get(DcMotor.class, "viperRight");
        viperLeft.setDirection(DcMotor.Direction.FORWARD);
        viperRight.setDirection(DcMotor.Direction.REVERSE);

        beltServo = hardwareMap.crservo.get("beltServo");
        beltServo.resetDeviceConfigurationForOpMode();

        clawSpin = hardwareMap.crservo.get("spinServo");
        clawSpin.resetDeviceConfigurationForOpMode();

        push = hardwareMap.get(Servo.class, "pushServo");
        tilt1 = hardwareMap.get(Servo.class, "tiltServo");

        // Reverse Front Motors
        frontRight.setDirection(DcMotorSimple.Direction.REVERSE);
        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);

        // Classify Zero-Power Break
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {

            // first controller control for driving
            double y = gamepad1.left_stick_y * 0.75;
            double x = -gamepad1.left_stick_x * 0.75;
            double rx = -gamepad1.right_stick_x * 0.75;

            // second controller control for claw
            // viper slide extension
            // ViperPower = gamepad2.right_stick_y * -1;
            // if (ViperPower == 0){
            // ViperPower = 0.1;
            //

            if (gamepad2.dpad_up) {
                ViperPower = 1;
            } else if (gamepad2.dpad_down) {
                ViperPower = -1;
            } else {
                ViperPower = 0.1;
            }

            // arm extension
            // if(gamepad2.b){
            // beltServo.setPower(-1);
            // }
            // else if (gamepad2.x){
            // beltServo.setPower(1);
            // }
            // else{
            // beltServo.setPower(0);
            // }
            beltServo.setPower(gamepad2.right_stick_y * -2);
            if (gamepad2.right_stick_y == 0) {
                beltServo.setPower(0);
            }
            // eject the block
            if (gamepad2.right_bumper) {
                push.setPosition(1);
                sleep(1000);
                push.setPosition(0);
            }

            // tilt the claw
            if (gamepad2.right_trigger != 0) {
                tilt1.setPosition(0.53);
            } else if (gamepad2.left_trigger != 0) {
                tilt1.setPosition(0.175);
            }

            // Horizontal spin
            if (gamepad2.dpad_right) {
                clawSpin.setPower(-0.5);
            } else if (gamepad2.dpad_left) {
                clawSpin.setPower(0.5);
            } else {
                clawSpin.setPower(0);
            }
            if (gamepad2.a) {
                push.setPosition(0.57);
            } else {
                push.setPosition(0.2);
            }

            // Movement Code (Forward, Backward, StrafeL, StrafeR, TurnL, TurnR)
            frontLeft.setPower(y + x + rx);
            backLeft.setPower(y - x + rx);
            frontRight.setPower(y - x - rx);
            backRight.setPower(y + x - rx);

            viperLeft.setPower(ViperPower);
            viperRight.setPower(ViperPower);

            telemetry.addData("Status", "Initialized");
            telemetry.update();
        }

    }
}
