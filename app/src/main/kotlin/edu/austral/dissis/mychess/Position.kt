package edu.austral.dissis.mychess

data class Position(val x: Int, val y: Int){
    fun a(position: Position) : Int{
        val (x, y) = position
        return x + y
    }
}