package org.ghrobotics.falcondashboard.PIDTune


//import org.ghrobotics.falcondashboard.Settings.pPIDSlider

import edu.wpi.first.networktables.NetworkTable
import edu.wpi.first.networktables.NetworkTableEntry
import edu.wpi.first.networktables.NetworkTableInstance
import javafx.geometry.Pos
import javafx.scene.control.Label
import javafx.scene.control.Slider
import javafx.scene.layout.Priority
import javafx.scene.paint.Color
import javafx.stage.StageStyle
import kfoenix.jfxtabpane
import kfoenix.jfxtextfield
import org.ghrobotics.falcondashboard.PIDTune.charts.VelocityGraphicChart
import org.ghrobotics.falcondashboard.PIDTune.charts.PIDTuneChart

import org.ghrobotics.falcondashboard.PIDTune.fragments.DSliderFragment
import org.ghrobotics.falcondashboard.PIDTune.fragments.ISliderFragment
import org.ghrobotics.falcondashboard.PIDTune.fragments.PSliderFragment
import org.ghrobotics.falcondashboard.Settings.dPID
import org.ghrobotics.falcondashboard.Settings.dSliderMax
import org.ghrobotics.falcondashboard.Settings.dSliderMin
import org.ghrobotics.falcondashboard.Settings.iPID
import org.ghrobotics.falcondashboard.Settings.iSliderMax
import org.ghrobotics.falcondashboard.Settings.iSliderMin
import org.ghrobotics.falcondashboard.Settings.ipNetwork
import org.ghrobotics.falcondashboard.Settings.pPID
import org.ghrobotics.falcondashboard.Settings.pSliderMax
import org.ghrobotics.falcondashboard.Settings.pSliderMin
import org.ghrobotics.falcondashboard.createNumericalEntry
import tornadofx.*



class PIDTuneView : View() {

    var pSlider: Slider by singleAssign()
    var iSlider: Slider by singleAssign()
    var dSlider: Slider by singleAssign()



    /*
    YAPILACAKLAR

    Minler maxlardan küçük olmalı
    Sliderda değiştirilen değer numerical entryde görünmüyor
    Networktables çalışıyor mu kontrol edilecek



    */


    val a: Number = 0

    val l = Label(" ")

    override val root = hbox {
        stylesheets += resources["/GeneratorStyle.css"]
         vbox {
            style {
                paddingAll = 20.0
                maxWidth = 300.px
                spacing = 5.px
            }

            hbox {
                paddingAll = 5
                jfxtextfield {
                   // bind(name)
                    prefWidth = 290.0
                }
            }



            createNumericalEntry("Value of P (PID)", pPID)
             hbox(spacing = 5.0){
                 label("P")
                 pSlider = slider(min = pSliderMin.value, max = pSliderMax.value, value = pPID.value)

             }
             button {
                 prefWidth = 270.0
                 text = "P Slider Interval"
                 action {
                     find<PSliderFragment>().openModal(stageStyle = StageStyle.UTILITY)
                 }
             }


             createNumericalEntry("Value of I (PID)", iPID)
             hbox(spacing = 5.0){
                 label("I")
                 iSlider = slider(min = iSliderMin.value, max = iSliderMax.value, value = iPID.value)
             }
             button {
                 prefWidth = 270.0
                 text = "I Slider Interval"
                 action {
                     find<ISliderFragment>().openModal(stageStyle = StageStyle.UTILITY)
                 }
             }


            createNumericalEntry("Value of D (PID)", dPID)
             hbox(spacing = 5.0){
                 label("")
                 dSlider = slider(min = dSliderMin.value, max = dSliderMax.value, value = dPID.value)
             }
             button {
                 prefWidth = 270.0
                 text = "D Slider Interval"
                 action {
                     find<DSliderFragment>().openModal(stageStyle = StageStyle.UTILITY)
                 }
             }

             button {
                 prefWidth = 270.0
                 text = "Restart PID Value"
                 action {
                     pPID.value = 0.0
                     iPID.value = 0.0
                     dPID.value = 0.0
                 }
             }


             /*
             imageview("FalconDashboard/src/main/resources/mascot.jpg") {

                 scaleX = .50
                 scaleY = .50
             }*/












             val falcon = NetworkTableInstance.create()
             var p: NetworkTableEntry
             var i: NetworkTableEntry
             var d: NetworkTableEntry
             val inst = NetworkTableInstance.getDefault()
             val table: NetworkTable = falcon.getTable("falcon-dashboard")

             falcon.startClient(ipNetwork.value)
             p = table.getEntry("p")
             i = table.getEntry("i")
             d = table.getEntry("d")

             fun pValue(): Double {
                 return p.getDouble(pPID.value)
             }
             fun iValue(): Double {
                 return i.getDouble(iPID.value)
             }
             fun dValue(): Double {
                 return d.getDouble(dPID.value)
             }


        } 
        jfxtabpane {
            maxWidth = Double.MAX_VALUE
            hgrow = Priority.ALWAYS
            style {
                backgroundColor = multi(Color.LIGHTGRAY)
            }
            /*tab("Map") {
                hbox {
                    alignment = Pos.CENTER_LEFT
                    add(PIDTuneChart)
                }
                isClosable = false
            }*/
            tab("Graphic") {
                hbox {
                    alignment = Pos.CENTER_LEFT
                    add(VelocityGraphicChart)
                }
                isClosable = false
            }

            
            
        }
    }


}






