package org.ghrobotics.falcondashboard.PIDTune.fragments

import edu.wpi.first.wpilibj.geometry.Rotation2d
import javafx.beans.property.SimpleDoubleProperty
import javafx.scene.input.KeyEvent
import org.ghrobotics.falcondashboard.Settings
import org.ghrobotics.falcondashboard.Settings.fISliderMax
import org.ghrobotics.falcondashboard.Settings.fISliderMin
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

    val x = SimpleDoubleProperty(0.0)
    val y = SimpleDoubleProperty(0.0)
    val a = SimpleDoubleProperty(0.0)

    init {
        with(root) {
            title = "I Slider Interval"

            paddingAll = 50

            createNumericalEntry("P Min", fISliderMin).addEventHandler(KeyEvent.KEY_PRESSED){
                iSliderMin.value = fISliderMin.value
                mainString.set("Changed: " + Settings.isISliderMin.value+ " " + iSliderMin.value)
                if(fISliderMin.value > iPID.value){
                    iPID.value = fISliderMin.value
                }

            }
            createNumericalEntry("P Max", fISliderMax).addEventHandler(KeyEvent.KEY_PRESSED){
                iSliderMax.value = fISliderMax.value
                mainString.set("Changed: " + Settings.isISliderMax.value+ " " + iSliderMax.value)
                if(fISliderMax.value < Settings.iPID.value){
                    iPID.value = fISliderMax.value
                }

            }


            button {
                text = "Add"
                prefWidth = 100.0
                action {
                    close().run {
                        iSliderMin.value = fISliderMin.value
                        iSliderMax.value = fISliderMax.value
                    }
                }
            }
        }
    }
}