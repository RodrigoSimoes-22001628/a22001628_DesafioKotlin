package pt.ulusofona.cm.kotlin.challenge.models
import pt.ulusofona.cm.kotlin.challenge.exceptions.MenorDeIdadeException
import pt.ulusofona.cm.kotlin.challenge.exceptions.PessoaSemCartaException
import pt.ulusofona.cm.kotlin.challenge.exceptions.VeiculoNaoEncontradoException
import pt.ulusofona.cm.kotlin.challenge.interfaces.Movimentavel
import java.text.SimpleDateFormat
import java.util.*


class Pessoa(val nome: String, val  dataNascimento: Date) : Movimentavel {
    var veiculos : MutableList<Veiculo> = mutableListOf()
    var carta: Carta? = null
    var posicao: Posicao = Posicao(0, 0)

    fun comprarVeiculo(veiculo: Veiculo) {
        veiculos.add(veiculo)
    }

    fun pesquisarVeiculo(identificador: String): Veiculo? {
        val veiculo = veiculos.find { it.identificador == identificador }
        if (veiculo != null){
            return veiculo
        }else{
            throw VeiculoNaoEncontradoException()
        }
    }

    fun venderVeiculo(identificador: String, comprador: Pessoa) {
        val veiculo = pesquisarVeiculo(identificador)
        if (veiculo != null) {
            veiculo.setDataAquisicao()
            veiculos.remove(veiculo)
            comprador.comprarVeiculo(veiculo)
        }
    }

    fun moverVeiculoPara(identificador: String, x: Int, y: Int) {
        val veiculo = pesquisarVeiculo(identificador) ?: throw VeiculoNaoEncontradoException()
        if (veiculo.requerCarta()) {
            if (temCarta()){
                moverPara(x,y)
                veiculo.moverPara(x, y)
            }else{
                throw PessoaSemCartaException(nome)
            }
        }else{
            moverPara(x,y)
            veiculo.moverPara(x, y)
        }
    }

    fun temCarta(): Boolean {
        return this.carta != null
    }

    fun tirarCarta(){
        val idade = calculaIdade(dataNascimento)
        if (idade >= 18) {
            this.carta = Carta()
        } else {
            throw MenorDeIdadeException()
        }
    }

    fun calculaIdade(dataDeNascimento: Date): Int {
        val hoje = Calendar.getInstance()
        val dataNascimento = Calendar.getInstance()
       dataNascimento.time = dataDeNascimento
        var idade = hoje.get(Calendar.YEAR) - dataNascimento.get(Calendar.YEAR)
        if (hoje.get(Calendar.DAY_OF_YEAR) < dataNascimento.get(Calendar.DAY_OF_YEAR)) {
            idade--
        }
        return idade
    }

    override fun moverPara(x: Int, y: Int) {
        posicao.alterarPosicaoPara(x, y)
    }

     fun dataFormatada(): String {
        val formato = SimpleDateFormat("dd-MM-yyyy")
        val data = formato.format(dataNascimento)
        return data.toString()
    }

    override fun toString(): String {
        return "Pessoa | $nome | ${dataFormatada()} | ${posicao}"
    }
}