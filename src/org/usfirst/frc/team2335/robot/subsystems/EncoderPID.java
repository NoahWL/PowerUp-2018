package org.usfirst.frc.team2335.robot.subsystems;

import org.usfirst.frc.team2335.robot.Robot;
import org.usfirst.frc.team2335.robot.RobotMap;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.PIDSubsystem;

public class EncoderPID extends PIDSubsystem
{
	Encoder cimEncoderLeft, cimEncoderRight;
	
	//TODO: Fine tune these, currently not accurate what so ever
	private static double speed = 1.0, error = 2000;
	
	private String alliancePlacements;
	
	private final int[] encoderCounts = new int[3];
	
	private final int encoderCenter;
	
    public EncoderPID()
    {
    	super("Cim Encoder", (speed / error), 0.0, 0.0);
    	
    	cimEncoderLeft = new Encoder(RobotMap.Sensors.leftEncoderA, RobotMap.Sensors.leftEncoderB);
    	cimEncoderRight = new Encoder(RobotMap.Sensors.rightEncoderA, RobotMap.Sensors.rightEncoderB);
    	
    	alliancePlacements = DriverStation.getInstance().getGameSpecificMessage();
    	
    	//TODO: Put correct values
    	encoderCounts[0] = 100;
    	encoderCounts[1] = 200;
    	encoderCounts[2] = 300;
    	
    	encoderCenter = 100;
    }
    
    public void setEncoderCount(char robotSide)
    {
    	for(int i = 0; i < 3; i++)
    	{
    		if(alliancePlacements.charAt(i) == robotSide)
    		{
    			setSetpoint(encoderCounts[i]);
    			break;
    		}
    	}
    	
    	if(robotSide == 'C') {
    		setSetpoint(encoderCenter);
    	}
    	DriverStation.reportError("You're fucked", true);
    	setSetpoint(encoderCounts[0]);
    }
    
    
    
    public void enable()
    {
    	enable();
    }
    
    public void disable()
    {
    	disable();
    }
    
    public void reset()
    {
    	cimEncoderLeft.reset();
    	cimEncoderRight.reset();
    }
    
    
    public void initDefaultCommand()
    {
    	
    }

    protected double returnPIDInput()
    {
        return (cimEncoderLeft.getDistance() + cimEncoderRight.getDistance()) / 2;
    }

    protected void usePIDOutput(double output)
    {
    	Robot.drive.drive(output, 0.0);
    }
}