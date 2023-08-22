import controller.principal.PrincipalController
import view.principal.PrincipalView

fun main(args: Array<String>) {
    println("Hello World!")

    val principalView = PrincipalView()
    val principalController = PrincipalController(principalView)
    principalController.iniciar()

    // Try adding program arguments via Run/Debug configuration.
    // Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.
    println("Program arguments: ${args.joinToString()}")
}