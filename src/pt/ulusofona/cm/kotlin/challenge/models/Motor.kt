package pt.ulusofona.cm.kotlin.challenge.models
import pt.ulusofona.cm.kotlin.challenge.exceptions.VeiculoLigadoException
import pt.ulusofona.cm.kotlin.challenge.exceptions.VeiculoDesligadoException
import pt.ulusofona.cm.kotlin.challenge.interfaces.Ligavel

class Motor(val cavalos: Int, val cilindrada: Int) : Ligavel {
    var ligado: Boolean = false

    override fun ligar() {
       ligado = if (ligado) throw  VeiculoLigadoException() else true
    }

    override fun desligar()  {
        ligado = if (!ligado) throw  VeiculoDesligadoException() else false
    }

    override fun estaLigado(): Boolean = ligado


    override fun toString(): String {
        return "Motor | $cavalos | $cilindrada"
    }
}