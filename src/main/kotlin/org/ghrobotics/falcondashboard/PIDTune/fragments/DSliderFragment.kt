package org.ghrobotics.falcondashboard.PIDTune.fragments

import edu.wpi.first.wpilibj.geometry.Rotation2d
import javafx.beans.property.SimpleDoubleProperty
import javafx.scene.input.KeyEvent
import org.ghrobotics.falcondashboard.PIDTune.PIDTuneView
import org.ghrobotics.falcondashboard.Settings
import org.ghrobotics.falcondashboard.createNumericalEntry
import org.ghrobotics.falcondashboard.generator.GeneratorView
import org.ghrobotics.lib.mathematics.twodim.geometry.Pose2d
import org.ghrobotics.lib.mathematics.units.meters
import tornadofx.*
import org.ghrobotics.falcondashboard.Settings.dSliderMax
import org.ghrobotics.falcondashboard.Settings.dSliderMin
import org.ghrobotics.falcondashboard.Settings.dPID
import org.ghrobotics.falcondashboard.Settings.isDSliderMax
import org.ghrobotics.falcondashboard.Settings.isDSliderMin
import org.ghrobotics.falcondashboard.Settings.mainString


class DSliderFragment : Fragment() {
    override val root = vbox { }



    init {
        with(root) {
            title = "D Slider Interval"

            paddingAll = 50

            createNumericalEntry("D Min", dSliderMin).addEventHandler(KeyEvent.KEY_PRESSED){
                mainString.set("Changed: " + isDSliderMin.value+ " " + dSliderMin.value)
                if(dSliderMin.value > dPID.value){
                    dPID.value = dSliderMin.value
                }
                if(dPID.value == Double.NaN){
                    dPID.value = dSliderMin.value
                }

            }
            createNumericalEntry("D Max", dSliderMax).addEventHandler(KeyEvent.KEY_PRESSED){
                mainString.set("Changed: " + isDSliderMax.value+ " " + dSliderMax.value)
                if(dSliderMax.value < dPID.value){
                    dPID.value = dSliderMax.value
                }
                if(dPID.value == Double.MAX_VALUE){
                    dPID.value = dSliderMax.value
                }

            }


            button {
                text = "Add"
                prefWidth = 100.0
                action {
                    close().run {

                    }
                }
            }
        }
    }
}