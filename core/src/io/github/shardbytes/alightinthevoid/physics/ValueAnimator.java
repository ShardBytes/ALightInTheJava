package io.github.shardbytes.alightinthevoid.physics;

import java.util.function.Consumer;

/**
 * Inspired by @Plasmoxy's Tween class in JS
 * Wait not inspired, literally Javascript -> Java rewrite :D
 *
 * Takes a value and makes sure it transitions smoothly between two values.
 * Basically so the value doesn't jump from 0 to 10, but smoothly moves from 0 to 1, then to 2... and so on.
 * Depends on ticking the ValueAnimator object as a trigger.
 */
public class ValueAnimator{
	
	private Double variable;
	private Double changeRate;
	private Double target;
	private Consumer<Double> setter;
	private boolean stopOnReached;
	private boolean active;
	
	/**
	 * Constructs a new ValueAnimator object
	 * @param value Value that should be animated
	 * @param changeRate Rate at which the value should be changed
	 * @param setter Setter that is used to set the value in the first parameter
	 * @param stopOnValueReached Should this object be disabled when the value is reached or should it listen to more changes?
	 */
	public ValueAnimator(Double value, Double changeRate, Consumer<Double> setter, boolean stopOnValueReached){
		this.variable = value;
		this.setter = setter;
		this.changeRate = changeRate;
		this.stopOnReached = stopOnValueReached;
		this.target = variable;
		this.active = false;
	}
	
	/**
	 * Sets ValueAnimator target value which the value in constructor should be animated to.
	 * @param target Target value
	 */
	public void setTargetValue(double target){
		this.target = target;
	}
	
	/**
	 * Sets whether ValueAnimator is active or not.
	 * @param active Should ValueAnimator be active?
	 */
	public void setActive(boolean active){
		this.active = active;
	}
	
	/**
	 * Step the value animator by one step. This should be called every frame after rendering is complete.
	 * @param delta Delta time
	 */
	public void step(float delta){
		if(active){
			double d = changeRate * delta;
			
			if(variable > target - d && variable < target + d){
				variable = target;
				
				if(stopOnReached){
					setActive(false);
				}
				
			}
			
			if(variable < target){
				variable += d;
			}else if(variable > target){
				variable -= d;
			}
			
			setter.accept(variable);
			
		}
		
	}
	
}
