#!/usr/bin/env python3

import wpilib
import wpilib.buttons

class Robot(wpilib.IterativeRobot):
    def robotInit(self):
        self.drive = wpilib.RobotDrive(0, 1)
        self.joystick = wpilib.Joystick(0)
        self.button = wpilib.buttons.JoystickButton(self.joystick, 1)
        self.buttonPrevious = False
    def teleopPeriodic(self):
        self.drive.mecanumDrive_Cartesian(self.joystick.getX(), self.joystick.getY(), 0, 0)
        self.drive.setMaxOutput(self.joystick.getZ())
        # Check for shoot state
        buttonState = self.button.get()
        if buttonState and not self.buttonPrevious:
            self.shoot()
        self.buttonPrevious = buttonState
    def shoot(self):
        # TODO
        print("Shootin'!")

if __name__ == "__main__":
    wpilib.run(Robot)
