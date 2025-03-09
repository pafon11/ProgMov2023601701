import java.util.Scanner

val scanner = Scanner(System.`in`)

fun calcularTiempoVida() {
    print("Ingrese su año de nacimiento: ")
    val anioNacimiento = scanner.nextInt()
    print("Ingrese su mes de nacimiento (1-12): ")
    val mesNacimiento = scanner.nextInt()
    print("Ingrese su día de nacimiento: ")
    val diaNacimiento = scanner.nextInt()

    print("Ingrese el año actual: ")
    val anioActual = scanner.nextInt()
    print("Ingrese el mes actual (1-12): ")
    val mesActual = scanner.nextInt()
    print("Ingrese el día actual: ")
    val diaActual = scanner.nextInt()
    var aniosVividos = anioActual - anioNacimiento
    var mesesVividos = mesActual - mesNacimiento
    var diasVividos = diaActual - diaNacimiento

    if (diasVividos < 0) {
        mesesVividos -= 1
        diasVividos += 30 // Aproximación
    }

    if (mesesVividos < 0) {
        aniosVividos -= 1
        mesesVividos += 12
    }

    println("Tiempo vivido:")
    println("$aniosVividos años, $mesesVividos meses, $diasVividos días")
}

fun NombreCompleto() {
    print("Ingrese su nombre completo: ")
    scanner.nextLine() // Consumir la línea pendiente
    val nombreCompleto = scanner.nextLine()
    println("Su nombre es: $nombreCompleto")
}

fun sumarNumeros(): Double {
    print("Ingrese el primer número: ")
    val primerNumero = scanner.nextDouble()
    print("Ingrese el segundo número: ")
    val segundoNumero = scanner.nextDouble()
    print("Ingrese el tercer número: ")
    val tercerNumero = scanner.nextDouble()
    val suma = primerNumero + segundoNumero + tercerNumero
    println("La suma es: $suma")
    return suma
}

fun main() {
    while (true) {
        println("Escoja una opción:")
        println("---- MENÚ ----")
        println("1. Suma de 3 números")
        println("2. Ingresar nombre completo")
        println("3. Cálculo de vida (tiempo vivido)")
        println("4. Cierre de programa")
        print("Seleccione una opción: ")

        val opcion = if (scanner.hasNextInt()) scanner.nextInt() else {
            scanner.next()
            -1
        }

        when (opcion) {
            1 -> sumarNumeros()
            2 -> NombreCompleto()
            3 -> calcularTiempoVida()
            4 -> {
                println("Cerrando el programa...")
                break
            }
            else -> println("Opción no válida. Intente otra vez:(.")
        }
        println()
    }
    scanner.close()
}
