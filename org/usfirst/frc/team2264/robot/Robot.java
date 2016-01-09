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
	private RobotDrive drive;
	private Solenoid solenoid;
	private Joystick joystick;
	private Monostable fireButton;
	
	/**
	 * The robotInit method sets up the programming of the robot.
	 */
    public void robotInit() {
    	Talon leftMotor = new Talon(0),
    			rightMotor = new Talon(1);
    	this.drive = new RobotDrive(leftMotor, rightMotor);
    	this.solenoid = new Solenoid(2);
    	this.joystick = new Joystick(0);
    	this.fireButton = new Monostable(this.joystick, 1);
    }

    /**
     * The teleopPeriodic method is called repeatedly during the teleop phase.
     */
    public void teleopPeriodic() {
        this.drive.arcadeDrive(this.joystick);
        this.solenoid.set(this.fireButton.getAsBoolean());
    }
}
