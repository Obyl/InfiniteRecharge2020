package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;

import java.util.function.DoubleSupplier;

import frc.robot.Constants;
import frc.robot.subsystems.Turret;
import frc.robot.util.Utils;

/**
 * This class allows for rotation of the turret using
 * one axis of a joystick.
 * 
 * It is used by the operator for large-scale turret
 * adjustments (for when the Limelight's target is
 * completely out of the camera frame) before the
 * AlignTurret command takes over.
 */
public class RotateWithJoystick extends CommandBase {

    private final Turret turret;
    private final DoubleSupplier input;

    public RotateWithJoystick(Turret turret, DoubleSupplier input){
        this.turret = turret;
        this.input = input;

        addRequirements(turret);
    }

    @Override
    public void execute(){
        double speed = Utils.applyDeadband(input.getAsDouble(), Constants.CONTROL_DEADBAND) / 2;
        //                                                                                    ^
        //                                  divide by two to allow for much finer turret control
        turret.setRotatorSpeed(speed);
    }

    @Override
    public boolean isFinished(){
        return false;
    }

    @Override
    public void end(boolean interrupted){
        turret.setRotatorSpeed(0);
    }

}