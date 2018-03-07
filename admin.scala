import customer2._

import scala.collection.mutable._
import scala.xml._
class admin
{
  case class item(id: Int, name: String, uom: String, price: Double, stock: Int)
  def toItem(node: Node): item = {
  val id = (node \ "id").text.toInt
  val name = (node \ "name").text
  val uom = (node \ "uom").text
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
  def checkSchemes(): String=
  {
    println("buy 2 soaps & get 1 free.....")
    "Dove soap"
  }
  def toItemNew(node: Node): item = {

    val id = (node \\"item" \ "id").text.toInt
    val name = (node \ "name").text
    val uom = (node \ "uom").text
    val stock = (node \\ "stock").text.toInt
    val price = (node \\ "amount").text.toDouble
    println("price is "+price)
    var list = List(id, name, uom, stock, price)
    map1 += (stock -> name)
    check_price += (name -> price)
    listBu += stock
    item(id, name, uom, price, stock)
  }
  def uploadSchemes():(ListBuffer[String],Int)=
   {
     println("Schemes available on categories"+categorysch.keySet+"and on items"+categorysch.values.toBuffer)
     categorysch.values.toBuffer
     return(items1Scheme,discount)
   }
  var lessStock:Map[String,Int]=Map()
  var categorySel:Int=0
  var items1Scheme=ListBuffer[String]()
  var categorysch=HashMap[Int,ListBuffer[String]]()
  items1Scheme+=("Dove soap")
  items1Scheme+=("Lux bath soap")
  categorysch+=(1->items1Scheme)
  var discount=50


  def entry()
  {
  println("**************************")
  println("** 1. For stock check ****")
  println("** 2. For Add Discounts **")
  println("**************************")
  var read=readInt()
  read match {
    case 1 => {
      val file = XML.loadFile("C:/Users/hashmap/Downloads/category.xml");
      val item1 = (file \\ "item").map(toItem)
      check_stock(lessStock)
    }
    case 2 => {println("** Enter category u want to provide discount  **");
              var read=readInt()
              categorySel=read
              read=read-1
              val file = XML.loadFile("C:/Users/hashmap/Downloads/category.xml");
             val item1 = (file \\"items"\\"category")
             var result=(item1(read)\\"item").map(toItemNew)
             result.foreach(println)
             var res=(item1(read)\\"item"\\"name")
             for(i<-0 to res.length-1)
              {
                items1Scheme+=(res(i).text)
              }
             println(items1Scheme)
             println("enter discount")
             discount=readInt()
             categorysch.remove(1)
             categorysch+=(categorySel->items1Scheme)
             println(categorysch)
             uploadSchemes()
            // addScheme()
            }
     }
    println("Do u want to continue ?")
    println("** y. For yes ** ")
    println("** n. For No *** ")

    var opt=readChar()
    opt match
      {
      case 'y'=>mainn1()
      case 'n'=>println("thanks...")
      }

    }
}
