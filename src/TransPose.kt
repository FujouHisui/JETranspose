val tone:ArrayList<String> = arrayListOf("1","#1","2","#2","3","4","#4","5","#5","6","#6","7")
var sheetRange = ArrayList<Int>()
fun sheetFormat(rawSheet:String): ArrayList<String> {
    var isSharp = false
    var sheet = ArrayList<String>()
    sheetRange.clear()
    var pitch:Int = 0
    loop@ for (item:Char in rawSheet){
        when(item){
            '[','【' -> pitch++
            ']','】' -> pitch--
            '(','（' -> pitch--
            ')','）' -> pitch++
            '#' -> {
                isSharp = true
                //println("sharp")
            }
            in '0'..'9' ->{
                //println("digit")
                if (isSharp){
                    if (item == '3'){
                        sheet.add("4")
                        sheetRange.add(pitch)
                        isSharp = false
                        continue@loop
                    }
                    if(item == '7'){
                        sheet.add("1")
                        sheetRange.add(pitch+1)
                        isSharp = false
                        continue@loop
                    }
                    sheet.add("#$item")
                    sheetRange.add(pitch)
                    isSharp = false
                }
                else{
                    sheet.add("$item")
                    sheetRange.add(pitch)
                }
            }
            //' ' -> {
                //println("space")
            //}
            '\n' -> {
                //println("linefeed")
                sheet.add(item.toString())
                sheetRange.add(9)
            }
        }
    }
    return sheet
}

fun transPose(sheet:ArrayList<String>,sheetRange:ArrayList<Int>,step:Int):ArrayList<String>{
    var newSheet = ArrayList<String>()
    var pitch = 0
    for ((i,item:String) in sheet.withIndex()){
        pitch = 0
        if(item!="\n"){
            var index = tone.indexOf(item)
            index += step
            while (index >= tone.size) {
                pitch += 1
                index -= tone.size
            }
            while (index < 0){
                pitch -= 1
                index += tone.size
            }
            newSheet.add(tone[index])
            sheetRange[i] += pitch
        }
        else{
            newSheet.add(item)
        }
    }
    //if (pitch != 0)
    //    throw ArithmeticException("pitch error")
    return newSheet
}

fun sheetOutput(sheet:ArrayList<String>,sheetRange:ArrayList<Int>):String{

    var newSheetStr = String()
    loop@ for ((i,item:String) in sheet.withIndex()){
        var tempStr: String
        when{
            sheetRange[i] in 1..2 -> {
                tempStr = item
                for (index in 0 until sheetRange[i])
                    tempStr = "[$tempStr]"
                newSheetStr += tempStr
            }
            sheetRange[i] in -2..-1 -> {
                tempStr = item
                for(index in sheetRange[i] until 0)
                    tempStr = "($tempStr)"
                newSheetStr += tempStr
            }
            sheetRange[i] == 0 -> {
                newSheetStr += item
            }
            sheetRange[i] == 9 ->{
                newSheetStr += item
                continue@loop
            }
            else -> {
                throw ArithmeticException("illegal pitch!")
            }
        }
        newSheetStr += " "
    }
    return newSheetStr
}
