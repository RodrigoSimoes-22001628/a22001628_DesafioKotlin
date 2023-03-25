package pt.ulusofona.cm.kotlin.challenge.models

import pt.ulusofona.cm.kotlin.challenge.exceptions.AlterarPosicaoException

class Posicao(var x : Int, var y: Int) {
    fun alterarPosicao (x: Int, y: Int) {
        if (this.x == x && this.y == y) {
            throw AlterarPosicaoException()
        } else {
            this.x = x
            this.y = y
        }
    }
    override fun toString(): String {
        return "Posicao | x:$x | y:$y"
    }
}