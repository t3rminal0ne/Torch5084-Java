/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

//Hardware imports:
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Compressor;
import com.playingwithfusion.TimeOfFlight;

//Other imports:
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  //DECLARING GLOBAL VARIABLES
  //Drivebase Motor-related (corresponds to "OPENing" the TalonFX motors in LABView):
  TalonFX driveBaseTalon1 = new TalonFX(1);
  TalonFX driveBaseTalon2 = new TalonFX(2);
  TalonFX driveBaseTalon3 = new TalonFX(3);
  TalonFX driveBaseTalon4 = new TalonFX(4);

  //Other Motors:
  TalonFX shooterTalon1 = new TalonFX(5);
  TalonFX shooterTalon2 = new TalonFX(6);

  TalonFX acceleratorTalon = new TalonFX(7);
  TalonFX conveyorTalon = new TalonFX(8);
  
  TalonFX innerIntakeTalon = new TalonFX(9);
  TalonFX outerIntakeTalon = new TalonFX(10);

  TalonFX climberTalon1 = new TalonFX(1);
  TalonFX climberTalon2 = new TalonFX(2);

  //Joystick:
  Joystick leftStick = new Joystick(0);
  Joystick rightStick = new Joystick(1);

  //Solenoid:
  Solenoid intakePistonSolenoid = new Solenoid(0);
  Solenoid climberPistonSolenoid = new Solenoid(0);

  //Compressor:
  Compressor compressor = new Compressor(0);

  //Time of flight:
  TimeOfFlight timeOfFlight1 = new TimeOfFlight(1);
  TimeOfFlight timeOfFlight2 = new TimeOfFlight(2);
  TimeOfFlight timeOfFlight3 = new TimeOfFlight(3);
  TimeOfFlight timeOfFlight4 = new TimeOfFlight(4);

  /**
   * This function is run when the robot is first started up and should be used
   * for any initialization code.
   */
  @Override
  public void robotInit() {
  }

  @Override
  public void autonomousInit() {
  }

  @Override
  public void autonomousPeriodic() {
  }

  @Override
  public void teleopInit() {
  }

  @Override
  public void teleopPeriodic() {

    //DRIVEBASE TALON STUFF:
    //Talon 2 follows talon 1, talon 4 follows talon 3
    driveBaseTalon2.follow(driveBaseTalon1);
    driveBaseTalon4.follow(driveBaseTalon3);

    //These two lines to help set PercentOutput:
    double leftStickValue = leftStick.getRawAxis(0);
    double rightStickValue = rightStick.getRawAxis(0);

    //Corresponds to "MC SET B/C MODE" (can't figure out what NeutralMode options there are)
    //talon1.setNeutralMode();
    //talon3.setNeutralMode();

    //Set percent output for talon1 and talon3 (talon 2 and 4 follow 1 and 3, respectively)
    driveBaseTalon1.set(ControlMode.PercentOutput, leftStickValue);
    driveBaseTalon3.set(ControlMode.PercentOutput, rightStickValue);
  
    //Corresponds to "MC DEAD BAND" in LABView (guess)
    driveBaseTalon1.configNeutralDeadband(.1);
    driveBaseTalon3.configNeutralDeadband(.1);

    //Corresponds to "MC SET SENSOR POS" in LABView (guess)
    driveBaseTalon1.setSelectedSensorPosition(0);
    driveBaseTalon3.setSelectedSensorPosition(0);

    //Missing code for "MC CONFIG PIDF"

    //SHOOTER TALON STUFF:
    shooterTalon2.follow(shooterTalon1);
    shooterTalon2.setInverted(true);
    //Missing code for "MC CONFIG PIDF"

    //ACCELERATOR TALON STUFF:
    acceleratorTalon.enableVoltageCompensation(true);
    acceleratorTalon.configVoltageCompSaturation(12);

    //CONVEYOR TALON STUFF:
    conveyorTalon.enableVoltageCompensation(true);
    conveyorTalon.configVoltageCompSaturation(12);

    //INNER INTAKE STUFF:
    innerIntakeTalon.enableVoltageCompensation(true);
    innerIntakeTalon.configVoltageCompSaturation(12);

    //OUTER INTAKE STUFF:
    outerIntakeTalon.enableVoltageCompensation(true);
    outerIntakeTalon.configVoltageCompSaturation(12);

    //CLIMBER STUFF:
    climberTalon2.follow(climberTalon1);
    climberTalon1.setInverted(true);

    climberTalon1.enableVoltageCompensation(true);
    climberTalon1.configVoltageCompSaturation(12);

    //SOLENOID STUFF:

    //COMPRESSOR STUFF:
    compressor.start();

    //TIME OF FLIGHT:
    // timeOfFlight1.setRangingMode();
    // timeOfFlight2.setRangingMode();
    // timeOfFlight3.setRangingMode();
    // timeOfFlight4.setRangingMode();
  }

  @Override
  public void testInit() {
  }

  @Override
  public void testPeriodic() {
  }

}
