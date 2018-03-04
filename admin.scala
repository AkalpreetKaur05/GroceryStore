import customer2.{check_price, listBu, map1}
import scala.collection.mutable._
import scala.xml._
class admin
{
  case class item(id: Int, name: String, uom: Int, price: Double, stock: Int)
  def toItem(node: Node): item = {
  val id = (node \ "id").text.toInt
  val name = (node \ "name").text
  val uom = (node \ "uom").text.toInt
  val stock = (node \\ "stock").text.toInt
  val price = (node \\ "amount").text.toDouble
    println("stock of "+name+"is "+stock )
  var list = List(id, name, uom, stock, price)
  map1 += (stock -> name)
  if(stock<70)
    {
      lessStock+=(name->stock)
    }
  check_price += (name -> price)
  listBu += stock
  item(id, name, uom, price, stock)
}
  def check_stock(lessStock:Map[String,Int]): Unit =
  {
    println("items that are getting shortage are "+lessStock)
  }
  var lessStock:Map[String,Int]=Map()
  val file = XML.loadFile("C:/Users/hashmap/Downloads/store.xml")
  val item1=(file\\"item").map(toItem)
  println(item1)
  println("enter 1 to check stock.....2 to add discounts..... 3 to add stock....")
  var read=readInt()
  read match
    {
    case 1=> check_stock(lessStock)
    }


  //println(item1)
}
