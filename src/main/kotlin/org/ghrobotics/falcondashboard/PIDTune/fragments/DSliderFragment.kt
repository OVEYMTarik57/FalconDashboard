package org.ghrobotics.falcondashboard.PIDTune.fragments

import edu.wpi.first.wpilibj.geometry.Rotation2d
import javafx.beans.property.SimpleDoubleProperty
import javafx.scene.input.KeyEvent
import org.ghrobotics.falcondashboard.Settings
import org.ghrobotics.falcondashboard.createNumericalEntry
import org.ghrobotics.falcondashboard.generator.GeneratorView
import org.ghrobotics.lib.mathematics.twodim.geometry.Pose2d
import org.ghrobotics.lib.mathematics.units.meters
import tornadofx.*
import org.ghrobotics.falcondashboard.Settings.dSliderMax
import org.ghrobotics.falcondashboard.Settings.dSliderMin
import org.ghrobotics.falcondashboard.Settings.dPID
import org.ghrobotics.falcondashboard.Settings.fDSliderMax
import org.ghrobotics.falcondashboard.Settings.fDSliderMin
import org.ghrobotics.falcondashboard.Settings.isDSliderMax
import org.ghrobotics.falcondashboard.Settings.isDSliderMin
import org.ghrobotics.falcondashboard.Settings.mainString


class DSliderFragment : Fragment() {
    override val root = vbox { }

    val x = SimpleDoubleProperty(0.0)
    val y = SimpleDoubleProperty(0.0)
    val a = SimpleDoubleProperty(0.0)

    init {
        with(root) {
            title = "D Slider Interval"

            paddingAll = 50

            createNumericalEntry("P Min", fDSliderMin).addEventHandler(KeyEvent.KEY_PRESSED){
                dSliderMin.value = fDSliderMin.value
                mainString.set("Changed: " + isDSliderMin.value+ " " + dSliderMin.value)
                if(fDSliderMin.value > dPID.value){
                    dPID.value = fDSliderMin.value
                }

            }
            createNumericalEntry("P Max", fDSliderMax).addEventHandler(KeyEvent.KEY_PRESSED){
                dSliderMax.value = fDSliderMax.value
                mainString.set("Changed: " + isDSliderMax.value+ " " + dSliderMax.value)
                if(fDSliderMax.value < dPID.value){
                    dPID.value = fDSliderMax.value
                }

            }


            button {
                text = "Add"
                prefWidth = 100.0
                action {
                    close().run {
                        dSliderMin.value = fDSliderMin.value
                        dSliderMax.value = fDSliderMax.value
                    }
                }
            }
        }
    }
}