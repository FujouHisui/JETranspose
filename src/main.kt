fun main() {
    val step = -2
    val oldSheet = Sheet()
    val sheetOut = (sheetOutput(transPose(sheetFormat(oldSheet.rawSheet),sheetRange,step),sheetRange))
    loop@ for (item:Char in sheetOut)
        //if(item!=' ')
            print(item)

    //println(sheetOutput(transPose(sheetFormat(rawSheet),sheetRange,step),sheetRange))
}