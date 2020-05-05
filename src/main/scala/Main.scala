import codec.Codec
import codec.Codec.{given _}
import box.Factory
import box.Factory.{given _, _}
import codec.FactoryCodec.{given _}
import codec.{DatabaseColumn, BaseColumnType, mappedColumn, MappedColumn}
import scala.util.Try
import codec.FactoryColumn.{given _}
import testtypes._
import testtypes.Pet.{given _}

object Main {

  def main(args: Array[String]): Unit = {
    println("Hello world!")
  
    val i = 42

    println(summon[Codec[Int]].write(i))
    println(summon[Codec[Int]].read("41"))

    println(50.toBox[DeviceId].map(_.value))
    println(50.toBox[DeviceId])
  
    println("101".convertTo[DeviceId])
    println(11.toBox[DeviceId].map(_.writeString))

    println("true".convertTo[IsReady])
    println(false.toBox[IsReady].map(_.writeString))

    println("blaName".convertTo[OpaqueName])
    println("asd".toBox[OpaqueName].map(_.writeString.convertTo[OpaqueName]))
  
    println("asd".toBox[OpaqueName] == false.toBox[IsReady])
    val name: OpaqueName = "asd".toBox[OpaqueName].get
    val isReady: IsReady = false.toBox[IsReady].get
    println(name == isReady)

    def takeDeviceId(id: DeviceId): String = id.writeString
    def takeInt(i: Int): DeviceId = i.toBox[DeviceId].get
    
    takeDeviceId(1.toBox[DeviceId].get)
    takeInt(1)

    println(("asd", 1).toBox[Person])
    println(("asd", 1).toBox[Person].map(_.value))

    println(-11.toBox[DeviceId])
    println(Try("-11".convertTo[DeviceId]))

    println(summon[BaseColumnType[Int]].name(1))

    case class SomeColumn(i: Int)
    val someColumnMapping = mappedColumn[SomeColumn, Int](_.i, SomeColumn)
    println(Try(someColumnMapping.from("1asdsa")))
    println(someColumnMapping.name(SomeColumn(1)))

    println(summon[BaseColumnType[DeviceId]].name(1.toBox[DeviceId].get))
    println(Try(summon[BaseColumnType[DeviceId]].from("-2")))

    println(summon[DatabaseColumn[UserId]].name(14.toBox[UserId].get))


    println("name".toBox[OpaqueName])
    println(Factory[OpaqueName].create("name").map(_.value))
    println("name".toBox[OpaqueName].get.writeString.convertTo[OpaqueName])

    println(OpaqueName("name"))

    //val m: MappedColumn[Int, DeviceId] = factMap[DeviceId]
    println(summon[MappedColumn[Int, DeviceId]].fromRaw(51))
    println(summon[MappedColumn[Int, DeviceId]].toRaw(341.toBox[DeviceId].get))

    println(summon[MappedColumn[String, OpaqueName]].fromRaw("name"))

    println(Try("-41".convertTo[BookId]))

    val p: Pet = "mouse"
    println(p) 
    println(summon[Factory[Pet]](using PetFact).create("mouse"))

    "dog" match {
      case EnumPet(EnumPet.Cat) => println("cat!")
      case EnumPet(EnumPet.Dog) => println("dog!")
      case s => println(s"other '$s'")
    }

    println(EnumPet.valueOf("Cat").toString)

    println(Codec[EnumPet].read("cat").writeString)

    val pet: EnumPet = EnumPet.Cat
    pet match {
      case EnumPet.Cat => println("CAT!")
      case _ => println("other")
    }

    println(EnumPet.Dog.toString)

    DeviceId(1).get match {
      case DeviceId(i) => println(i)
    }
  }
}
