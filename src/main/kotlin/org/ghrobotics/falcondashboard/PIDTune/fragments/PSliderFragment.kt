package org.ghrobotics.falcondashboard.PIDTune.fragments

import javafx.beans.property.SimpleBooleanProperty
import javafx.beans.property.SimpleDoubleProperty
import javafx.scene.control.Slider
import javafx.scene.control.TableView
import javafx.scene.input.KeyEvent

import org.ghrobotics.falcondashboard.Settings.pSliderMax
import org.ghrobotics.falcondashboard.Settings.pSliderMin
import org.ghrobotics.falcondashboard.Settings.fpSliderMax
import org.ghrobotics.falcondashboard.Settings.fpSliderMin
import org.ghrobotics.falcondashboard.Settings.isPSliderMax
import org.ghrobotics.falcondashboard.Settings.pPID
import org.ghrobotics.falcondashboard.Settings.mainString
import org.ghrobotics.falcondashboard.Settings.isPSliderMin
import org.ghrobotics.falcondashboard.createNumericalEntry
import tornadofx.*


class PSliderFragment : Fragment() {
    override val root = vbox {

    }

    val x = SimpleDoubleProperty(0.0)
    val y = SimpleDoubleProperty(0.0)
    val a = SimpleDoubleProperty(0.0)

    init {
        with(root) {
            title = "P Slider Interval"

            paddingAll = 50

            createNumericalEntry("P Min", fpSliderMin).addEventHandler(KeyEvent.KEY_PRESSED){
                pSliderMin.value = fpSliderMin.value
                mainString.set("Changed: " + isPSliderMin.value+ " " + pSliderMin.value)
                if(fpSliderMin.value > pPID.value){
                    pPID.value = fpSliderMin.value + 1/100
                }

            }

            createNumericalEntry("P Max", fpSliderMax).addEventHandler(KeyEvent.KEY_PRESSED){
                pSliderMax.value = fpSliderMax.value
                mainString.set("Changed: " + isPSliderMax.value+ " " + pSliderMax.value)
                if(fpSliderMax.value < pPID.value){
                    pPID.value = fpSliderMax.value - 1/100
                }

            }


            button {
                text = "Add"
                prefWidth = 100.0
                action {
                    close().run {
                        pSliderMin.value = fpSliderMin.value
                        pSliderMax.value = fpSliderMax.value


                    }
                }
            }
        }
    }
}