package pt.ulusofona.cm.kotlin.challenge.models
import pt.ulusofona.cm.kotlin.challenge.exceptions.MenorDeIdadeException
import pt.ulusofona.cm.kotlin.challenge.exceptions.PessoaSemCartaException
import pt.ulusofona.cm.kotlin.challenge.exceptions.VeiculoNaoEncontradoException
import java.time.LocalDate
import java.time.Period

class Pessoa (private val nome : String, private val dataNascimento: LocalDate){
    private val veiculos : MutableList<Veiculo> = mutableListOf()
    private var carta: Carta? = null
    private var posicao: Posicao = Posicao(0,0)

    fun comprarVeiculo(veiculo: Veiculo) {
        veiculos.add(veiculo)
    }
    fun pesquisarVeiculo(identificador: String) : Veiculo {
        for(veiculo : Veiculo in veiculos) {
            if(veiculo.getIdentificador().equals(identificador)) {
                return veiculo
            }
        }
        throw VeiculoNaoEncontradoException()
    }
    fun venderVeiculo(identificador: String, comprador : Pessoa) {
        var veiculo : Veiculo = pesquisarVeiculo(identificador)
        veiculo.setDataAquisicao()
        comprador.veiculos.add(veiculo)
        veiculos.remove(veiculo)
    }
    fun moverVeiculoPara(identificador: String, x: Int, y: Int) {
        var veiculo : Veiculo = pesquisarVeiculo(identificador)
        if (veiculo.requerCarta() && this.carta == null) {
            throw PessoaSemCartaException(nome)
        } else {
            veiculo.getPosicao().alterarPosicao(x,y)
        }
    }
    fun temCarta() : Boolean{
        return this.carta != null
    }
    fun tirarCarta() {
        val from = LocalDate.parse(dataNascimento.toString())
        val to = LocalDate.parse(LocalDate.now().toString())
        if(Period.between(from,to).years >= 18) {
            this.carta = Carta()
        } else {
            throw MenorDeIdadeException()
        }
    }

    override fun toString(): String {
        return "Pessoa | $nome | $dataNascimento | ${posicao.toString()}"
    }
}