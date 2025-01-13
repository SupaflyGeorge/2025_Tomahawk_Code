// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.MathUtil;
/*import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;*/
import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
/*import frc.robot.subsystems.mechanisms.Elevator;
import frc.robot.subsystems.mechanisms.ShooterTop;
import frc.robot.subsystems.mechanisms.ShooterBottom;
import frc.robot.subsystems.mechanisms.IntakeArm;
import frc.robot.subsystems.mechanisms.RightClimber;
import frc.robot.subsystems.mechanisms.Intake;
import frc.robot.subsystems.mechanisms.Feeder;
import frc.robot.subsystems.mechanisms.LeftClimber;
import frc.robot.subsystems.mechanisms.LimelightSubsystem;*/
import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.swervedrive.drivebase.AbsoluteDriveAdv;
//import frc.robot.commands.swervedrive.auto.ShootAndDrive;
/*import frc.robot.commands.swervedrive.commands.AmpShooter;
import frc.robot.commands.swervedrive.commands.ElevatorDown;
import frc.robot.commands.swervedrive.commands.ElevatorUp;
import frc.robot.commands.swervedrive.commands.FeedNote;
import frc.robot.commands.swervedrive.commands.IntakeDown;
import frc.robot.commands.swervedrive.commands.IntakeNote;
import frc.robot.commands.swervedrive.commands.IntakeUp;
import frc.robot.commands.swervedrive.commands.LeftClimberControl;
import frc.robot.commands.swervedrive.commands.OutTakeNote;
import frc.robot.commands.swervedrive.commands.OutTakeNoteForAmp;
import frc.robot.commands.swervedrive.commands.RightClimberControl;
import frc.robot.commands.swervedrive.commands.SpeakerShooter;
import frc.robot.commands.swervedrive.drivebase.AbsoluteDriveAdv;
//import frc.robot.commands.swervedrive.auto.ShootAndDrive;*/
import frc.robot.subsystems.swervedrive.SwerveSubsystem;
import java.io.File;
//import java.lang.management.OperatingSystemMXBean;

import com.pathplanner.lib.auto.AutoBuilder;
//import com.pathplanner.lib.auto.NamedCommands;




/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a "declarative" paradigm, very
 * little robot logic should actually be handled in the {@link Robot} periodic methods (other than the scheduler calls).
 * Instead, the structure of the robot (including subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer
{
  SendableChooser<Command> autonomousChooser = new SendableChooser<>();
  // The robot's subsystems and commands are defined here...
  public static final SwerveSubsystem drivebase = new SwerveSubsystem(new File(Filesystem.getDeployDirectory(),
                                                                         "swerve/neo"));
  /*public static final ShooterTop shooterTop = new ShooterTop();
  public static final ShooterBottom shooterBottom = new ShooterBottom();
  private final Elevator elevator = new Elevator();
  public static final Feeder feeder = new Feeder();
  public static final Intake intake = new Intake();
  public static final IntakeArm intakeArm = new IntakeArm();
  private final RightClimber rightClimber = new RightClimber();
  private final LeftClimber leftClimber = new LeftClimber();
   public static LimelightSubsystem limelightSubsystem = new LimelightSubsystem();*/

  // Replace with CommandPS4Controller or CommandJoystick if needed
  final CommandXboxController driverXbox = new CommandXboxController(0);
  final CommandXboxController operatorXbox = new CommandXboxController(1);

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer()
  {

    /*NamedCommands.registerCommand("Speaker", new SpeakerShooter(shooterTop,shooterBottom, 1));
    NamedCommands.registerCommand("Speaker Stop", new SpeakerShooter(shooterTop,shooterBottom, 0));
    NamedCommands.registerCommand("Intake", new IntakeNote(intake, .3));
    NamedCommands.registerCommand("IntakeStop", new IntakeNote(intake, 0));
    NamedCommands.registerCommand("OutTake", new OutTakeNote(intake, .3));
    NamedCommands.registerCommand("OutTake Stop", new OutTakeNote(intake, 0));
    NamedCommands.registerCommand("FeedNote", new FeedNote(feeder, .3));
    NamedCommands.registerCommand("FeedStop", new FeedNote(feeder, 0));
    NamedCommands.registerCommand("Intake Up", new IntakeUp(intakeArm, .6));
    NamedCommands.registerCommand("Intake Up Stop", new IntakeUp(intakeArm, 0));
    NamedCommands.registerCommand("Intake Down", new IntakeDown(intakeArm, -.3));
    NamedCommands.registerCommand("Intake Down Stop", new IntakeDown(intakeArm, 0));*/
  

    
    // Configure the trigger bindings
    configureBindings();

    AbsoluteDriveAdv closedAbsoluteDriveAdv = new AbsoluteDriveAdv(drivebase,
                                                                   () -> -MathUtil.applyDeadband(driverXbox.getLeftY(),
                                                                                                OperatorConstants.LEFT_Y_DEADBAND),
                                                                   () -> -MathUtil.applyDeadband(driverXbox.getLeftX(),
                                                                                                OperatorConstants.LEFT_X_DEADBAND),
                                                                   () -> -MathUtil.applyDeadband(driverXbox.getRightX(),
                                                                                                OperatorConstants.RIGHT_X_DEADBAND),
                                                                   driverXbox.getHID()::getYButtonPressed,
                                                                   driverXbox.getHID()::getAButtonPressed,
                                                                   driverXbox.getHID()::getXButtonPressed,
                                                                   driverXbox.getHID()::getBButtonPressed);

    // Applies deadbands and inverts controls because joysticks
    // are back-right positive while robot
    // controls are front-left positive
    // left stick controls translation
    // right stick controls the desired angle NOT angular rotation
    Command driveFieldOrientedDirectAngle = drivebase.driveCommand(
        () -> -MathUtil.applyDeadband(driverXbox.getLeftY(), OperatorConstants.LEFT_Y_DEADBAND),
        () -> -MathUtil.applyDeadband(driverXbox.getLeftX(), OperatorConstants.LEFT_X_DEADBAND),
        () -> -MathUtil.applyDeadband(driverXbox.getRightX(), OperatorConstants.RIGHT_X_DEADBAND),
        () -> -MathUtil.applyDeadband(driverXbox.getRightY(), OperatorConstants.RIGHT_X_DEADBAND));

    // Applies deadbands and inverts controls because joysticks
    // are back-right positive while robot
    // controls are front-left positive
    // left stick controls translation
    // right stick controls the angular velocity of the robot
    Command driveFieldOrientedAnglularVelocity = drivebase.driveCommand(
        () -> -MathUtil.applyDeadband(driverXbox.getLeftY(), OperatorConstants.LEFT_Y_DEADBAND),
        () -> -MathUtil.applyDeadband(driverXbox.getLeftX(), OperatorConstants.LEFT_X_DEADBAND),
        () -> -MathUtil.applyDeadband(driverXbox.getRightX(), OperatorConstants.RIGHT_X_DEADBAND));


 //The line below is where you choose the type of drive you want from the commands above.
 //driveFieldOrientedAnglularVelocity is the type that we are currently using

    drivebase.setDefaultCommand(
        //!RobotBase.isSimulation() ? driveFieldOrientedAnglularVelocity : driveFieldOrientedDirectAngleSim);
        driveFieldOrientedAnglularVelocity);

        configureShuffleboard();
  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary predicate, or via the
   * named factories in {@link edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for
   * {@link CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller PS4}
   * controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight joysticks}.
   */
  private void configureBindings()
  {
    // Schedule `ExampleCommand` when `exampleCondition` changes to `true`

    driverXbox.start().onTrue((Commands.runOnce(drivebase::zeroGyro)));
    driverXbox.back().onTrue(Commands.runOnce(drivebase::addFakeVisionReading));
    //driverXbox.b().whileTrue(
      //  Commands.deferredProxy(() -> drivebase.driveToPose(
          //                         new Pose2d(new Translation2d(4, 4), Rotation2d.fromDegrees(0)))
        //                      ));
    // driverXbox.x().whileTrue(Commands.runOnce(drivebase::lock, drivebase).repeatedly());
 
  // DRIVER AND OPERATOR CONTROLS AND COMMANDS...............................................................................
  
  
  
    //Operator Controls

   /*  //Operator Controls rightBumper - Controls the Elevator Movement Up
    operatorXbox.rightBumper().onTrue(new ElevatorUp(elevator, .7));
    operatorXbox.rightBumper().onFalse(new ElevatorUp(elevator, 0));
    //Operator Controls leftBumper - Controls the Elevator Movement Down
    operatorXbox.leftBumper().onTrue(new ElevatorDown(elevator, .8));
    operatorXbox.leftBumper().onFalse(new ElevatorDown(elevator, 0));
    //RightTrigger - Controls to Shoot Into the Speaker
    operatorXbox.rightTrigger(0.5).onTrue(new SpeakerShooter(shooterTop,shooterBottom,0.75));
    operatorXbox.rightTrigger(0.5).onFalse(new SpeakerShooter(shooterTop, shooterBottom,0));
    //LeftTrigger - Feeds the Note Into the Speaker/AMP
    operatorXbox.leftTrigger(0.5).onTrue(new FeedNote(feeder, .45));
    operatorXbox.leftTrigger(0.5).onFalse(new FeedNote(feeder,0));
    //Y - Outake/ Outfeed the note to allow for calibrating to shoot into AMP
    operatorXbox.y().onTrue(new OutTakeNoteForAmp(shooterTop, shooterBottom, feeder, -0.15));
    operatorXbox.y().onFalse(new OutTakeNoteForAmp(shooterTop,shooterBottom, feeder, 0));
    //X - Shoots Into The AMP
    operatorXbox.x().onTrue(new AmpShooter(shooterTop, shooterBottom,1));
    operatorXbox.x().onFalse(new AmpShooter(shooterTop, shooterBottom,0));
    //A - Intakes the Notes
    operatorXbox.a().onTrue(new IntakeNote(intake, .7));
    operatorXbox.a().onFalse(new IntakeNote(intake, 0));
    //B - OutTakes the Notes-
    operatorXbox.b().onTrue(new OutTakeNote(intake, .4));
    operatorXbox.b().onFalse(new OutTakeNote(intake, 0));

    //Driver Controls

    //Driver Controls A - Moves the Intake Up
    driverXbox.a().onTrue(new IntakeUp(intakeArm, -0.50));
    driverXbox.a().onFalse(new IntakeUp(intakeArm, 0));
    //Driver Controls X- Moves the Intake Down
    driverXbox.x().onTrue(new IntakeDown(intakeArm, 0.5));
    driverXbox.x().onFalse(new IntakeDown(intakeArm, 0));
    //Driver Controls rightTrigger - RIGHT CLIMBER MOVE :O
    driverXbox.leftTrigger(0.5).onTrue( new RightClimberControl(rightClimber, 1));
    driverXbox.leftTrigger(0).onFalse( new RightClimberControl(rightClimber, 0));
    //Driver Controls leftTrigger - RIGHT CLIMBER MOVES DOWN :O
    driverXbox.rightTrigger(0.5).onTrue( new RightClimberControl(rightClimber, -1));
    driverXbox.rightTrigger(0).onFalse( new RightClimberControl(rightClimber, 0));
    //Driver Controls rightBumper - LEFT CLIMBER MOVES UP :O
    driverXbox.rightBumper().onTrue(new LeftClimberControl(leftClimber, 1)); 
    driverXbox.rightBumper().onFalse(new LeftClimberControl(leftClimber, 0)); 
    //Driver Controls leftBumper - LEFT CLIMBER MOVES DOWN :O
    driverXbox.leftBumper().onTrue(new LeftClimberControl(leftClimber, -1)); 
    driverXbox.leftBumper().onFalse(new LeftClimberControl(leftClimber, 0));*/
   
    }
    

   
//NEED TO FIGURE OUT HOW TO INPUT CODE AS DROP DOWN MENU BUTTON ON SMARTDASHBOARD -----------------------------------------

  private void configureShuffleboard() {
    autonomousChooser = AutoBuilder.buildAutoChooser();
    autonomousChooser.setDefaultOption("Do Nothing Auton", new InstantCommand());
    //autonomousChooser.addOption("New Auto", getAutonomousCommand());

    //Backup Camera Idea ig ---------------------------------------------------------------------------------
    /*HttpCamera httpCamera = new HttpCamera("limelight", "http://frcvision.local:8817/stream.mjpg");
    CameraServer.addCamera(httpCamera);
    Shuffleboard.getTab("Limelight").add(httpCamera);*/

  }

  public Command getAutonomousCommand() {
    //return autonomousChooser.getSelected();
    return drivebase.getAutonomousCommand("1 Note Center Auto");
    
  }
 //------------------------------------------------------------------------------------------------------------------------- 

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *+0
   * @return the command to run in autonomous
   */
  /*public Command getAutonomousCommand()
  {
    // An example command will be run in autonomous
    return drivebase.getAutonomousCommand("1st Inner Note");

    
  }*/
  public void setDriveMode()
  {
    //drivebase.setDefaultCommand();
  }

  public void setMotorBrake(boolean brake)
  {
    drivebase.setMotorBrake(brake);
  }
}
