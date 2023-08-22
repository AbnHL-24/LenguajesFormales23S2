package model.analizador.lexico

import model.clasesextension.StringUtil.Companion.normalizarSaltosDeLinea
import model.clasesextension.CharUtil.Companion.isDigitNotZero
import model.analizador.lexico.estados.Estados
import model.analizador.lexico.palabras.Constantes
import model.analizador.lexico.palabras.Logicos
import model.analizador.lexico.palabras.PalabrasReservadas
import model.analizador.lexico.token.TipoToken
import model.analizador.lexico.token.Token
import model.analizador.lexico.transiciones.s1.S1S2
import model.analizador.lexico.transiciones.s1.S1S6

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

    fun  generarTokens(entradaString: String): ArrayList<Token>{
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
            }
        }

        return  ArrayList<Token>(listaDeTokens)
    }

    /**
     * Función correspondiente al estado inicial S1
     */
    private fun estadoS1(char: Char) {
        if (S1S2.isTransicionS1S2(char)) {
            estado = Estados.S2
            tokenActual += char.toString()
            tipoDeToken = TipoToken.isOtros(char)
        }else if (char == '"') {
            estado = Estados.S3
            tokenActual += char.toString()
            tipoDeToken = TipoToken.CADENA
        }  else if (char == '#') {
            estado = Estados.S4
            tokenActual += char.toString()
            tipoDeToken = TipoToken.COMENTARIO
        }else if (char == '\'') {
            estado = Estados.S5
            tokenActual += char.toString()
            tipoDeToken = TipoToken.CADENA
        } else if (S1S6.isTransicionS1S6(char)) {
            estado = Estados.S6
            tokenActual += char.toString()
            tipoDeToken = TipoToken.isSignosSimples(char)
        } else if (Char.Companion.isDigitNotZero(char)) {
            estado = Estados.S8
            tokenActual += char.toString()
            tipoDeToken = TipoToken.ENTERO
        } else if (char == '_') {
            estado = Estados.S9
            tokenActual += char.toString()
            tipoDeToken = TipoToken.IDENTIFICADOR
        } else if (char.isLetter()) {
            estado = Estados.S10
            tokenActual += char.toString()
            tipoDeToken = TipoToken.IDENTIFICADOR
        } else if (char == '!') {
            estado = Estados.S11
            tokenActual += char.toString()
            tipoDeToken = TipoToken.DIFERENTE
        }else if (char == '-') {
            estado = Estados.S13
            tokenActual += char.toString()
            tipoDeToken = TipoToken.RESTA
        } else if (char == '/') {
            estado = Estados.S14
            tokenActual += char.toString()
            tipoDeToken = TipoToken.DIVISION
        } else if (char == '0') {
            estado = Estados.S15
            tokenActual += char.toString()
            tipoDeToken = TipoToken.ENTERO
        } else if (char == '*') {
            estado = Estados.S16
            tokenActual += char.toString()
            tipoDeToken = TipoToken.MULTIPLICACION
        }
    }

    /**
     * Función correspondiente al estado de aceptación S2
     */
    private fun estadoS2(char: Char) {
        estadoTerminal(char)
    }

    /**
     * Función correspondiente al estado S3 de cadenas con comillas dobles;
     */
    private fun estadoS3(char: Char) {
        when (char) {
            '"' -> {
                estado = Estados.S2
                tokenActual += char.toString()
                tipoDeToken = TipoToken.CADENA
            }
            '\n' -> {
                estado = Estados.S2
                tokenActual += char.toString()
                tipoDeToken = TipoToken.ERROR
            }
            else -> {
                tokenActual += char.toString()
                //Sin cambios: estado = S3, tipoDeToken = CADENA
            }
        }
    }

    /**
     * Función correspondiente a los comentarios.
     */
    private fun estadoS4(char: Char) {
        if (char == '\n') {
            estado = Estados.S2
            tokenActual += char.toString()
            tipoDeToken = TipoToken.COMENTARIO
        }
        else {
            tokenActual += char.toString()
        }
    }

    /**
     * Función correspondiente al estado S6 de cadena de comillas simples
     */
    private fun estadoS5(char: Char) {
        when (char) {
            '\'' -> {
                estado = Estados.S2
                tokenActual += char.toString()
                tipoDeToken = TipoToken.CADENA
            }
            '\n' -> {
                estado = Estados.S2
                tokenActual += char.toString()
                tipoDeToken = TipoToken.ERROR
            }
            else -> {
                tokenActual += char.toString()
                //Sin cambios: estado = S6, tipoDeToken = CADENA
            }
        }
    }

    /**
     * Función correspondiente al estado S6 que puede aceptar signos simples o agregarles un =.
     */
    private fun estadoS6(char: Char) {
        if (char == '=') {
            estado = Estados.S2
            tokenActual += char.toString()
            tipoDeToken = TipoToken.isSignosCompuestos(tipoDeToken)
        } else {
            estadoTerminal(char)
        }
    }

    /**
     * Función dedicada al estado S7, recibe un entero o cero con un punto y verifica que continue otro dígito.
     */
    private fun estadoS7(char: Char) {
        if (char.isDigit()) {
            estado = Estados.S12
            tokenActual += char.toString()
            tipoDeToken = TipoToken.DECIMAL
        } else {
            estado = Estados.S2
            tokenActual += char.toString()
            tipoDeToken = TipoToken.ERROR
        }
    }

    /**
     * Función dedicada al estado S8, gestiona si un número es entero o se convierte en decimal.
     */
    private fun estadoS8(char: Char) {
        if (char.isDigit()) {
            tokenActual += char.toString()
            //Sin cambios: estado = S8, tipoDeToken = ENTERO
        } else if (char == '.') {
            estado = Estados.S7
            tokenActual += char.toString()
            tipoDeToken = TipoToken.DECIMAL
        } else {
            estadoTerminal(char)
        }
    }

    /**
     * Función correspondiente al estado S10 que verifica los guiones bajos para un identificador.
     */
    private fun estadoS9(char: Char) {
        if (char == '_'){
            tokenActual += char.toString()
            //Sin cambios: estado = S2, tipoDeToken = IDENTIFICADOR
        } else if (char.isLetterOrDigit()) {
            estado = Estados.S10
            tokenActual += char.toString()
            //Sin cambios: tipoDeTokenIDENTIFICADOR
        } else {
            estado = Estados.S2
            tokenActual += char.toString()
            tipoDeToken = TipoToken.ERROR
        }
    }

    /**
     * Función correspondiente al estado S11, sigue concatenando un identificador o lo acepta.
     */
    private fun estadoS10(char: Char) {
        if (char == '_' || char.isLetterOrDigit()){
            tokenActual += char.toString()
            //Sin cambios: estado = S2, tipoDeToken = IDENTIFICADOR
        } else {
            when (tokenActual) {
                in Constantes.booleanas -> {
                    tipoDeToken = TipoToken.BOOLEANA
                }
                in Logicos.logicos -> {
                    tipoDeToken = TipoToken.isTipoDeLogico(tokenActual)
                }
                in PalabrasReservadas.palabrasClave -> {
                    tipoDeToken = TipoToken.PALABRA_RESERVADA
                }
            }
            estadoTerminal(char)
        }
    }

    /**
     * Función correspondiente al estado S12, analiza la comparación 'diferente'
     */
    private fun estadoS11(char: Char) {
        if (char == '=') {
            estado = Estados.S2
            tokenActual += char.toString()
            //Sin cambios: tipoDeToken = DIFERENTE
        } else {
            estado = Estados.S2
            tokenActual += char.toString()
            tipoDeToken = TipoToken.ERROR
        }
    }

    /**
     * Función dedicada al estado S12, recibe un decimal y se encarga de acumular los dígitos y/o guardarlo.
     */
    private fun estadoS12(char: Char) {
        if (char.isDigit()) {
            tokenActual += char.toString()
            //Sin cambio: estado = S12, tipoDeToken = DECIMAL
        } else {
            estadoTerminal(char)
        }
    }

    /**
     * Función que corresponde al estado S14
     */
    private fun estadoS13(char: Char) {
        if (char == '=') {
            estado = Estados.S2
            tokenActual += char.toString()
            tipoDeToken = TipoToken.RESTA_Y_ASIGNACION
        } else if (Char.Companion.isDigitNotZero(char)) {
            estado = Estados.S8
            tokenActual += char.toString()
            tipoDeToken = TipoToken.ENTERO
        } else {
            estadoTerminal(char)
        }
    }

    /**
     * Funcion correspondiente al estado S16, analiza una barra de división.
     */
    private fun estadoS14(char: Char) {
        when (char) {
            '/' -> {
                estado = Estados.S6
                tokenActual += char.toString()
                //Sin cambios: tipoDeToken = DIVISION
            }
            '=' -> {
                estado = Estados.S2
                tokenActual += char.toString()
                tipoDeToken = TipoToken.DIVISION_Y_ASIGNACION
            }
            else -> {
                estadoTerminal(char)
            }
        }
    }

    /**
     * Función dedicada al estado S15, recibe un cero o un punto, el primero lo guarda y el segundo lo manda a S7
     */
    private fun estadoS15(char: Char) {
        if (char == '.') {
            estado = Estados.S7
            tokenActual += char.toString()
            tipoDeToken = TipoToken.DECIMAL
        } else {
            estadoTerminal(char)
        }
    }

    /**
     * Función correspondiente al estado S18, analiza un símbolo de multiplicación
     */
    private fun estadoS16(char: Char) {
        when (char) {
            '*' -> {
                estado = Estados.S6
                tokenActual += char.toString()
                tipoDeToken = TipoToken.EXPONENTE
            }
            '=' -> {
                estado = Estados.S2
                tokenActual += char.toString()
                tipoDeToken = TipoToken.MULTIPLICACION_Y_ASIGNACION
            }
            else -> {
                estadoTerminal(char)
            }
        }
    }

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

    private fun estadoTerminal(char: Char) {
        agregarToken() //Guardo el token y limpio (ahora es S1).
        estadoS1(char) // Tras guardar el token, analizo el nuevo char con estadoS1()
    }
}
