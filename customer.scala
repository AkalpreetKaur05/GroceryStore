
import scala.xml._
object customer
{
  def main(args:Array[String]):Unit= {
    case class item(id: Int, name: String, uom: Int, price: Double, stock: Int)
    def itemtoXml(i: item): Node = {
      <item>
        <id>=
          {i.id}
        </id>
        <name>
          {i.name}
        </name>
        <uom>
          {i.uom}
        </uom>
        <stock>
          {i.stock}
        </stock>
      </item>
    }

    def toItem(node: Node): item = {
      val id = (node \ "id").text.toInt
      val name = (node \ "name").text
      val uom = (node \ "uom").text.toInt
      val stock = (node \\ "stock").text.toInt
      val price = (node \\ "amount").text.toDouble
      item(id, name, uom, price, stock)
    }

    val file = XML.loadFile("C:/Users/hashmap/Downloads/store.xml")
    val item1 = (file \\ "item").map(toItem)
    item1.foreach(println)
  }
}
