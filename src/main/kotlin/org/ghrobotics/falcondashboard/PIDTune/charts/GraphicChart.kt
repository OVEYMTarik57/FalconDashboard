package org.ghrobotics.falcondashboard.PIDTune.charts

import edu.wpi.first.wpilibj.trajectory.Trajectory
import javafx.scene.chart.LineChart
import javafx.scene.chart.NumberAxis
import javafx.scene.paint.Color
import javafx.scene.paint.Paint
import org.ghrobotics.falcondashboard.generator.GeneratorView
import tornadofx.MultiValue
import tornadofx.data
import tornadofx.style
import kotlin.math.abs

object GraphicChart : LineChart<Number, Number>(NumberAxis(), NumberAxis()) {


    init {
        style {
            backgroundColor = MultiValue(arrayOf<Paint>(Color.LIGHTGRAY))
        }

        setMinSize(54 * 25.0, 27 * 25.0)

        axisSortingPolicy = SortingPolicy.NONE
        isLegendVisible = false
        createSymbols = false
        animated = false

        
    }

    
}