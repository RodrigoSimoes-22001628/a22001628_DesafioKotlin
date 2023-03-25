package pt.ulusofona.cm.kotlin.challenge.models
import pt.ulusofona.cm.kotlin.challenge.exceptions.MenorDeIdadeException
import pt.ulusofona.cm.kotlin.challenge.exceptions.PessoaSemCartaException
import pt.ulusofona.cm.kotlin.challenge.exceptions.VeiculoNaoEncontradoException
import pt.ulusofona.cm.kotlin.challenge.interfaces.Movimentavel
import java.text.SimpleDateFormat
import java.util.*
import java.time.LocalDate
import java.time.Period
import java.time.ZoneId


class Pessoa(val nome: String, val dataDeNascimento: Date) : Movimentavel {
    val veiculos : MutableList<Veiculo> = mutableListOf()
    var carta: Carta? = null
    var posicao: Posicao = Posicao(0, 0)

    fun comprarVeiculo(veiculo: Veiculo) {
        veiculos.add(veiculo)
    }

    fun pesquisarVeiculo(identificador: String): Veiculo? {
        return veiculos.find { it.identificador == identificador }
    }

    fun venderVeiculo(identificador: String, comprador: Pessoa) {
        val veiculo = pesquisarVeiculo(identificador)
        if (veiculo != null) {
            veiculos.remove(veiculo)
            comprador.comprarVeiculo(veiculo)
        }
    }

    fun moverVeiculoPara(identificador: String, x: Int, y: Int) {
        val veiculo = pesquisarVeiculo(identificador) ?: throw VeiculoNaoEncontradoException()
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
        val nascimento = LocalDate.ofInstant(dataDeNascimento.toInstant(), ZoneId.systemDefault())
        val idade = Period.between(nascimento, LocalDate.now()).years
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