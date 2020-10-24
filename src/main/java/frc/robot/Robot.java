/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.playingwithfusion.TimeOfFlight;
import com.playingwithfusion.TimeOfFlight.RangingMode;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;

import frc.robot.JoystickButtons;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  //**************** */
  //DRIVEBASE MOTORS
  //**************** */
  //Drivebase Motor-related (corresponds to "OPENing" the TalonFX motors in LABView):
  WPI_TalonFX driveTalonLeft = new WPI_TalonFX(1);
  WPI_TalonFX driveTalonLeftFollow = new WPI_TalonFX(2);
  WPI_TalonFX driveTalonRight = new WPI_TalonFX(3);
  WPI_TalonFX driveTalonRightFollow = new WPI_TalonFX(4);
  //idk how to instantiate SpeedController, SpeedController constructor doesn't exist
  SpeedControllerGroup leftMotors = new SpeedControllerGroup(driveTalonLeft, driveTalonLeftFollow);
  SpeedControllerGroup rightMotors = new SpeedControllerGroup(driveTalonRight, driveTalonRightFollow);

  RobotDrive twoMotorDrive = new RobotDrive(leftMotors, rightMotors);

  //Other Motors:
  TalonFX shooterTalon = new TalonFX(5);
  TalonFX shooterTalonFollow = new TalonFX(6);

  TalonFX acceleratorTalon = new TalonFX(7);
  TalonFX conveyorTalon = new TalonFX(8);
  
  TalonFX innerIntakeTalon = new TalonFX(9);
  TalonFX outerIntakeTalon = new TalonFX(10);

  TalonFX climberTalon = new TalonFX(1);
  TalonFX climberTalonFollow = new TalonFX(2);

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
 
  //Whatever DIO is:
  //Gyro:
  AnalogGyro gyro = new AnalogGyro(0);

  //Whisker:

  //Creating an instance of the JoystickButtons class so we can use the method in the class:
  JoystickButtons joystickButtons = new JoystickButtons();

  //Variables that were originally in Global Vars.vi
  boolean fireStatus = false;
  boolean aimStatus = false;
  boolean fire = false;
  boolean intakeReverse = false;
  boolean shooterSideForward = false;
  boolean sensor1Override = false;
  boolean autoDrive = false;
  boolean autoDriveStatus = false;
  boolean limelightLEDStatus = false;

  int distanceL = -7823;
  int distanceR = 7823;
  int limelightDistance = 0;
  int autoShooterSpeedDistance = 0;
  int manualShooterSpeed = 0;
  int travelDistance = 7823;
  int RMPosition = 0;
  int LMPosition = 0;
  // I don't know what this "Movement Selector" variable is, so I'm just gonna guess its a string?
  String movementSelector = "Teleop";

  // ????????????????????????????????????????????????????????
  // JOYSTICK BUTTONS
  // ????????????????????????????????????????????????????????

  //Left joystick buttons:
  boolean leftTrigger = leftStick.getRawButton(0);
  boolean leftThumbMain = leftStick.getRawButton(1);
  boolean leftThumbLeft = leftStick.getRawButton(2);
  boolean leftThumbRight = leftStick.getRawButton(3);
  boolean leftRightArrayTR = leftStick.getRawButton(4);
  boolean leftRightArrayTM = leftStick.getRawButton(5);
  boolean leftRightArrayTL = leftStick.getRawButton(6);
  boolean leftRightArrayBL = leftStick.getRawButton(7);
  boolean leftRightArrayBM = leftStick.getRawButton(8);
  boolean leftRightArrayBR = leftStick.getRawButton(9);
  boolean leftLeftArrayTL = leftStick.getRawButton(10);
  boolean leftLeftArrayTM = leftStick.getRawButton(11);
  boolean leftLeftArrayTR = leftStick.getRawButton(12);
  boolean leftLeftArrayBR = leftStick.getRawButton(13);
  boolean leftLeftArrayBM = leftStick.getRawButton(14);
  boolean leftLeftArrayBL = leftStick.getRawButton(15);

  //Right joystick buttons:
  boolean rightTrigger = rightStick.getRawButton(0);
  boolean rightThumbMain = rightStick.getRawButton(1);
  boolean rightThumbLeft = rightStick.getRawButton(2);
  boolean rightThumbRight = rightStick.getRawButton(3);
  boolean rightLeftArrayTL = rightStick.getRawButton(4);
  boolean rightLeftArrayTM = rightStick.getRawButton(5);
  boolean rightLeftArrayTR = rightStick.getRawButton(6);
  boolean rightLeftArrayBR = rightStick.getRawButton(7);
  boolean rightLeftArrayBM = rightStick.getRawButton(8);
  boolean rightLeftArrayBL = rightStick.getRawButton(9);
  boolean rightRightArrayTR = rightStick.getRawButton(10);
  boolean rightRightArrayTM = rightStick.getRawButton(11);
  boolean rightRightArrayTL = rightStick.getRawButton(12);
  boolean rightRightArrayBL = rightStick.getRawButton(13);
  boolean rightRightArrayBM = rightStick.getRawButton(14);
  boolean rightRightArrayBR = rightStick.getRawButton(15);

  //Axes Left:
  double leftX = 0;
  double leftY = 0;
  double leftZ = 0;
  double leftSlider = 0;
  //Axis Right:
  double rightX = 0;
  double rightY = 0;
  double rightZ = 0;
  double rightSlider = 0;

  // ????????????????????????????????????????????????????????
  // GLOBAL VARS
  // ????????????????????????????????????????????????????????

  // ????????????????????????????????????????????????????????
  // GLOBAL REFNUM
  // ????????????????????????????????????????????????????????

  /**
   * This function is run when the robot is first started up and should be used
   * for any initialization code.
   */
  @Override
  public void robotInit() {
    // **************************
    // TELEOP SETUP
    // **************************
    joystickButtons.initializeJoystickButtons();
    //DRIVEBASE TALON STUFF:
    //Talon 2 follows talon 1, talon 4 follows talon 3
    driveTalonLeftFollow.follow(driveTalonLeft);
    driveTalonRightFollow.follow(driveTalonRight);

    //SpeedControllerGroup leftMotors = new SpeedControllerGroup(driveTalonLeft, driveTalonLeftFollow);

    //These two lines to help set PercentOutput:
    double leftStickValue = leftStick.getRawAxis(0);
    double rightStickValue = rightStick.getRawAxis(0);

    //Corresponds to "MC SET B/C MODE" (can't figure out what NeutralMode options there are)
    driveTalonLeft.setNeutralMode(NeutralMode.Brake);
    driveTalonRight.setNeutralMode(NeutralMode.Brake);

    //Set percent output for talon1 and talon3 (talon 2 and 4 follow 1 and 3, respectively)
    driveTalonLeft.set(ControlMode.PercentOutput, leftStickValue);
    driveTalonRight.set(ControlMode.PercentOutput, rightStickValue);
  
    //Corresponds to "MC DEAD BAND" in LABView (guess)
    driveTalonLeft.configNeutralDeadband(.1);
    driveTalonRight.configNeutralDeadband(.1);

    //Corresponds to "MC SET SENSOR POS" in LABView (guess)
    driveTalonLeft.setSelectedSensorPosition(0);
    driveTalonRight.setSelectedSensorPosition(0);

    //Missing code for "MC CONFIG PIDF"


    //SHOOTER TALON STUFF:
    shooterTalonFollow.follow(shooterTalon);
    shooterTalonFollow.setInverted(true);
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
    climberTalonFollow.follow(climberTalon);
    climberTalon.setInverted(true);

    climberTalon.enableVoltageCompensation(true);
    climberTalon.configVoltageCompSaturation(12);

    //SOLENOID STUFF:
    //intakePistonSolenoid.setSolenoidChannel?

    //COMPRESSOR STUFF:
    compressor.start();

    //TIME OF FLIGHT:
    timeOfFlight1.setRangingMode(RangingMode.Short, 0);
    timeOfFlight2.setRangingMode(RangingMode.Short, 0);
    timeOfFlight3.setRangingMode(RangingMode.Short, 0);
    timeOfFlight4.setRangingMode(RangingMode.Short, 0);
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
    // **************************
    // TELEOP
    // **************************

    if (rightThumbLeft) {
      shooterSideForward = false;
    } else {
      if (rightThumbRight) {
        shooterSideForward = true;
      } else {
        //nothing
      }
    }

    if (rightRightArrayBM) {
      //Limelight code
    } else {
      if (leftThumbMain) {
        //More limelight code
      } else {
        if (shooterSideForward) {
          //Guessing code:
        }
      }
    }

    //****** */
    //PERIODIC TASKS
    //****** */
    if (rightRightArrayTL) {
      climberTalon.set(ControlMode.PercentOutput, .7);
    } else {
      climberTalon.set(ControlMode.PercentOutput, 0);
    }

    //
    // INTAKE CODE
    //
    if (leftTrigger) {
      innerIntakeTalon.set(ControlMode.PercentOutput, .4);
      conveyorTalon.set(ControlMode.PercentOutput, .6);
    } else {
      if (tof1SeesBall() && tof4SeesBall()) {
        innerIntakeTalon.set(ControlMode.PercentOutput, 0);
      } else {
        if (rightTrigger && !leftLeftArrayBL) {
          innerIntakeTalon.set(ControlMode.PercentOutput, 1);
        } else if (rightTrigger && leftLeftArrayBL) {
          innerIntakeTalon.set(ControlMode.PercentOutput, -1);
        } else {
          innerIntakeTalon.set(ControlMode.PercentOutput, 0);
        }
      }

      if (leftLeftArrayBL) {
        if (((tof2SeesBall()||tof3SeesBall()) && !tof4SeesBall())||rightRightArrayTR) {
          conveyorTalon.set(ControlMode.PercentOutput, -1);
        } else {
          conveyorTalon.set(ControlMode.PercentOutput, 0);
        }
      } else {
        if (((tof2SeesBall()||tof3SeesBall()) && !tof4SeesBall())||rightRightArrayTR) {
          conveyorTalon.set(ControlMode.PercentOutput, .6);
        } else {
          conveyorTalon.set(ControlMode.PercentOutput, 0);
        }
      }
    }

    if ((leftLeftArrayBL||leftThumbLeft)||outerIntakeTalon.getStatorCurrent()>160) {
      if (rightTrigger) {
        outerIntakeTalon.set(ControlMode.PercentOutput, -1);
        intakePistonSolenoid.set(true);
      } else {
        outerIntakeTalon.set(ControlMode.PercentOutput, 0);
        intakePistonSolenoid.set(false);
      }
    } else {
      if (rightTrigger) {
        outerIntakeTalon.set(ControlMode.PercentOutput, .65);
        intakePistonSolenoid.set(true);
      } else {
        outerIntakeTalon.set(ControlMode.PercentOutput, 0);
        intakePistonSolenoid.set(false);
      }
    }

  }

  ///////////////////////
  // HELPER METHODS FOR PERIODIC TASKS
  //////////////////////
  public boolean tof1SeesBall() {
    if (timeOfFlight1.getRange() < 110) {
      return true;
    } else {
      return false;
    }
  }

  public boolean tof2SeesBall() {
    if (timeOfFlight2.getRange() < 100) {
      return true;
    } else {
      return false;
    }
  }

  public boolean tof3SeesBall() {
    if (timeOfFlight3.getRange() < 100) {
      return true;
    } else {
      return false;
    }
  }

  public boolean tof4SeesBall() {
    if (timeOfFlight4.getRange() < 110) {
      return true;
    } else {
      return false;
    }
  }

  





  @Override
  public void testInit() {
  }

  @Override
  public void testPeriodic() {
  }

}
