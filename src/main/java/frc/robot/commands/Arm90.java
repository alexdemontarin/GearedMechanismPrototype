// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import static edu.wpi.first.units.Units.Degrees;
//import static edu.wpi.first.units.Units.Angle;

import java.lang.management.ManagementFactory;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ArmSubsystem;

/** An example command that uses an example subsystem. */
public class Arm90 extends Command {
  @SuppressWarnings("PMD.UnusedPrivateField")
  private final ArmSubsystem subsystem;
  private final double angle;

  /**
   * CONSTRUCTOR
   * Creates a new Arm90.
   *
   * @param subsystem The subsystem used by this command.
   */
  public Arm90(ArmSubsystem subsystem, double angle) {
    this.subsystem = subsystem;
    this.angle = angle;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    System.out.println("Arm90 Init " + subsystem.getName());
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    System.out.println("Arm90 Exec " + angle);
    subsystem.setAngle(Degrees.of(angle)).schedule();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {subsystem.setAngle(Degrees.of(0)).schedule();
    }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
