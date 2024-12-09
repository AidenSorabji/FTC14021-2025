// EVERYTHING WORKS AT 13.7v - 13.0v
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Blinker;

@Autonomous(name = "LeftSideProcedurals")
public class LeftSideProcedurals extends LinearOpMode {

    // Classify Motor Variables
    private DcMotor backLeft;
    private DcMotor backRight;
    private DcMotor frontLeft;
    private DcMotor frontRight;

    private DcMotor viperLeft;
    private DcMotor viperRight;

    // Classify viper slide variables
    double ViperPower;

    private CRServo beltServo;
    private CRServo clawSpin;
    private Servo tilt1;

    private Servo push;

    @Override
    public void runOpMode() {
        backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        backRight = hardwareMap.get(DcMotor.class, "backRight");
        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");

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

        waitForStart();
        if (opModeIsActive()) {
            // Move viper up a bit
            viperLeft.setPower(1);
            viperRight.setPower(1);
            sleep(250);

            viperLeft.setPower(0);
            viperRight.setPower(0);

            // Set tilt upright
            tilt1.setPosition(0.53);
            sleep(1000);

            viperLeft.setPower(0.1);
            viperRight.setPower(0.1);

            // Move forward towards the bar
            frontLeft.setPower(-0.4);
            frontRight.setPower(-0.4);
            backLeft.setPower(-0.4);
            backRight.setPower(-0.4);
            sleep(740);

            frontLeft.setPower(0);
            frontRight.setPower(0);
            backLeft.setPower(0);
            backRight.setPower(0);

            // Raise Viper
            viperLeft.setPower(1);
            viperRight.setPower(1);
            sleep(1200);

            viperLeft.setPower(0);
            viperRight.setPower(0);

            // Motor Power
            frontLeft.setPower(-0.4);
            frontRight.setPower(-0.4);
            backLeft.setPower(-0.4);
            backRight.setPower(-0.4);
            sleep(300);

            frontLeft.setPower(0);
            frontRight.setPower(0);
            backLeft.setPower(0);
            backRight.setPower(0);

            // Wait a bit
            sleep(200);

            // Lower Viper while moving a tiny bit forward to latch specimen
            viperLeft.setPower(-1);
            viperRight.setPower(-1);

            frontLeft.setPower(-0.2);
            frontRight.setPower(-0.2);
            backLeft.setPower(-0.2);
            backRight.setPower(-0.2);

            sleep(150);

            viperLeft.setPower(0);
            viperRight.setPower(0);

            frontLeft.setPower(0);
            frontRight.setPower(0);
            backLeft.setPower(0);
            backRight.setPower(0);

            // Wait a bit to verify everything
            sleep(300);

            // Lower Viper slide while going back. Reset Tilt position.
            frontLeft.setPower(0.7);
            frontRight.setPower(0.7);
            backLeft.setPower(0.7);
            backRight.setPower(0.7);

            viperLeft.setPower(-0.7);
            viperRight.setPower(-0.7);

            tilt1.setPosition(0.175);

            sleep(400);

            viperLeft.setPower(0);
            viperRight.setPower(0);

            frontLeft.setPower(0);
            frontRight.setPower(0);
            backLeft.setPower(0);
            backRight.setPower(0);

            sleep(1000);

            // Twist Left a bit
            frontLeft.setPower(0.5);
            frontRight.setPower(-0.5);
            backLeft.setPower(0.5);
            backRight.setPower(-0.5);

            sleep(100);

            frontLeft.setPower(0);
            frontRight.setPower(0);
            backLeft.setPower(0);
            backRight.setPower(0);

            sleep(750);

            // Strafe Right
            frontLeft.setPower(0.5);
            frontRight.setPower(-0.5);
            backLeft.setPower(-0.5);
            backRight.setPower(0.5);

            sleep(1400);

            frontLeft.setPower(0);
            frontRight.setPower(0);
            backLeft.setPower(0);
            backRight.setPower(0);

            sleep(400);

            // Go forward a bit
            frontLeft.setPower(-0.4);
            frontRight.setPower(-0.4);
            backLeft.setPower(-0.4);
            backRight.setPower(-0.4);

            sleep(1450);

            frontLeft.setPower(0);
            frontRight.setPower(0);
            backLeft.setPower(0);
            backRight.setPower(0);

            sleep(500);

            // 90 degree turn right
            frontLeft.setPower(-0.4);
            frontRight.setPower(0.4);
            backLeft.setPower(-0.4);
            backRight.setPower(0.4);

            sleep(650);

            frontLeft.setPower(0);
            frontRight.setPower(0);
            backLeft.setPower(0);
            backRight.setPower(0);

            sleep(500);

            // Forward a bit
            frontLeft.setPower(-0.4);
            frontRight.setPower(-0.4);
            backLeft.setPower(-0.4);
            backRight.setPower(-0.4);

            sleep(500);

            frontLeft.setPower(0);
            frontRight.setPower(0);
            backLeft.setPower(0);
            backRight.setPower(0);

            sleep(500);

            // Push viper slide up
            viperLeft.setPower(0.5);
            viperRight.setPower(0.5);

            sleep(510);

            viperLeft.setPower(0);
            viperRight.setPower(0);
        }
    }
}