package xyz.remexre.robotics.util;

import java.util.function.BooleanSupplier;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Trigger;

/**
 * The Monostable class takes an input and will return true itself the first
 * time its state is checked after the underlying function is true, then false
 * until the next transition from false to true by the underlying function.
 * @author Nathan Ringo
 */
public class Monostable implements BooleanSupplier {
	private BooleanSupplier triggerFn;
	private boolean last;

	/**
	 * Bases a Monostable off of an arbitrary boolean function.
	 */
	public Monostable(BooleanSupplier triggerFn) {
		this.triggerFn = triggerFn;
		this.last = false;
	}
	/**
	 * Bases a Monostable off of a particular button on a joystick.
	 */
	public Monostable(Joystick joystick, int button) {
		this.triggerFn = () -> joystick.getRawButton(button);
		this.last = false;
	}
	/**
	 * Bases a Monostable off of a Trigger object.
	 */
	public Monostable(Trigger trigger) {
		this.triggerFn = trigger::get;
		this.last = false;
	}

	/**
	 * Returns true if a state transition from false to true has occured on the
	 * underlying function since the last time this function was called.
	 */
	@Override
	public boolean getAsBoolean() {
		if(this.triggerFn.getAsBoolean()) { // If the trigger is true now...
			if(this.last) { // But was true before...
				return false; // No transition occured.
			} else { // And was false before...
				// A transition occured, so update the internal state to account
				// for this and return true.
				return (this.last = true);
			}
		} else { // Otherwise...
			// Set the internal state to false and return false.
			return (this.last = false);
		}
	}
}
