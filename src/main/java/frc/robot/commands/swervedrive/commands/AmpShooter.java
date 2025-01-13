/*package frc.robot.commands.swervedrive.commands;

import edu.wpi.first.wpilibj2.command.Command;

import frc.robot.subsystems.mechanisms.ShooterBottom;
import frc.robot.subsystems.mechanisms.ShooterTop;


public class AmpShooter extends Command{;
    public final ShooterBottom ShooterBottomSubsystem;
    public final ShooterTop ShooterTopSubsystem;
    public final double speed;


    // Create new Stage Shooter.
    public AmpShooter(ShooterTop shooterTopSubsystem, ShooterBottom shooterBottomSubsystem, double speed) {
        this.ShooterBottomSubsystem = shooterBottomSubsystem;
        this.ShooterTopSubsystem = shooterTopSubsystem;
        this.speed = speed;

        // Use addRequirements() here to declare subsystem dependicies
        addRequirements(ShooterBottomSubsystem, ShooterTopSubsystem);
    }
    
    // Called when the command is intially scheduled
    @Override
    public void initialize() {}
    
    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        ShooterTopSubsystem.set(speed);
        ShooterBottomSubsystem.set(-speed);

        
    }

    // Called once the command ends or is interruped.
    @Override
    public void end(boolean interruped) {
        ShooterTopSubsystem.set(0);
        ShooterBottomSubsystem.set(0);
    }

    //Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }
}*/