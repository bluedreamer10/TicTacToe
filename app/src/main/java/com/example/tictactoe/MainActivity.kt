package com.example.tictactoe

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() , View.OnClickListener {
    var PLAYER = true
    var TURN_COUNT = 0
    var boardStatus = Array(3){IntArray(3)}

    lateinit var board : Array<Array<Button>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        board = arrayOf(arrayOf(button1,button2,button3) , arrayOf(button4,button5,button6)
            , arrayOf(button7,button8,button9))

        for (i:Array<Button> in board){
            for(button: Button in i ){
                button.setOnClickListener(this)
            }
        }

        initializeBoardStatus()

        resetBtn.setOnClickListener{
            PLAYER = true
            TURN_COUNT = 0
            initializeBoardStatus()
            updateText("Player X's Turn")
        }
    }

    @SuppressLint("SetTextI18n")
    fun updateText(text : String){
        displayText.setText(text)
    }

    fun checkDraw() : Boolean{
        return TURN_COUNT == 9
    }

    private fun initializeBoardStatus() {
        for(i in 0..2)
            for(j in 0..2) {
                boardStatus[i][j] = -1
                board[i][j].isEnabled = true
                board[i][j].text = ""
            }
    }

    override fun onClick(itemView: View){
        when (itemView.id){
            R.id.button1 ->{
                updateValue(row = 0, column = 0 , player = PLAYER)
            }
            R.id.button2 ->{
                updateValue(row = 0, column = 1 , player = PLAYER)
            }
            R.id.button3 ->{
                updateValue(row = 0, column = 2 , player = PLAYER)
            }
            R.id.button4 ->{
                updateValue(row = 1, column = 0 , player = PLAYER)
            }
            R.id.button5 ->{
                updateValue(row = 1, column = 1 , player = PLAYER)
            }
            R.id.button6 ->{
                updateValue(row = 1, column = 2 , player = PLAYER)
            }
            R.id.button7 ->{
                updateValue(row = 2, column = 0 , player = PLAYER)
            }
            R.id.button8 ->{
                updateValue(row = 2, column = 1 , player = PLAYER)
            }
            R.id.button9 ->{
                updateValue(row = 2, column = 2 , player = PLAYER)
        }
        }
        changeColour()
        TURN_COUNT++
        PLAYER = !PLAYER
        if (!PLAYER) updateText("Player O's Turn")
        else updateText("Player X's Turn")

        //Condition to check Winner
        checkWinner()
        //Condition to check Draw!
        if (checkDraw()){
            updateText("Game Draw!")
        }
    }


    private fun checkWinner() {
        //Checking for horizontal Rows!
        for (i in 0..2) {
            if (boardStatus[i][0] == boardStatus[i][1] && boardStatus[i][1] == boardStatus[i][2]) {
                if (boardStatus[i][0] == 1) {
                    updateText("Player X won!")
                    disableButton()
                    break
                } else if (boardStatus[i][0] == 0) {
                    updateText("Player O won!")
                    disableButton()
                    break
                }
            }
        }

        //Checking for vertical rows!
        for (i in 0..2) {
            if (boardStatus[0][i] == boardStatus[1][i] && boardStatus[1][i] == boardStatus[2][i]) {
                if (boardStatus[0][i] == 1) {
                    updateText("Player X won!")
                    disableButton()
                    break
                } else if (boardStatus[0][i] == 0) {
                    updateText("Player O won!")
                    disableButton()
                    break
                }
            }
        }

        //Checking for Diagonals!
            if (boardStatus[0][0] == boardStatus[1][1] && boardStatus[2][2] == boardStatus[1][1]) {
                if (boardStatus[0][0] == 1) {
                 updateText("Player X won!")
                    disableButton()
                }

            if (boardStatus[0][2] == boardStatus[1][1] && boardStatus[2][0] == boardStatus[1][1] ){
                if (boardStatus[0][2] == 0) {
                    updateText("Player O won!")
                    disableButton()
                }
        }
    }
    }

    private fun disableButton() {
        for(i: Array<Button> in board){
            for(buttton: Button in i){
                buttton.isEnabled = false
            }
        }
    }

    private fun updateValue(row: Int, column: Int, player: Boolean) {
        var text : String? = null
        var value: Int? = null
        if (player) {
            value = 1
            text = "X"
        }
        else{
            value = 0
            text = "O"
        }


        board[row][column].isEnabled = false
        board[row][column].setText(text)

        boardStatus[row][column] = value

    }

    private fun changeColour(){
        for(i:Array<Button> in board){
            for(button: Button in i ){
                if (button.text == "X")
                    button.setTextColor(Color.parseColor("#F60606"))
                else button.setTextColor(Color.parseColor("#FF000000"))
            }
        }
    }
}