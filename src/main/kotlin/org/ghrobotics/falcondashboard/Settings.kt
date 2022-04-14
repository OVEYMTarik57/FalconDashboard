package org.ghrobotics.falcondashboard

import com.github.salomonbrys.kotson.fromJson
import com.github.salomonbrys.kotson.registerTypeAdapter
import com.google.gson.GsonBuilder
import javafx.beans.property.DoubleProperty
import javafx.beans.property.SimpleBooleanProperty
import javafx.beans.property.SimpleDoubleProperty
import javafx.beans.property.SimpleStringProperty
import java.io.File
import java.io.FileReader
import java.io.FileWriter


object Settings {
    val name = SimpleStringProperty("Baseline")
    val reversed = SimpleBooleanProperty(false)
    val clampedCubic = SimpleBooleanProperty(true)
    val autoPathFinding = SimpleBooleanProperty(false)
    val robotWidth = SimpleDoubleProperty(Properties.kRobotWidth.value)
    val robotLength = SimpleDoubleProperty(Properties.kRobotLength.value)
    val startVelocity = SimpleDoubleProperty(0.0)
    val endVelocity = SimpleDoubleProperty(0.0)
    val maxVelocity = SimpleDoubleProperty(2.0)
    val maxAcceleration = SimpleDoubleProperty(1.5)
    val maxCentripetalAcceleration = SimpleDoubleProperty(2.0)
    val ip = SimpleStringProperty("127.0.1.1")
    val trajectoryTime = SimpleStringProperty("Trajectory Time (s): ")

    var mainString = SimpleStringProperty(null)
    //P
    var pPID = SimpleDoubleProperty(0.0)
    var pSliderMin = SimpleDoubleProperty(0.0)
    var pSliderMax = SimpleDoubleProperty(0.0)
    var fpSliderMin = SimpleDoubleProperty(0.0)
    var fpSliderMax = SimpleDoubleProperty(0.0)
    var isPSliderMin = SimpleStringProperty("New P Slider Min Value")
    var isPSliderMax = SimpleStringProperty("New P Slider Max Value")
    var isPSliderValue = SimpleStringProperty("New P Slider Value")


    //I
    var iPID = SimpleDoubleProperty(0.0)
    var iSliderMin = SimpleDoubleProperty(0.0)
    var iSliderMax = SimpleDoubleProperty(0.0)
    var fISliderMin = SimpleDoubleProperty(0.0)
    var fISliderMax = SimpleDoubleProperty(0.0)
    var isISliderMin = SimpleStringProperty("New I Slider Min Value")
    var isISliderMax = SimpleStringProperty("New I Slider Max Value")
    var isISliderValue = SimpleStringProperty("New I Slider Value")

    //D
    var dPID = SimpleDoubleProperty(0.0)
    var dSliderMin = SimpleDoubleProperty(0.0)
    var dSliderMax = SimpleDoubleProperty(0.0)
    var fDSliderMin = SimpleDoubleProperty(0.0)
    var fDSliderMax = SimpleDoubleProperty(0.0)
    var isDSliderMin = SimpleStringProperty("New D Slider Min Value")
    var isDSliderMax = SimpleStringProperty("New D Slider Max Value")
    var isDSliderValue = SimpleStringProperty("New D Slider Value")

    //Reset
    var isSlidersReset = SimpleStringProperty("Slider Values is Reset")

    val ipNetwork = SimpleStringProperty("127.0.0.1")

    private val gson = GsonBuilder().registerTypeAdapter<Settings> {
        write {
            beginArray()
            value(it.reversed.value)
            value(it.clampedCubic.value)
            value(it.autoPathFinding.value)
            value(it.startVelocity.value)
            value(it.endVelocity.value)
            value(it.maxVelocity.value)
            value(it.maxAcceleration.value)
            value(it.maxCentripetalAcceleration.value)
            value(it.ip.value)
            value(it.trajectoryTime.value)
            value(it.pPID.value)
            value(it.iPID.value)
            value(it.dPID.value)
            value(it.pSliderMin.value)
            value(it.pSliderMax.value)
            value(it.iSliderMin.value)
            value(it.iSliderMax.value)
            value(it.dSliderMin.value)
            value(it.dSliderMax.value)
            value(it.fpSliderMin.value)
            value(it.fpSliderMax.value)
            value(it.fISliderMin.value)
            value(it.fISliderMax.value)
            value(it.fDSliderMin.value)
            value(it.fDSliderMax.value)

            value(it.ipNetwork.value)
            endArray()
        }
        read {
            beginArray()
            reversed.set(nextBoolean())
            clampedCubic.set(nextBoolean())
            autoPathFinding.set(nextBoolean())
            startVelocity.set(nextDouble())
            endVelocity.set(nextDouble())
            maxVelocity.set(nextDouble())
            maxAcceleration.set(nextDouble())
            maxCentripetalAcceleration.set(nextDouble())
            ip.set(nextString())
            trajectoryTime.set(nextString())
            pPID.set(nextDouble())
            iPID.set(nextDouble())
            dPID.set(nextDouble())
            pSliderMin.set(nextDouble())
            pSliderMax.set(nextDouble())
            iSliderMax.set(nextDouble())
            iSliderMin.set(nextDouble())
            dSliderMin.set(nextDouble())
            dSliderMax.set(nextDouble())
            fpSliderMin.set(nextDouble())
            fpSliderMax.set(nextDouble())
            fISliderMax.set(nextDouble())
            fISliderMin.set(nextDouble())
            fDSliderMin.set(nextDouble())
            fDSliderMax.set(nextDouble())

            ipNetwork.set(nextString())
            endArray()
            return@read Settings
        }
    }.create()!!

    init {
        val file = File("settings.json")
        if (file.exists()) {
            try {
                gson.fromJson<Settings>(FileReader(file))
            } catch (e: Exception) {
                file.delete()
                val writer = FileWriter(file)
                writer.write(gson.toJson(Settings))
                writer.close()
            }
        } else {
            val writer = FileWriter(file)
            writer.write(gson.toJson(Settings))
            writer.close()
        }
    }

    fun save() {
        val writer = FileWriter(File("settings.json"))
        writer.write(gson.toJson(Settings))
        writer.close()
    }
}

