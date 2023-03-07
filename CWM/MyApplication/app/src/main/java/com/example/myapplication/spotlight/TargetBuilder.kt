package com.example.myapplication.spotlight

import android.graphics.PointF
import android.graphics.drawable.shapes.Shape
import android.media.effect.Effect
import android.view.View

/**
 * Target represents the spot that Spotlight will cast.
 */
class Target(
    val archor: PointF, //mantém duas coordenadas em float
    val shape: Shape,
    val effect: Effect,
    val overlay: View?,
    val listener: OnTargetListener
)

/**
 * [Builder] to build a [Target].
 * All parameters should be set in this [Builder].
 */
class Builder {

}