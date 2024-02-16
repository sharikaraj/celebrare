package com.example.sharikarajcelebrareapp

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PointF
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import yuku.ambilwarna.AmbilWarnaDialog

class WritingView (context: Context, attrs: AttributeSet?) : View(context, attrs) {

    private val paint = Paint().apply {
        color = Color.BLACK
        textSize = 50f
    }

    private val textList = mutableListOf<TextData>()

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        textList.forEach { textData ->
            paint.color = textData.color
            canvas.drawText(textData.text, textData.x, textData.y, paint)
        }


    }





    override fun onTouchEvent(event: MotionEvent): Boolean {
            when (event.action) {
          MotionEvent.ACTION_DOWN -> {
              val touchX = event.x
              val touchY = event.y
  
              for (textData in textList) {
                  if (touchX >= textData.x && touchX <= textData.x + paint.measureText(textData.text)
                      && touchY >= textData.y - paint.textSize && touchY <= textData.y
                  ) {
                      textData.touchOffsetX = touchX - textData.x
                      textData.touchOffsetY = touchY - textData.y
                      textData.isBeingDragged = true
                      return true
                  }
              }
          }
          MotionEvent.ACTION_MOVE -> {
              val touchX = event.x
              val touchY = event.y
              textList.forEach { textData ->
                  if (textData.isBeingDragged) {
                      textData.x = touchX - textData.touchOffsetX
                      textData.y = touchY - textData.touchOffsetY
                      invalidate()
                      return true
  
                  }
              }
          }
  
          MotionEvent.ACTION_UP -> {
              textList.forEach { it.isBeingDragged = false }
          }
      }
  
  
  
  
      return super.onTouchEvent(event)
  }


      fun setText(newText: String,  color: Int = Color.BLACK) {
        textList.clear()
        addText(newText, 100f, 100f, color)


    }



    fun addText(newText: String, x: Float, y: Float,color: Int) {
        textList.add(TextData(newText, x, y, color))
        invalidate()
    }

    fun setTextSize(newTextSize: Float) {
        paint.textSize = newTextSize
        invalidate()

    }

    fun setTextColor(textIndex: Int, color: Int) {

        if (textIndex >= 0 && textIndex < textList.size) {
            textList[textIndex].color = color
            paint.color = color
            invalidate()
        }
    }


    private data class TextData(
        val text: String,
        var x: Float,
        var y: Float,
        var color: Int,
        var touchOffsetX: Float = 0f,
        var touchOffsetY: Float = 0f,
        var isBeingDragged: Boolean = false
    )
    }


