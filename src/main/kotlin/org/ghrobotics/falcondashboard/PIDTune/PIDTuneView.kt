package org.ghrobotics.falcondashboard.PIDTune

import edu.wpi.first.wpilibj.geometry.Rotation2d
import edu.wpi.first.wpilibj.trajectory.TrajectoryConfig
import edu.wpi.first.wpilibj.trajectory.TrajectoryGenerator
import edu.wpi.first.wpilibj.trajectory.constraint.CentripetalAccelerationConstraint
import edu.wpi.first.wpilibj.util.Units
import javafx.beans.property.SimpleObjectProperty
import javafx.geometry.Pos
import javafx.scene.layout.Priority
import javafx.scene.paint.Color
import javafx.stage.StageStyle
import kfoenix.jfxbutton
import kfoenix.jfxcheckbox
import kfoenix.jfxtabpane
import kfoenix.jfxtextfield
import org.ghrobotics.falcondashboard.Settings.autoPathFinding
import org.ghrobotics.falcondashboard.Settings.clampedCubic
import org.ghrobotics.falcondashboard.Settings.endVelocity
import org.ghrobotics.falcondashboard.Settings.maxAcceleration
import org.ghrobotics.falcondashboard.Settings.maxCentripetalAcceleration
import org.ghrobotics.falcondashboard.Settings.maxVelocity
import org.ghrobotics.falcondashboard.Settings.name
import org.ghrobotics.falcondashboard.Settings.reversed
import org.ghrobotics.falcondashboard.Settings.startVelocity
import org.ghrobotics.falcondashboard.Settings.pPID
import org.ghrobotics.falcondashboard.Settings.iPID
import org.ghrobotics.falcondashboard.Settings.dPID
import org.ghrobotics.falcondashboard.createNumericalEntry
import org.ghrobotics.falcondashboard.generator.charts.PositionChart
import org.ghrobotics.falcondashboard.generator.charts.VelocityChart
import org.ghrobotics.falcondashboard.generator.fragments.CodeFragment
import org.ghrobotics.falcondashboard.generator.fragments.KtCodeFragment
import org.ghrobotics.falcondashboard.generator.fragments.WaypointFragment
import org.ghrobotics.falcondashboard.generator.tables.WaypointsTable
import org.ghrobotics.lib.mathematics.epsilonEquals
import org.ghrobotics.lib.mathematics.twodim.geometry.Pose2d
import org.ghrobotics.lib.mathematics.twodim.trajectory.FalconTrajectoryConfig
import org.ghrobotics.lib.mathematics.twodim.trajectory.optimization.PathFinder
import org.ghrobotics.lib.mathematics.units.derived.acceleration
import org.ghrobotics.lib.mathematics.units.derived.velocity
import org.ghrobotics.lib.mathematics.units.feet
import tornadofx.*

import org.ghrobotics.falcondashboard.livevisualizer.charts.FieldChart
import org.ghrobotics.falcondashboard.PIDTune.charts.PIDTuneChart
import org.ghrobotics.falcondashboard.PIDTune.charts.GraphicChart









class PIDTuneView : View() {

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
            createNumericalEntry("Value of I (PID)", iPID)
            createNumericalEntry("Value of D (PID)", dPID)

           /*  jfxcheckbox {
                paddingAll = 5
                text = "Reversed"
                bind(reversed)
            }
            jfxcheckbox {
                paddingAll = 5
                text = "Clamped Cubic"
                bind(clampedCubic)
            }
            jfxcheckbox {
                paddingAll = 5
                text = "Auto Path Finding (Experimental)"
                bind(autoPathFinding)
            }

            createNumericalEntry("Start Velocity (f/s)", startVelocity)
            createNumericalEntry("End Velocity (f/s)", endVelocity)
            createNumericalEntry("Max Velocity (f/s)", maxVelocity)
            createNumericalEntry("Max Acceleration (f/s/s)", maxAcceleration)
            createNumericalEntry("Max Centripetal Acceleration (f/s/s)", maxCentripetalAcceleration)

            this += WaypointsTable

            vbox {
                spacing = 5.0
                jfxbutton {
                    prefWidth = 290.0
                    text = "Add Waypoint"
                    action {
                        find<WaypointFragment>().openModal(stageStyle = StageStyle.UTILITY)
                    }
                }
                jfxbutton {
                    prefWidth = 290.0
                    text = "Remove Waypoint"
                    action {
                        WaypointsTable.removeSelectedItemIfPossible()
                    }
                }
                jfxbutton {
                    prefWidth = 290.0
                    text = "Load from text"
                    action {
                        object : Fragment() {
                            override val root = vbox {

                                prefWidth = 600.0
                                prefHeight = 200.0

                                val textEntryArea = textarea {
                                    prefWidth = 290.0

                                    isWrapText = true

                                    text = "                        Pose2d(11.75.feet, 25.689.feet, 0.0.degrees),\n" +
                                            "                        Pose2d(20.383.feet, 18.592.feet, (-68).degrees)"
                                }
                                jfxbutton {
                                    prefWidth = 290.0
                                    text = "Load from text"
                                    action {
                                        WaypointsTable.loadFromText(textEntryArea.text)
                                    }
                                }
                            }
                        }.openModal(stageStyle = StageStyle.UTILITY)
                    }
                }
                jfxbutton {
                    prefWidth = 290.0
                    text = "Generate JSON"
                    action {
                        find<CodeFragment>().openModal(stageStyle = StageStyle.UTILITY)
                    }
                }
                jfxbutton {
                    prefWidth = 290.0
                    text = "Generate Code"
                    action {
                        find<KtCodeFragment>().openModal(stageStyle = StageStyle.UTILITY)
                    }
                }
            } */
        } 
        jfxtabpane {
            maxWidth = Double.MAX_VALUE
            hgrow = Priority.ALWAYS
            style {
                backgroundColor = multi(Color.LIGHTGRAY)
            }
            tab("Position") {
                hbox {
                    alignment = Pos.CENTER_LEFT
                    add(PIDTuneChart)
                }
                isClosable = false
            }
            tab("Graphic") {
                hbox {
                    alignment = Pos.CENTER_LEFT
                    add(GraphicChart)
                }
                isClosable = false
            }
            
            
        }
    }

    
        
    

    
}