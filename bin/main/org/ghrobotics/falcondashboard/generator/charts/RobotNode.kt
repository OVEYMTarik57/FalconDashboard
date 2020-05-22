package org.ghrobotics.falcondashboard.generator.charts

import edu.wpi.first.wpilibj.geometry.Rotation2d
import javafx.beans.property.ReadOnlyDoubleProperty
import javafx.beans.property.ReadOnlyProperty
import javafx.beans.property.SimpleDoubleProperty
import javafx.scene.layout.StackPane
import javafx.scene.paint.Color
import org.ghrobotics.falcondashboard.Properties
import org.ghrobotics.falcondashboard.Settings
import org.ghrobotics.falcondashboard.generator.GeneratorView
import org.ghrobotics.falcondashboard.mapprop
import org.ghrobotics.lib.mathematics.units.inMeters
import org.ghrobotics.lib.mathematics.units.meters
import tornadofx.*

open class RobotNode(
    private val robotRotationProperty: ReadOnlyProperty<Rotation2d>,
    scaleProperty: ReadOnlyDoubleProperty
) : StackPane() {

    val robotRotation = SimpleDoubleProperty()

    fun bindRobotRotation() {
        robotRotation
            .bind(mapprop<Rotation2d, Number>(
                robotRotationProperty
            ) { (-value).degrees })
    }

    val robotPane = object : StackPane() {
        init {
            style {
                backgroundColor = multi(Color.TRANSPARENT)
                borderColor = multi(box(Color.BLUE))
                borderRadius = multi(box(0.5.em))
                borderWidth = multi(box(0.25.em))
            }
            rotateProperty().bind(robotRotation)
            bindRobotRotation()
            usePrefHeight = true
            usePrefWidth = true
            prefHeightProperty()
                .bind(scaleProperty.multiply(Settings.robotWidth.value.meters.inMeters()))
            prefWidthProperty()
                .bind(scaleProperty.multiply(Settings.robotLength.value.meters.inMeters()))
        }
    }

    init {
        children.add(robotPane)
    }

}