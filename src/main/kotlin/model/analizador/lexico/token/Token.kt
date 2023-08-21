package model.analizador.lexico.token

/**
 * Objeto Token, contiene los datos necesarios para cada token.
 * @param token contiene el token
 * @param tipoDeToken es el tipo de token
 * @param fila almacena la fila del carácter
 * @param columna almacena la columna
 */
class Token(
    private val token: String,
    private val tipoDeToken : TipoToken,
    private val fila: Int,
    private val columna: Int)