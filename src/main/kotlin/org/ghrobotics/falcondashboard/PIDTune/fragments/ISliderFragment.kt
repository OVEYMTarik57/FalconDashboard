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
import org.ghrobotics.falcondashboard.Settings.iSliderMax
import org.ghrobotics.falcondashboard.Settings.iSliderMin
import org.ghrobotics.falcondashboard.Settings.iPID
import org.ghrobotics.falcondashboard.Settings.mainString


class ISliderFragment : Fragment() {
    override val root = vbox { }



    init {
        with(root) {
            title = "I Slider Interval"

            paddingAll = 50

            createNumericalEntry("I Min", iSliderMin).addEventHandler(KeyEvent.KEY_PRESSED){
                mainString.set("Changed: " + Settings.isISliderMin.value+ " " + iSliderMin.value)
                if(iSliderMin.value > iPID.value){
                    iPID.value = iSliderMin.value
                }
                if(iPID.value == Double.NaN){
                    iPID.value = iSliderMin.value
                }


            }
            createNumericalEntry("I Max", iSliderMax).addEventHandler(KeyEvent.KEY_PRESSED){
                mainString.set("Changed: " + Settings.isISliderMax.value+ " " + iSliderMax.value)
                if(iSliderMax.value < Settings.iPID.value){
                    iPID.value = iSliderMax.value
                }
                if(iPID.value == Double.MAX_VALUE){
                    iPID.value = iSliderMax.value
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