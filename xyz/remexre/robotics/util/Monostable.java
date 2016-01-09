package xyz.remexre.robotics.util;

import java.util.function.BooleanSupplier;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Trigger;

public class Monostable implements BooleanSupplier {
	private BooleanSupplier triggerFn;
	private boolean last;

	public Monostable(BooleanSupplier triggerFn) {
		this.triggerFn = triggerFn;
		this.last = false;
	}
	public Monostable(Joystick joystick, int button) {
		this.triggerFn = () -> joystick.getRawButton(button);
		this.last = false;
	}
	public Monostable(Trigger trigger) {
		this.triggerFn = trigger::get;
		this.last = false;
	}
	
	@Override
	public boolean getAsBoolean() {
		if(this.triggerFn.getAsBoolean()) {
			if(this.last) {
				return false;
			} else {
				return (this.last = true);
			}
		} else {
			return (this.last = false);
		}
	}
}
