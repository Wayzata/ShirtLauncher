package org.usfirst.frc.team2264.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;
import xyz.remexre.robotics.util.Monostable;

/**
 * This is an example robot for example purposes.
 * @author Nathan Ringo
 */
public class Robot extends IterativeRobot {
    // The RobotDrive class manages multiple motors. This means that we can, for
    // example, specify "move at 80% power at a 35 degree angle" instead of
    // "set left motor to 92.5% power and right motor to 77.5% power".
    private RobotDrive drive;

    // A solenoid is used to trigger hydraulics.
    private Solenoid solenoid;

    // A Joystick is used to manage input from one Joystick, XBox Controller, or
    // (hopefully) Steam Controller. /r/FRC says Steam Controllers work, at
    // least, and shiny.
    private Joystick joystick;

    // The Monostable class is a custom class for use with input buttons -- to
    // avoid triggering the same action repeatedly when a button is held down,
    // it only returns that an event has occured at the first instant that it
    // has.
    private Monostable fireButton;

    /**
     * The robotInit method sets up the robot's hardware. Almost all the work
     * here should be initializing variables with references to hardware.
     */
    public void robotInit() {
        // Talons are the motor controllers we use. Basically, they convert
        // something like "25% power" to something like "9.8 volts".
        Talon leftMotor = new Talon(0),
            rightMotor = new Talon(1);
        // The RobotDrive class takes the motors that it will manage as inputs.
        this.drive = new RobotDrive(leftMotor, rightMotor);

        // I'm assuming we're connecting the solenoid over PWM port 2, as ports
        // 0 and 1 go to the motors.
        this.solenoid = new Solenoid(2);

        // The first joystick is joystick zero...
        this.joystick = new Joystick(0);

        // ...but the first button is button one.
        this.fireButton = new Monostable(this.joystick, 1);
    }

    /**
     * The teleopPeriodic method is called repeatedly during the teleop phase.
     */
    public void teleopPeriodic() {
        // This basically says "read the position of the joystick, then use that
        // to configure the motor speeds".
        this.drive.arcadeDrive(this.joystick);

        // This will instantaneously trigger the solenoid when the fire button
        // is pressed. I'm not sure how long we need to hold the solenoid open
        // for, so for now it'll trigger only for a short burst.
        this.solenoid.set(this.fireButton.getAsBoolean());
    }
}
