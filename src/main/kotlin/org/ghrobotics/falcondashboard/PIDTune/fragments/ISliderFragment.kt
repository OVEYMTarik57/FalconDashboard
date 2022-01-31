package org.ghrobotics.falcondashboard.PIDTune.fragments

import edu.wpi.first.wpilibj.geometry.Rotation2d
import javafx.beans.property.SimpleDoubleProperty
import org.ghrobotics.falcondashboard.Settings.iSliderMax
import org.ghrobotics.falcondashboard.Settings.iSliderMin
import org.ghrobotics.falcondashboard.createNumericalEntry
import org.ghrobotics.falcondashboard.generator.GeneratorView
import org.ghrobotics.lib.mathematics.twodim.geometry.Pose2d
import org.ghrobotics.lib.mathematics.units.meters
import tornadofx.*


class ISliderFragment : Fragment() {
    override val root = vbox { }

    val x = SimpleDoubleProperty(0.0)
    val y = SimpleDoubleProperty(0.0)
    val a = SimpleDoubleProperty(0.0)

    init {
        with(root) {
            title = "I Slider Interval"

            paddingAll = 50

            createNumericalEntry("I Min", iSliderMin)
            createNumericalEntry("I Max", iSliderMax)


            button {
                text = "Add"
                prefWidth = 100.0
                action {
                    GeneratorView.waypoints.add(Pose2d(x.value.meters, y.value.meters, Rotation2d.fromDegrees(a.value)))
                    close()
                }
            }
        }
    }
}