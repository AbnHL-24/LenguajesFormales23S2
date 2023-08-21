package model.analizador.lexico

import model.clasesextension.StringUtil.Companion.normalizarSaltosDeLinea
import model.analizador.lexico.estados.Estados
import model.analizador.lexico.token.TipoToken
import model.analizador.lexico.token.Token
import model.analizador.lexico.transiciones.s1.S1S2
import model.analizador.lexico.transiciones.s1.S1S7

/**
 * Esta clase se encarga del analizador lexíco.
 */
class AnalizadorAFD {
    private var estado: Estados = Estados.S1
    private val listaDeTokens: MutableList<Token> = mutableListOf()

    //Las cuatro variables siguientes serán parte de cada objeto
    private var tokenActual: String = ""
    private var tipoDeToken: TipoToken = TipoToken.NULL
    private var fila: Int = 1
    private var columna: Int = 0

    fun  generarTokens(entradaString: String): MutableList<Token>{
        listaDeTokens.clear()

        estado = Estados.S1
        //Las cuatro variables siguientes serán parte de cada objeto
        tokenActual = ""
        tipoDeToken = TipoToken.NULL
        fila = 1
        columna = 0

        //normalizamos los saltos de linea a "\n"
        val entrada: String = entradaString.normalizarSaltosDeLinea()
        //creamos la lista de Chars y agregamos un espacio al final
        val entradaChars: MutableList<Char> = entrada.toMutableList()
        entradaChars.add(' ')

        for (char in entradaChars) {
            if (char == '\n') {
                fila++
                columna = 1
            } else columna++



            //Si es posible usar el when
            when(estado) {
                Estados.S1 -> estadoS1(char)
                Estados.S2 -> estadoS2(char)
                Estados.S3 -> estadoS3(char)
                Estados.S4 -> estadoS4(char)
                Estados.S5 -> estadoS5(char)
                Estados.S6 -> estadoS6(char)
                Estados.S7 -> estadoS7(char)
                Estados.S8 -> estadoS8(char)
                Estados.S9 -> estadoS9(char)
                Estados.S10 -> estadoS10(char)
                Estados.S11 -> estadoS11(char)
                Estados.S12 -> estadoS12(char)
                Estados.S13 -> estadoS13(char)
                Estados.S14 -> estadoS14(char)
                Estados.S15 -> estadoS15(char)
                Estados.S16 -> estadoS16(char)
                Estados.S17 -> estadoS17(char)
                Estados.S18 -> estadoS18(char)
            }
        }

        return listaDeTokens
    }

    /**
     * Función correspondiente al estado inicial S1
     */
    private fun estadoS1(char: Char) {
        if (S1S2.isTransicionS1S2(char)) {
            estado = Estados.S2
            tokenActual += char.toString()
            tipoDeToken = TipoToken.isOtros(char)
        } else if (char == '#') {
            estado = Estados.S4
            tokenActual += char.toString()
            tipoDeToken = TipoToken.COMENTARIO
        }else if (char == '"') {
            estado = Estados.S3
            tokenActual += char.toString()
            tipoDeToken = TipoToken.CADENA
        } else if (char == '\'') {
            estado = Estados.S6
            tokenActual += char.toString()
            tipoDeToken = TipoToken.CADENA
        } else if (S1S7.isTransicionS1S7(char)) {
            estado = Estados.S7
            tokenActual += char.toString()
            tipoDeToken = TipoToken.isSignosSimples(char)
        }
    }

    /**
     * Función correspondiente al estado de aceptación S2
     */
    private fun estadoS2(char: Char) {
        agregarToken()
    }

    /**
     * Función correspondiente al estado S3 de cadenas con comilla doble;
     */
    private fun estadoS3(char: Char) {
        if (char == '"') {
            estado = Estados.S2
            tokenActual += char.toString()
            tipoDeToken = TipoToken.CADENA
        } else if (char == '\n') {
            estado = Estados.S2
            tokenActual += char.toString()
            tipoDeToken = TipoToken.ERROR
        } else {
            tokenActual += char.toString()
            //No es necesario modificar el estado ni el tipo de token.
        }
    }

    /**
     * Función correspondiente a los comentarios.
     */
    private fun estadoS4(char: Char) {
        if (char == '\n') {
            agregarToken()
        }
        else {
            tokenActual += char.toString()
        }
    }
    private fun estadoS5(char: Char) {}

    /**
     * Función correspondiente al estado S6 de cadena de comilla simple
     */
    private fun estadoS6(char: Char) {
        if (char == '\'') {
            estado = Estados.S2
            tokenActual += char.toString()
            tipoDeToken = TipoToken.CADENA
        } else if (char == '\n') {
            estado = Estados.S2
            tokenActual += char.toString()
            tipoDeToken = TipoToken.ERROR
        } else {
            tokenActual += char.toString()
            //No es necesario modificar el estado ni el tipo de token.
        }
    }

    /**
     * Función corresponiente al estado S7 que puede aceptar signos simples o agregarles un =.
     */
    private fun estadoS7(char: Char) {
        if (char == '=') {
            estado = Estados.S2
            tokenActual += char.toString()
            tipoDeToken = TipoToken.isSignosCompuestos(tipoDeToken)
        } else {
            agregarToken()
        }
    }
    private fun estadoS8(char: Char) {}
    private fun estadoS9(char: Char) {}
    private fun estadoS10(char: Char) {}
    private fun estadoS11(char: Char) {}
    private fun estadoS12(char: Char) {}
    private fun estadoS13(char: Char) {}
    private fun estadoS14(char: Char) {}
    private fun estadoS15(char: Char) {}
    private fun estadoS16(char: Char) {}
    private fun estadoS17(char: Char) {}
    private fun estadoS18(char: Char) {}

    /**
     * Restablece lo necesario para entrar nuevamente en el estado 1.
     */
    private fun restablecerAS1() {
        estado = Estados.S1
        tokenActual = ""
        tipoDeToken = TipoToken.NULL
    }

    /**
     * Genera un nuevo token, lo agrega a la lista de tokens y restablece a S1.
     */
    private fun agregarToken() {
        val tokenAceptado = Token (tokenActual, tipoDeToken, fila, columna)
        listaDeTokens.add(tokenAceptado)
        restablecerAS1()
    }
}