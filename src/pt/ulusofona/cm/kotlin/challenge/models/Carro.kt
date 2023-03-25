package pt.ulusofona.cm.kotlin.challenge.models

class Carro(override val identificador : String, val motor : Motor) : Veiculo(identificador){
    override fun requerCarta(): Boolean {
        return true
    }
    override fun toString(): String {
        return "Carro | $identificador | ${getDataAquisicao()} | ${getPosicao().toString()}"
    }
}