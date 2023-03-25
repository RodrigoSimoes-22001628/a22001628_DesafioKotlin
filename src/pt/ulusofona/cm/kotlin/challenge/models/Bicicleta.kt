package pt.ulusofona.cm.kotlin.challenge.models

class Bicicleta(override val identificador : String): Veiculo(identificador){
    override fun requerCarta(): Boolean {
        return false
    }
    override fun toString(): String {
        return "Bicicleta | $identificador | ${getDataAquisicao()} | ${getPosicao().toString()}"
    }
}