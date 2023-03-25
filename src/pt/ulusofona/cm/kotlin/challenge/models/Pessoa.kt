package pt.ulusofona.cm.kotlin.challenge.models
import pt.ulusofona.cm.kotlin.challenge.exceptions.MenorDeIdadeException
import pt.ulusofona.cm.kotlin.challenge.exceptions.PessoaSemCartaException
import pt.ulusofona.cm.kotlin.challenge.exceptions.VeiculoNaoEncontradoException
import pt.ulusofona.cm.kotlin.challenge.interfaces.Movimentavel
import java.text.SimpleDateFormat
import java.util.*

class Pessoa(val nome: String, val dataDeNascimento: Date) : Movimentavel {
    val veiculos: List<Veiculo> = listOf()
    var carta: Carta? = null
    var posicao: Posicao = Posicao(0, 0)

    fun comprarVeiculo(veiculo: Veiculo) {
        veiculos.plus(veiculo)
    }

    fun pesquisarVeiculo(identificador: String): Veiculo? {
        return veiculos.find { it.identificador == identificador }
    }

    fun venderVeiculo(identificador: String, comprador: Pessoa) {
        val veiculo = pesquisarVeiculo(identificador)
        if (veiculo != null) {
            veiculos.minus(veiculo)
            comprador.comprarVeiculo(veiculo)
        }
    }

    fun moverVeiculoPara(identificador: String, x: Int, y: Int) {
        val veiculo = pesquisarVeiculo(identificador)
        if (veiculo == null) {
            throw VeiculoNaoEncontradoException()
        }
        if (veiculo.requerCarta() && carta == null) {
            throw PessoaSemCartaException()
        }
        veiculo.moverPara(x, y)
    }

    fun temCarta(): Boolean {
        return carta != null
    }

    fun tirarCarta(dataDeNascimento: Date): Carta {
        val idade = calculaIdade(dataDeNascimento)
        if (idade >= 18) {
            return Carta()
        } else {
            throw MenorDeIdadeException()
        }
    }

    fun calculaIdade(dataDeNascimento: Date): Int {
        val diaAtual = Calendar.getInstance()
        val nascimento = Calendar.getInstance()
        nascimento.time = dataDeNascimento
        var idade = diaAtual.get(Calendar.YEAR) - nascimento.get(Calendar.YEAR)
        if (diaAtual.get(Calendar.DAY_OF_YEAR) < nascimento.get(Calendar.DAY_OF_YEAR)) {
            idade--
        }
        return idade
    }

    override fun moverPara(x: Int, y: Int) {
        posicao.alterarPosicaoPara(x, y)
    }

    private fun dataFormatada(): String {
        val formato = SimpleDateFormat("dd-MM-yyyy")
        val data = formato.format(dataDeNascimento)
        return data.toString()
    }


    override fun toString(): String {
        return "Pessoa | $nome | ${dataFormatada()} | ${posicao}"
    }
}