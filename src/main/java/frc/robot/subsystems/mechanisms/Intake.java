
/*package frc.robot.subsystems.mechanisms;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Intake extends SubsystemBase{
    //motors
    // final CANSparkMax IntakeMovement = new CANSparkMax(14, MotorType.kBrushless);
    private final SparkMax TopSpinner = new SparkMax(15, MotorType.kBrushed);
    private final SparkMax BottomSpinner = new SparkMax(16, MotorType.kBrushless);
    
    //creates new Intake
    public Intake() {}

    public void set(double speed) {
    
        TopSpinner.set(-speed);
        BottomSpinner.set(speed);
    }

}*/

