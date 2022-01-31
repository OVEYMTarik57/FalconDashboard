package org.ghrobotics.falcondashboard.PIDTune.fragments

import javafx.beans.property.SimpleDoubleProperty
import javafx.scene.control.TableView
import org.ghrobotics.falcondashboard.Settings.pSliderMax
import org.ghrobotics.falcondashboard.Settings.pSliderMin
import org.ghrobotics.falcondashboard.createNumericalEntry
import tornadofx.*


class PSliderFragment : Fragment() {
    override val root = vbox { }

    val x = SimpleDoubleProperty(0.0)
    val y = SimpleDoubleProperty(0.0)
    val a = SimpleDoubleProperty(0.0)





    init {
        with(root) {
            title = "P Slider Interval"

            paddingAll = 50

            createNumericalEntry("P Min", pSliderMin)
            createNumericalEntry("P Max", pSliderMax)


            button {
                text = "Add"
                prefWidth = 100.0
                action {

                }
            }
        }
    }
}