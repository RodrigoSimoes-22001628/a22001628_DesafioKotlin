package pt.ulusofona.cm.kotlin.challenge.models
import java.time.LocalDate

abstract class Veiculo (open val identificador : String){
    private var posicao : Posicao = Posicao(0,0)
    private var dataAquisicao : LocalDate = LocalDate.now()
    fun setDataAquisicao() {
        dataAquisicao = LocalDate.now()
    }
    fun getPosicao() = posicao
    fun getDataAquisicao() = dataAquisicao.toString()
    abstract fun requerCarta() : Boolean
}