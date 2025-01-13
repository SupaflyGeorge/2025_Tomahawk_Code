/*package frc.robot.commands.swervedrive.commands;
import edu.wpi.first.wpilibj2.command.Command;

import frc.robot.subsystems.mechanisms.ShooterTop;
import frc.robot.subsystems.mechanisms.ShooterBottom;
import frc.robot.subsystems.mechanisms.Feeder;

public class OutTakeNoteForAmp extends Command{
    public final Feeder feederSubsystem;
    public final ShooterTop shooterTopSubsystem;
    public final ShooterBottom shooterBottomSubsystem;
    public final double speed;
    

    // Create new Stage Shooter.
    public OutTakeNoteForAmp(ShooterTop shooterTopSubsystem, ShooterBottom shooterBottomSubsystem, Feeder feederSubsystem, double speed) {
        this.shooterTopSubsystem = shooterTopSubsystem;
        this.shooterBottomSubsystem = shooterBottomSubsystem;
        this.feederSubsystem = feederSubsystem;
        this.speed = speed;
        

        // Use addRequirements() here to declare subsystem dependicies
        addRequirements(shooterTopSubsystem, shooterBottomSubsystem, feederSubsystem);
    }
    
    // Called when the command is intially scheduled
    @Override
    public void initialize() {}
    
    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        shooterTopSubsystem.set(speed);
        shooterBottomSubsystem.set(speed);
        feederSubsystem.set(speed);
        
    }

    // Called once the command ends or is interruped.
    @Override
    public void end(boolean interruped) {
        shooterTopSubsystem.set(speed);
        shooterBottomSubsystem.set(speed);
        feederSubsystem.set(speed);

    }

    //Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }
}
*/