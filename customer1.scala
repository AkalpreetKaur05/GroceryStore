import scala.collection.mutable.Map
import scala.collection.mutable.ListBuffer
import scala.xml._
trait bill
{
  var tbill:Double=0
}
class cart(check_price:Map[String,Double])extends bill
{
  var bill:Double=0.0
  val file = XML.loadFile("C:/Users/hashmap/Downloads/store.xml")
  println("enter item you want to buy & its quantity")
  var name_item=readLine()
  var quantity=readInt()
  var nnn=(file \\"name").filter(_.text.equalsIgnoreCase(name_item))
  if (nnn.isEmpty){println("wrong input/choice...")}
  println(nnn.text)
  var price=check_price.get(nnn.text)
  println("price is "+price)
  var price1= price match
  {
    case Some(value)=>value
    case _=>0.0
  }
  println("price is "+price1)

  addtocart(nnn.text,price1,quantity)
  def addtocart(item_name:String,price:Double,quantity:Int)
  {
    bill=bill+quantity *price
    //tbill=bill+tbill
    println("bill is "+bill)
    billCal(bill)
    println("do u want to continue    press y to continue & n for no")
    var read1=readChar()
    read1 match
    {
      case 'y'=>new cart(check_price)
      case 'n'=>println("thanks for shopping.... ur bill is"+bill)
    }

  }
  def billCal(bill:Double): Unit =
  {
    tbill=bill+tbill
    println("total bill is "+tbill)
  }

}
object customer1
{
  def main(args:Array[String]):Unit= {
    println("enter 1 for customer login & 2 for admin login")
    var read = readInt()
    var listBu = ListBuffer[Int]()
    var nameStore = ListBuffer[String]()
    var bill:Double=0
    //var stock_item:List[Int]=0
    var map1: Map[Int, String] = Map()
    var check_price: Map[String, Double] = Map()
    case class item(id: Int, name: String, uom: Int, price: Double, stock: Int)
    def itemtoXml(i: item): Node = {
      <item><id>={i.id}</id>
        <name>{i.name}</name>
        <uom>{i.uom}</uom>
        <stock>{i.stock}</stock>
      </item>
    }

    def toItem(node: Node): item = {
      val id = (node \ "id").text.toInt
      val name = (node \ "name").text
      val uom = (node \ "uom").text.toInt
      val stock = (node \\ "stock").text.toInt
      val price = (node \\ "amount").text.toDouble
      var list = List(id, name, uom, stock, price)
      map1 += (stock -> name)
      check_price += (name -> price)
      listBu += stock
      item(id, name, uom, price, stock)
    }

    read match {
      case 1 => {
        println("welcome !")
        val file = XML.loadFile("C:/Users/hashmap/Downloads/store.xml")
        val item1 = (file \\ "item").map(toItem)
        item1.foreach(println)
        var obj = new cart(check_price)

      }
        // XML.save("C:/Users/hashmap/Downloads/store1.xml", (<catalogue>{item1.map(itemtoXml)}</catalogue>))
        var change = listBu.filter(_ < 150)
        println(change)
        var map2 = map1.filterKeys(_ < 70)
        println(map2)


    }
  }

}