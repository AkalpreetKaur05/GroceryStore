
import scala.collection.mutable
import scala.collection.mutable._
import scala.xml._
class customer2(check_price:Map[String,Double]) {
  //var bill: Double = 0.0
  val file = XML.loadFile("C:/Users/hashmap/Downloads/category.xml")
  println("enter item you want to buy & its quantity")
  var name_item = scala.io.StdIn.readLine()
  var quantity = readInt()
  var nnn = (file \\ "name").filter(_.text.equalsIgnoreCase(name_item))
  //println(nnn.text)
  var price = check_price.get(nnn.text)
  // println("price is "+price)
  var price1 = price match {
    case Some(value) => value
    case _ => 0.0
  }
  println("price is " + price1)
  addtocart(nnn.text, price1, quantity)

  def addtocart(item_name: String, price: Double, quantity: Int) {
    customer2.bill = customer2.bill + quantity * price

    //customer2.cart_items.foreach(println)
    var value = customer2.cart_items.get(item_name)
    var new_value = value match {
      case Some(x) => x
      case None => 0
    }
    if (new_value == 0) {
      customer2.cart_items += (item_name -> quantity)
    }
    else {
      new_value = new_value + quantity
      customer2.cart_items.update(item_name, new_value)
    }
    var stt=customer2.map1.get(item_name)
    var stt2=stt match
      {
      case Some(x)=>x
      case None=>0
      }
    stt2=stt2-quantity
    customer2.map1.update(item_name,stt2)
    println("updated stock is "+customer2.map1)

    customer2.listst += customer2.bill
    println("do u want to remove any item from cart???")
    println("** y for yes **")
    println("** n for no ***")
    var che = readChar()
    che match {
      case 'y' => {
        println("enter name of item to remove & its quantity");
        var itemrem = readLine()
        var quant = readInt()
        removefromcart(itemrem, quant, customer2.cart_items, check_price)
      }

      case 'n'
      => 0
    }
  }

    def removefromcart(item_na: String, quantity: Int, cart_items: Map[String, Int], price: Map[String, Double]) {
      if (cart_items.contains(item_na)) {
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
            var price1=price.get(item_na)
            var price2=price1 match
              {
              case Some(x)=>x
              case None=>0
              }
            var st=customer2.map1.get(item_na)
            var st2=st match
              {
               case Some(x)=>x
               case none=>0
              }
            st2=st2+quantity
            customer2.map1.update(item_na,st2)
            println("after removal items in stock are "+customer2.map1)
            customer2.bill=customer2.bill-(price2*quantity)
            println("after removal bill is "+customer2.bill)
            println("Item removed successfully....")
          }
        }
      }
      else
        {
          println("no item present")
        }

    }


}
object customer2 extends App {

  var read = 1
  var listBu = ListBuffer[Int]()
  var listst = ListBuffer[Double]()
  var nameStore = ListBuffer[String]()
  var cart_items: Map[String, Int] = Map()
  var map1: Map[String,Int] = Map()
  var check_price: Map[String, Double] = Map()
  var categorySel: Int = 0
  var items1Scheme = ListBuffer[String]()
  var categorysch = mutable.HashMap[Int, ListBuffer[String]]()
  var itemPrice = Map[String, Double]()
  var bill:Double=0.0
  mainn1()

  case class item(id: Int, name: String, uom: String, price: Double, stock: Int)

  def itemtoXml(i: item): Node = {
    <item>
      <id>={i.id - 4}</id>
      <name>{i.name}</name>
      <uom>{i.uom}</uom>
      <stock>{i.stock}</stock></item>
  }


  /*def toItem(node: Node): item = {
    val id = (node \\ "item" \ "id").text.toInt
    val name = (node \ "name").text
    val uom = (node \ "uom").text
    val stock = (node \\ "stock").text.toInt
    val price = (node \\ "amount").text.toDouble
    var list = List(id, name, uom, stock, price)
    map1 += (name->stock)
    println("stock of diff items is "+map1)
    check_price += (name -> price)
    listBu += stock
    item(id, name, uom, price, stock)
  }*/

  def toItemNew(node: Node): item = {

    val id = (node \\ "item" \ "id").text.toInt
    val name = (node \ "name").text
    val uom = (node \ "uom").text
    val stock = (node \\ "stock").text.toInt
    val price = (node \\ "amount").text.toDouble
    println("price is " + price)
    var list = List(id, name, uom, stock, price)
    map1 += (name->stock)
    println("stocks are "+map1)
    check_price += (name -> price)
    println("items prices are " + check_price)
    listBu += stock
    item(id, name, uom, price, stock)
  }

  /*def toItemNewScheme(node: Node): item = {

    val id = (node \\ "item" \ "id").text.toInt
    val name = (node \ "name").text
    val uom = (node \ "uom").text
    val stock = (node \\ "stock").text.toInt
    val price = (node \\ "amount").text.toDouble
    //println("price is "+price)
    var list = List(id, name, uom, stock, price)
    map1 += (name->stock)
    println(map1)
    //  check_price += (name -> price)
    //listBu += stock
    item(id, name, uom, price, stock)
  }*/

  //to calculate bill
  def orderSummary(cart_items: Map[String, Int]): Unit = {
    println("ur orders are as follows.... ")
    cart_items.foreach(println)

  }

  /*def addScheme() {
    println("** Enter category u want to provide discount  **");
    var read = readInt()
    var categorySel = read
    read = read - 1
    val file = XML.loadFile("C:/Users/hashmap/Downloads/category.xml");
    val item1 = (file \\ "items" \\ "category")
    var result = (item1(read) \\ "item").map(toItemNewScheme)
    result.foreach(println)
    var res = (item1(read) \\ "item" \\ "name")
    for (i <- 0 to res.length - 1) {
      items1Scheme += (res(i).text)
    }
    println(items1Scheme)
    categorysch += (categorySel -> items1Scheme)
    println(categorysch)
    println("enter discount % age")
    var dis = readInt()
    return (items1Scheme, dis)
  }*/

 /* def checkSchemeNew() {
    var add = addScheme()
    return (add)
  }*/

  def checkSchemes1(cart_items: Map[String, Int], categorysch: mutable.HashMap[Int, ListBuffer[String]]): Unit = {
    if (categorysch.keySet.contains(1)) {
      var valu = categorysch.get(1)
      var valu1 = valu match {
        case Some(x) => x
        case None => 0
      }
      if (valu1.toString.contains("Dove soap") && (cart_items.keySet.exists(x => x.equalsIgnoreCase("Dove soap")))) {
        var va = cart_items.get("Dove soap")
        println("vaa is " + va)
        var va1 = va match {
          case Some(x) => x
          case None => 0
        }
        println("value is " + va1)
        if (va1 >= 2) {
          va1 = va1 + 1
          println("** Congrats u get 1 item free on ur purchase of " + (va1 - 1) + "items")
          cart_items.update("Dove soap", va1)

        }
      }
      /*var y = "dove soap"
    var abc = cart_items.keySet.exists(x => x.equalsIgnoreCase(y))
    println("cccc is " + abc)
    if (cart_items.keySet.exists(x => x.equalsIgnoreCase(y))) {
      var va = cart_items.get("Dove soap")
      println("vaa is " + va)
      var va1 = va match {
        case Some(x) => x
        case None => 0
      }
      println("value is " + va1)
      if (va1 >= 2) {
        va1 = va1 + 1
        println("** Congrats u get 1 item free on ur purchase of " + (va1 - 1) + "items")
        cart_items.update("Dove soap", va1)

      }*/
    }
  }


  def calBill(bill:Double, cart_items: Map[String, Int]): Unit = {
    println("items bought along with their quantities ")
    cart_items.foreach(println)
    println(cart_items)
    var total_bill = bill
    println("total bill is " + total_bill)
    var obj = new admin
    var abc = obj.uploadSchemes()
    println("list is " + abc._1)
    println("price is " + abc._2)
    var discount = abc._2
    var abcd = cart_items.keySet
    for (i <- 0 to (abc._1.length) - 1) {
      for (j <- abcd) {
        if (j == (abc._1(i))) {
          println("discount is " + discount)
          total_bill = total_bill - discount
          println("Bill after discount is " + total_bill)
        }
      }
    }
    /*var main_key=abc._1.keys
    var abcd=abc._1.get(main_key.head)
      println("abcd value is"+abcd)
    var item_final=abcd match
      {
      case Some(x)=>x
      case None=>0
      }*/
    // if(cart_items.keySet.exists(x => x.equalsIgnoreCase(item_final.toString.))))
    //println("final "+item_final)
    /* if(abc._1.keySet.contains(1))
    {
      var valu=categorysch.get(1)
      var valu1=valu match {
        case Some(x) => x
        case None => 0
      }
      if(valu1.toString.contains("Dove soap")&&(cart_items.keySet.exists(x => x.equalsIgnoreCase("Dove soap"))))
      {
        var va = cart_items.get("Dove soap")
        println("vaa is " + va)
        var va1 = va match {
          case Some(x) => x
          case None => 0
        }
        println("value is " + va1)
        if (va1 >= 2) {
          va1 = va1 + 1
          println("** Congrats u get 1 item free on ur purchase of " + (va1 - 1) + "items")
          cart_items.update("Dove soap", va1)

        }*/
    // checkSchemes1(cart_items,categorysch)
    // var addd=checkSchemeNew()
    //println(addd)
    //println("total is "+total_bill)
    /*var itemName=(new admin).checkSchemes()
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
    }*/
    println(cart_items)

    println("total bill is " + total_bill)
  }
  /*def accesscategory(): Unit =
  {
    println("** Enter ur Choice **")
    println("** 1. To Buy Pdts ** ")
    println("** 2. To check Order Summary **")
    println("** 3. To Remove items from Cart **")
    println("** 4. To show Bill **")
    var red=readInt()
    red match
      {
      case 2=>orderSummary(cart_items)
      case 3=>{println("enter item u want to remove & quantity")
               var read1=readLine()
               var quantity=readInt()
                new customer2(check_price).removefromcart(read1,quantity,cart_items,itemPrice)

              }
      }
  }*/


  def mainn1() {
    println("enter 1 for customer login & 2 for admin login")
    var read = readInt()
    read match {
      case 1 => {
        println("welcome !")
        println("**********")
        println("Enter option...")
        println("1. For Soaps")
        println("2. For Fruits")
        println("3. For Biscuits")
        val file = XML.loadFile("C:/Users/hashmap/Downloads/category.xml")
        //val item1 = (file \\"item").map(toItem)
        //item1.foreach(println)
        val item1 = (file \\ "items" \\ "category")

        var option1 = readInt()
        categorySel = option1
        option1 = option1 - 1
        //println("items... "+item1(option))
        var result = (item1(option1) \\ "item").map(toItemNew)
        result.foreach(println)
        var res = (item1(option1) \\ "item" \\ "name")
        for (i <- 0 to res.length - 1) {
          items1Scheme += (res(i).text)
        }
        println(items1Scheme)
        categorysch += (categorySel -> items1Scheme)
        var obj = new customer2(check_price)
        println("do u want to continue    press y to continue & n for no")
        var read1 = readChar()
        read1 match {
          case 'y' => {
            new customer2(check_price)
            //calBill(listst, cart_items)
          }
          case 'n' => println("thanks for shopping.... ur bill is" + calBill(bill, cart_items))
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
          case 'y' => calBill(bill, cart_items)
          case 'n' => 0
        }
      }
      // calBill(listst,cart_items)

      case 2 => {
        println("welcome admin !!!!!!")
        var obj = new admin
        obj.entry()
        //XML.save("C:/Users/hashmap/Downloads/store2.xml", (<catalogue>{item1.map(itemtoXml)}</catalogue>))

      }
    }
  }
}


