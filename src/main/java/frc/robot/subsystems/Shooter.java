package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import static edu.wpi.first.units.Units.*;

import com.ctre.phoenix6.CANBus;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.wpilibj2.command.Command;
import yams.mechanisms.velocity.FlyWheel;
import yams.mechanisms.config.FlyWheelConfig;
import yams.gearing.GearBox;
import yams.gearing.MechanismGearing;
import yams.motorcontrollers.SmartMotorController;
import yams.motorcontrollers.SmartMotorControllerConfig;
import yams.motorcontrollers.SmartMotorControllerConfig.ControlMode;
import yams.motorcontrollers.SmartMotorControllerConfig.MotorMode;
import yams.motorcontrollers.SmartMotorControllerConfig.TelemetryVerbosity;
import yams.motorcontrollers.remote.TalonFXWrapper;
import yams.telemetry.SmartMotorControllerTelemetryConfig;

public class Shooter extends SubsystemBase{

    //Telemetry config
    SmartMotorControllerTelemetryConfig shooterTelemetryConfig = new SmartMotorControllerTelemetryConfig()
    .withMechanismPosition()
    .withMechanismVelocity()
    .withRotorPosition()
    .withRotorVelocity()
    .withOutputVoltage()
    .withStatorCurrent()
    .withTelemetryVerbosity(TelemetryVerbosity.HIGH);

    //Motor Controller config
    private SmartMotorControllerConfig shooterSMCConfig = new SmartMotorControllerConfig(this)
    .withControlMode(ControlMode.CLOSED_LOOP)
    .withClosedLoopController(10,0,0,DegreesPerSecond.of(360),DegreesPerSecondPerSecond.of(180))
    .withTelemetry("Shooter Motor", shooterTelemetryConfig)
    .withGearing(new MechanismGearing(GearBox.fromStages("1:1")))
    .withMotorInverted(false)
    .withIdleMode(MotorMode.COAST)
    .withStatorCurrentLimit(Amps.of(30))
    .withClosedLoopRampRate(Seconds.of(.25));

    //Talon init
    private TalonFX shooterTalonFX = new TalonFX(1, new CANBus());

    //SMC init
    private SmartMotorController shooterSMC = new TalonFXWrapper(shooterTalonFX, DCMotor.getKrakenX60(1), shooterSMCConfig);

    //Shooter FlyWheel config
    private final FlyWheelConfig shooterFlyWheelConfig = new FlyWheelConfig()
    .withSmartMotorController(shooterSMC)
    .withDiameter(Inches.of(4))
    .withMass(Pounds.of(1.54))
    .withUpperSoftLimit(RPM.of(5300))
    .withTelemetry("Fly Wheel", TelemetryVerbosity.HIGH);

    //Shooter Mechanism init
    private FlyWheel shooter = new FlyWheel(shooterFlyWheelConfig);


    public Shooter() {}

    public Command set(double dutycycle) {return shooter.set(dutycycle);}

    @Override
    public void periodic(){shooter.updateTelemetry();}

}
