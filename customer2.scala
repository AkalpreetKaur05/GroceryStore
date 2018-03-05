
import xml._
import scala.collection.mutable._
class customer2(check_price:Map[String,Double])
{
  var bill:Double=0.0
  val file = XML.loadFile("C:/Users/hashmap/Downloads/store.xml")
  println("enter item you want to buy & its quantity")
  var name_item=scala.io.StdIn.readLine()
  var quantity=readInt()
  var nnn=(file \\"name").filter(_.text.equalsIgnoreCase(name_item))
  //println(nnn.text)
  var price=check_price.get(nnn.text)
 // println("price is "+price)
  var price1= price match
  {
    case Some(value)=>value
    case _=>0.0
  }
  println("price is "+price1)
  addtocart(nnn.text,price1,quantity)
  def addtocart(item_name:String,price:Double,quantity:Int)
  {
    bill=bill+quantity*price

    //customer2.cart_items.foreach(println)
    var value=customer2.cart_items.get(item_name)
    var new_value=value match
      {
      case Some(x)=>x
      case None=>0
      }
    if(new_value==0 )
      {
        customer2.cart_items+=(item_name->quantity)
      }
    else {
      new_value = new_value + quantity
      customer2.cart_items.update(item_name, new_value)
    }
    customer2.listst+=bill
   // println("do u want to remove any item from cart???")
    //var che=readChar()
    /*che match {
      case 'y' => {
        println("enter name of item to remove & its quantity");
        var itemrem = readLine()
        var quant = readInt()("bill is " + bill)
      }
    }*/
  /* def removefromcart(item_na:String,quantity:Int,car
       //   removefromcart(itemrem,quant,customer2.cart_items)}
      case 'n'=>0
      }
   // XML.save("C:/Users/hashmap/Downloads/store1.xml", (<catalogue>{item1.map(itemtoXml)}</catalogue>)
    // customer2.listst+=bill
    //printlnt_items:Map[String,Int])
    {
      if (cart_items.contains(item_na))
      {
        var value = cart_items.get(item_na)
        var new_value = value match {
          case Some(x) => x
          case None => 0
        }
        if (new_value == 0) {
          println("no item present")
        }
        else {
          new_value = new_value - quantity
          if (new_value > 0) {
            cart_items.update(item_na, new_value)
          }
        }
      }*/
    }

}
object customer2 extends App {
  println("enter 1 for customer login & 2 for admin login")
  var read = readInt()
  var listBu = ListBuffer[Int]()
  var listst = ListBuffer[Double]()
  var nameStore = ListBuffer[String]()
  var cart_items: Map[String, Int] = Map()
  var map1: Map[Int, String] = Map()
  var check_price: Map[String, Double] = Map()

  case class item(id: Int, name: String, uom: Int, price: Double, stock: Int)

  def itemtoXml(i: item): Node = {
    <item><id>={i.id - 4}</id>
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

  //to calculate bill
  def orderSummary(cart_items: Map[String, Int]): Unit = {
    println("ur orders are as follows.... ")
    cart_items.foreach(println)

  }

  def calBill(liststo: ListBuffer[Double], cart_items: Map[String, Int]): Unit = {
    println("items bought along with their quantities ")
    cart_items.foreach(println)
    println(cart_items)
    var total_bill = liststo.sum
    var itemName=(new admin).checkSchemes()
    println(itemName)
    var no1=(cart_items.get(itemName))
    println(no1)
    var no2=no1 match
    {
      case Some(x)=>x
      case None=>0
    }
    if(no2>=2)
    {
      var no3=(no2/2)
      println("on purchase of "+no2+"items..... u get free .... "+no3+"items")
      no3=no2+no3
      cart_items.update(itemName,no3)
    }
    println(cart_items)

    println("total bill is " + total_bill)
  }

  read match {
    case 1 => {
      println("welcome !")
      val file = XML.loadFile("C:/Users/hashmap/Downloads/store.xml")
      val item1 = (file \\ "item").map(toItem)
      item1.foreach(println)
      var obj = new customer2(check_price)
      println("do u want to continue    press y to continue & n for no")
      var read1 = readChar()
      read1 match {
        case 'y' => {
          new customer2(check_price);
          //calBill(listst, cart_items)
        }
        case 'n' => println("thanks for shopping.... ur bill is")
      }
      println("enter y to check order summary & n to not check..")
      var cal = readChar()
      cal match {
        case 'y' => orderSummary(cart_items)
        case 'n' => 0
      }
      println("enter y to check bill  & n for not")
      var cal1 = readChar()
      cal1 match {
        case 'y' => calBill(listst, cart_items)
        case 'n' => 0
      }
    }
    // calBill(listst,cart_items)

    case 2 => {
      println("welcome admin !!!!!!")
      val file = XML.loadFile("C:/Users/hashmap/Downloads/store.xml")
      val item1 = (file \\ "item").map(toItem)
      var obj = new admin
      //XML.save("C:/Users/hashmap/Downloads/store2.xml", (<catalogue>{item1.map(itemtoXml)}</catalogue>))
      /* println("enter 1 for checking stock..... 2 for giving discounts....")

        println("check stock less than certain value")
        var change = listBu.filter(_ < 150)
        println(change)
        var map2 = map1.filterKeys(_ < 70)
        println(map2)
          */
    }
  }



}