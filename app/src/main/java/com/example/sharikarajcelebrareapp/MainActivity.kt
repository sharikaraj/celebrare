package com.example.sharikarajcelebrareapp

import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import yuku.ambilwarna.AmbilWarnaDialog


class MainActivity : AppCompatActivity() {
    private lateinit var writingView: WritingView
    private lateinit var fontSize: SeekBar
    private var defaultColor: Int = Color.WHITE

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // writingView = WritingView(this)

        val editText = findViewById<EditText>(R.id.editText)
        writingView = findViewById<WritingView>(R.id.writingView)
        //  val changeSize = findViewById<Button>(R.id.btnChangeSize)
        val changeColor = findViewById<Button>(R.id.btnChangeColor)
        val addText = findViewById<Button>(R.id.addText)
        fontSize = findViewById(R.id.fontSizeProgress)
        val textList = writingView.getAllTextList()


        editText.setOnClickListener {

        }

        editText.setOnEditorActionListener { _, _, _ ->
            val inputText = editText.text.toString()
            writingView.setText(inputText)
            true
        }

        // changeSize.setOnClickListener { }

        changeColor.setOnClickListener {

            openColorPicker()

        }

        addText.setOnClickListener {

            val newText = editText.text.toString()
            val x = 100f
            val y = 100f
            val textColor = Color.BLUE
            writingView.addText(newText, x, y, textColor)
            editText.text.clear()


            // val newTextIndex = writingView.textList.size - 1
            // writingView.textList[newTextIndex].setOnClickListener {
            //  openColorPicker(newTextIndex)}

            fontSize.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(
                    seekBar: SeekBar?,
                    progress: Int,
                    fromUser: Boolean
                ) {
                    val newTextSize = progress.toFloat()
                    writingView.setTextSize(newTextSize)
                }

                override fun onStartTrackingTouch(seekBar: SeekBar?) {

                }

                override fun onStopTrackingTouch(seekBar: SeekBar?) {

                }


            })

        }
    }





    private fun openColorPicker() {
        val ambilWarnaDialog = AmbilWarnaDialog(this, defaultColor,
            object : AmbilWarnaDialog.OnAmbilWarnaListener {
                override fun onOk(dialog: AmbilWarnaDialog?, color: Int) {
                    val newTextIndex = writingView.textList.size - 1 // Index of the newly added text
                    writingView.setTextColor(newTextIndex, color) // Set text color of the newly added text
                }

                override fun onCancel(dialog: AmbilWarnaDialog?) {
                    // Do nothing if color selection is canceled
                }
            }
        )
        ambilWarnaDialog.show()
    }
}
