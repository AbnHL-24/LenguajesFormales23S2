package model.analizador.lexico.token

/**
 * Objeto Token, contiene los datos necesarios para cada token.
 */
class Token(
    val token: String,
    val tipoDeToken : TipoToken,
    val fila: Int,
    val columna: Int)