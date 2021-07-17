fun main() {
    val step = 1
    val rawSheet = """
1 2 3 4 5 6 7 [1]
    """.trimIndent()
    println(sheetOutput(transPose(sheetFormat(rawSheet),sheetRange,step),sheetRange))
}