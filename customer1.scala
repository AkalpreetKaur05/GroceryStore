import scala.collection.mutable.ListBuffer
import scala.xml._
object customer1
{
  def main(args:Array[String]):Unit=
  {
    var listBu=ListBuffer[Int]()
    case class item(id: Int, name: String, uom: Int, price: Double, stock: Int)
    def itemtoXml(i: item): Node = {
      <item>
        <id>={i.id}</id>
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
      var list=List(id,name,uom,stock,price)
      println(list)
      listBu+=stock
      // println(list(3))
      println(listBu.length)
      item(id, name, uom, price, stock)
    }

    val file = XML.loadFile("C:/Users/hashmap/Downloads/store.xml")
    val item1 = (file \\ "item").map(toItem)
    item1.foreach(println)
    XML.save("C:/Users/hashmap/Downloads/store2.xml", (<catalogue>{item1.map(itemtoXml)}</catalogue>))
    var fetch = XML.loadFile("C:/Users/hashmap/Downloads/store2.xml")
    var stoc = (file \\ "item"\\"stock").text.toArray
    stoc.foreach(print)
    var change= listBu.filter(_ <50)
    println(change)


    //stoc.foreach()
    /*if(stoc<150)
      {
       println("item name"+stoc)
      }*/
    // println("stock value is "+checksto)
    //println("data is "+checksto)
    /* for(i<- (fetch\\"stock").text.toInt)
       {
        if(i<150)
          {

          }
       }*/

  }

  /*  override def transform(n:Node): Seq[Node] =n match{
      case Elem(prefix,"stock",attribs,scope,_ *)=>
        <stock>
         { stock map{i=> <stock value={67}/>}}
        </stock>
      case other=>other
    }*/

}
