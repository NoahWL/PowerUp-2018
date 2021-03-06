package org.usfirst.frc.team2335.robot.commands;

import org.usfirst.frc.team2335.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class QuickRelease extends Command
{

    public QuickRelease()
    {
        requires(Robot.vacuumArm);
    }

    // Called just before this Command runs the first time
    protected void initialize()
    {
    	setTimeout(3);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute()
    {
    	Robot.vacuumArm.releaseValve();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished()
    {
        return isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end()
    {
    	Robot.vacuumArm.closeValve();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted()
    {
    	end();
    }
}
