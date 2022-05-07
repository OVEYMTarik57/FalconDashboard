package org.ghrobotics.falcondashboard.PIDTune.fragments

import edu.wpi.first.wpilibj.trajectory.Trajectory
import javafx.beans.property.SimpleBooleanProperty
import javafx.beans.property.SimpleDoubleProperty
import javafx.scene.control.Slider
import javafx.scene.control.TableView
import javafx.scene.input.KeyEvent

import org.ghrobotics.falcondashboard.Settings.pSliderMax
import org.ghrobotics.falcondashboard.Settings.pSliderMin
import org.ghrobotics.falcondashboard.Settings.isPSliderMax
import org.ghrobotics.falcondashboard.Settings.pPID
import org.ghrobotics.falcondashboard.Settings.mainString
import org.ghrobotics.falcondashboard.Settings.isPSliderMin
import org.ghrobotics.falcondashboard.createNumericalEntry
import org.ghrobotics.falcondashboard.PIDTune.PIDTuneView
import org.ghrobotics.falcondashboard.Settings
import tornadofx.*


class PSliderFragment : Fragment() {
    override val root = vbox {


    }



    init {
        with(root) {
            title = "P Slider Interval"

            paddingAll = 50

            createNumericalEntry("P Min", pSliderMin).addEventHandler(KeyEvent.KEY_PRESSED){
                mainString.set("Changed: " + isPSliderMin.value+ " " + pSliderMin.value)
                if(pSliderMin.value > pPID.value){
                    pPID.value = pSliderMin.value
                }
                if(pPID.value == Double.NaN){
                    pPID.value = pSliderMin.value
                }


            }

            createNumericalEntry("P Max", pSliderMax).addEventHandler(KeyEvent.KEY_PRESSED){
                mainString.set("Changed: " + isPSliderMax.value+ " " + pSliderMax.value)
                if(pSliderMax.value < pPID.value){
                    pPID.value = pSliderMax.value
                }
                if(pPID.value == Double.MAX_VALUE){
                    pPID.value = pSliderMax.value
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