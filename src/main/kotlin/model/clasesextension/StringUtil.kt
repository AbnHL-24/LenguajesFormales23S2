package model.clasesextension

class StringUtil {
    companion object {
        fun String.normalizarSaltosDeLinea(): String {
            return this.replace("\r\n", "\n") // Windows
                .replace('\r', '\n')    // Mac OS Clásico
                .replace("\n", "\n")    // Unix/Linux/Android
        }
    }
}