package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.TalonSRXFeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import static frc.robot.Constants.*;

public class Turret extends SubsystemBase {

    private final WPI_TalonSRX rotator = new WPI_TalonSRX(TURRET_ROTATOR);

    private double setpoint = 0;

    public Turret(){
        rotator.configSelectedFeedbackSensor(TalonSRXFeedbackDevice.Analog, 0, 10);
        rotator.setSelectedSensorPosition(0, 0, 10);

        rotator.config_kF(0, TURRET_F);
        rotator.config_kP(0, TURRET_P);
        rotator.config_kI(0, TURRET_I);
        rotator.config_kD(0, TURRET_D);
    }

    public void rotate(double degrees){
        setpoint += degrees;
        rotator.set(ControlMode.Position, setpoint);
    }

    public double getVelocity(){
        return rotator.getSelectedSensorVelocity();
    }

}