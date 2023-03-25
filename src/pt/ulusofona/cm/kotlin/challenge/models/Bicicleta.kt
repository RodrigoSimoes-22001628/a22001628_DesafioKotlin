package pt.ulusofona.cm.kotlin.challenge.models
import java.text.SimpleDateFormat

class Bicicleta(identificador: String) : Veiculo(identificador) {
    override fun requerCarta(): Boolean {
        return false
    }

    private fun dataFormatada(): String {
        val formatoData = SimpleDateFormat("dd-MM-yyyy")
        val data = formatoData.format(dataDeAquisicao)
        return data.toString()
    }

    override fun moverPara(x: Int, y: Int) {
        posicao.alterarPosicaoPara(x, y)
    }

    override fun toString(): String {
        return "Bicicleta | $identificador | ${dataFormatada()} | $posicao"
    }
}