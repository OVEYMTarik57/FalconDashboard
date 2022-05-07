package org.ghrobotics.falcondashboard.PIDTune


//import org.ghrobotics.falcondashboard.Settings.pPIDSlider

import edu.wpi.first.networktables.NetworkTable
import edu.wpi.first.networktables.NetworkTableEntry
import edu.wpi.first.networktables.NetworkTableInstance
import javafx.geometry.Pos
import javafx.scene.control.Label
import javafx.scene.control.Slider
import javafx.scene.input.KeyEvent
import javafx.scene.input.MouseEvent
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
import org.ghrobotics.falcondashboard.Settings
import org.ghrobotics.falcondashboard.Settings.dPID
import org.ghrobotics.falcondashboard.Settings.dSliderMax
import org.ghrobotics.falcondashboard.Settings.dSliderMin

import org.ghrobotics.falcondashboard.Settings.iPID
import org.ghrobotics.falcondashboard.Settings.iSliderMax
import org.ghrobotics.falcondashboard.Settings.iSliderMin
import org.ghrobotics.falcondashboard.Settings.ipNetwork
import org.ghrobotics.falcondashboard.Settings.isDSliderValue
import org.ghrobotics.falcondashboard.Settings.isISliderValue
import org.ghrobotics.falcondashboard.Settings.isPSliderMax
import org.ghrobotics.falcondashboard.Settings.isPSliderMin
import org.ghrobotics.falcondashboard.Settings.isPSliderValue
import org.ghrobotics.falcondashboard.Settings.isSlidersReset
import org.ghrobotics.falcondashboard.Settings.pPID
import org.ghrobotics.falcondashboard.Settings.pSliderMax
import org.ghrobotics.falcondashboard.Settings.pSliderMin
import org.ghrobotics.falcondashboard.Settings.mainString
import org.ghrobotics.falcondashboard.createNumericalEntry
import org.ghrobotics.falcondashboard.generator.charts.PositionChart
import org.ghrobotics.lib.mathematics.units.meters
import tornadofx.*
import java.beans.EventHandler
import java.math.RoundingMode
import java.text.DecimalFormat


class PIDTuneView : View() {

    var pSlider: Slider by singleAssign()
    var iSlider: Slider by singleAssign()
    var dSlider: Slider by singleAssign()

    val df = DecimalFormat("#.###")




    /*
    YAPILACAKLAR

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

                    prefWidth = 290.0
                }
            }
             text("Change: ") {
                 alignment = Pos.CENTER_LEFT
                 bind(mainString)
             }

             df.roundingMode = RoundingMode.CEILING
            //P
            createNumericalEntry("Value of P (PID)", pPID).addEventHandler(KeyEvent.KEY_PRESSED){
                pSlider.value = pPID.value
                mainString.set("Changed: " + isPSliderValue.value+ " " + df.format(pPID.value))
            }
             hbox(spacing = 5.0){
                 label("P")
                 pSlider = slider(min = pSliderMin.value, max = pSliderMax.value, value = pPID.value)
                 pSlider.addEventHandler(MouseEvent.MOUSE_DRAGGED){
                     mainString.set("Changed: " + isPSliderValue.value+ " " + df.format(pPID.value))
                     pPID.value = pSlider.value
                     println(df.format(pPID.value))
                 }


             }

             button {
                 prefWidth = 270.0
                 text = "P Slider Interval"
                 action {
                     find<PSliderFragment>().openModal(stageStyle = StageStyle.UTILITY)
                 }
             }



              //I


             createNumericalEntry("Value of I (PID)", iPID).addEventHandler(KeyEvent.KEY_PRESSED){
                 iSlider.value = iPID.value
                 mainString.set("Changed: " + isISliderValue.value+ " " + df.format(iPID.value))
             }
             hbox(spacing = 5.0){
                 label("I")
                 iSlider = slider(min = iSliderMax.value, max = iSliderMin.value, value = iPID.value)
                 iSlider.addEventHandler(MouseEvent.MOUSE_DRAGGED){
                     mainString.set("Changed: " + isISliderValue.value+ " " + df.format(iPID.value))
                     iPID.value = iSlider.value
                     println(df.format(iPID.value))
                 }

             }
             button {
                 prefWidth = 270.0
                 text = "I Slider Interval"
                 action {
                     find<ISliderFragment>().openModal(stageStyle = StageStyle.UTILITY)
                 }
             }



             //D

             createNumericalEntry("Value of D (PID)", dPID).addEventHandler(KeyEvent.KEY_PRESSED){
                 dSlider.value = dPID.value
                 mainString.set("Changed: " + isDSliderValue.value+ " " + df.format(dPID.value))
             }
             hbox(spacing = 5.0){
                 label("")
                 dSlider = slider(min = dSliderMin.value, max = dSliderMax.value, value = dPID.value)
                 dSlider.addEventHandler(MouseEvent.MOUSE_DRAGGED){
                     mainString.set("Changed: " + isDSliderValue.value+ " " + df.format(dPID.value))
                     dPID.value = dSlider.value
                     println(df.format(dPID.value))
                 }
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
                    text = "Reset PID Value"
                    mainString.set("Changed: " + isSlidersReset.value)
                    action {
                        pPID.value = pSlider.min
                        if(pPID.value < pSlider.min){//PID aralığının dışında kalan değerler minimuma eşitlenir
                            pPID.value = pSlider.min
                            pSlider.value = pPID.value
                        }
                        pSlider.value = pSlider.min
                        iPID.value = iSlider.min
                        if(iPID.value < iSlider.min){
                            iPID.value = iSlider.min
                        }
                        iSlider.value = iSlider.min
                        dPID.value = dSlider.min
                        if(dPID.value < dSlider.min){
                            dPID.value = dSlider.min
                        }
                        dSlider.value = dSlider.min

                        //Aralıkları resetler
                        pSlider.min = pSliderMin.value
                        pSlider.max = pSliderMax.value
                        iSlider.min = iSliderMin.value
                        iSlider.max = iSliderMax.value
                        dSlider.min = dSliderMin.value
                        dSlider.max = dSliderMax.value
                    }
                }



             imageview("mascot300x300.png")

             text("*Note: Press reset PID Value when the sliders are NaN ") {
                 alignment = Pos.CENTER_LEFT
             }





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






