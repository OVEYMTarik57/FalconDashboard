package org.ghrobotics.falcondashboard.PIDTune.charts

import edu.wpi.first.wpilibj.geometry.Rotation2d
import javafx.beans.property.SimpleObjectProperty
import javafx.scene.chart.LineChart
import javafx.scene.chart.NumberAxis
import javafx.scene.control.Tooltip
import javafx.scene.input.MouseButton
import javafx.scene.paint.Color
import javafx.scene.paint.Paint
import org.ghrobotics.falcondashboard.generator.GeneratorView
import org.ghrobotics.falcondashboard.generator.charts.PositionChart.setOnMouseClicked
import org.ghrobotics.lib.mathematics.twodim.geometry.Pose2d
import org.ghrobotics.lib.mathematics.twodim.geometry.x_u
import org.ghrobotics.lib.mathematics.twodim.geometry.y_u
import org.ghrobotics.lib.mathematics.units.feet
import org.ghrobotics.lib.mathematics.units.inFeet
import tornadofx.MultiValue
import tornadofx.bind
import tornadofx.data
import tornadofx.style

/**
 * Chart that is used to display the field view for the trajectory
 * generator.
 */
object PIDTuneChart : LineChart<Number, Number>(
    NumberAxis(0.0, 54.0, 1.0),
    NumberAxis(0.0, 27.0, 1.0)
) {
    // Series
    private val seriesXY = Series<Number, Number>()
    private val seriesWayPoints = Series<Number, Number>()

    init {
        // Set styles
        style {
            backgroundColor = MultiValue(arrayOf<Paint>(Color.LIGHTGRAY))
        }
        lookup(".chart-plot-background").style +=
            "-fx-background-image: url(\"chart-background.png\");" +
                "-fx-background-size: stretch;" +
                "-fx-background-position: top right;" +
                "-fx-background-repeat: no-repeat;"

        axisSortingPolicy = SortingPolicy.NONE
        isLegendVisible = false
        animated = false
        createSymbols = true
        verticalGridLinesVisible = false
        isHorizontalGridLinesVisible = false

        data.add(seriesXY)
        data.add(seriesWayPoints)

        // Add waypoint on double click
        setOnMouseClicked {
            if (it.button == MouseButton.PRIMARY) {
                if (it.clickCount == 2) {
                    val plotX = xAxis.getValueForDisplay(xAxis.sceneToLocal(it.sceneX, it.sceneY).x)
                    val plotY = yAxis.getValueForDisplay(yAxis.sceneToLocal(it.sceneX, it.sceneY).y)
                    GeneratorView.waypoints.add(Pose2d(plotX.feet, plotY.feet, Rotation2d()))
                }
            }
        }

}

    override fun resize(width: Double, height: Double) = super.resize(height / 27 * 54, height) 
}