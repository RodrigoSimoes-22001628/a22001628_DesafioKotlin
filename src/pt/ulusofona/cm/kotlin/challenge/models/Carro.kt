package pt.ulusofona.cm.kotlin.challenge.models
import java.text.SimpleDateFormat

class Carro(identificador: String, val motor: Motor) : Veiculo(identificador) {
    var ligado : Boolean = false

    fun ligar() = motor.ligar()


    fun desligar() = motor.desligar()


    fun estaLigado(): Boolean = motor.estaLigado()

    override fun moverPara(x: Int, y: Int) {
        posicao?.alterarPosicaoPara(x, y)
    }

    private fun dataFormatada(): String {
        val formato = SimpleDateFormat("dd-MM-yyyy")
        val data = formato.format(dataDeAquisicao)
        return data.toString()
    }

    override fun requerCarta(): Boolean = true

    override fun toString(): String {
        return "Carro | $identificador | ${dataFormatada()} | Posicao | $posicao"
    }
}