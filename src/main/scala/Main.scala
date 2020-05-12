import codec.Codec
import codec.Codec.{given _}
import codec.FactoryCodec.{given _, _}
import codec.{DatabaseColumn, BaseColumnType, mappedColumn, MappedColumn}
import scala.util.Try
import codec.FactoryColumn.{given _}
import testtypes._
import scala.runtime.{Tuple, EnumValues}
import box.{given _}

object Main {

  def main(args: Array[String]): Unit = {
    /*println("Hello world!")
  
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

    println(EnumPet.Dog.toString)*/

    /*DeviceId(1).get match {
      case DeviceId(i) => println(i)
    }*/

    /*println(summon[Codec[DeviceId]].write(summon[Codec[DeviceId]].read("41")))
    val x = summon[BoxFact[Int, DeviceId]].create(1).valueOfBox

    val deviceId: DeviceId = 42.toBox[DeviceId].get
    val userId: UserId = 42.toBox[UserId].get
    
    val name1: OpaqueName = "name".toBox[OpaqueName].get
    val name2: OtherName = "name".toBox[OtherName].get

    println(name1.hashCode)
    println(name2.hashCode)

    val ids: Seq[DeviceId] = Seq(deviceId, deviceId, 0.toBox[DeviceId].get)
    //ids.sorted

    println(ids.sorted)
    println(Seq(1.toBox[UserId].get, 50.toBox[UserId].get, 11.toBox[UserId].get).sorted)
    println(Seq("it's".toBox[OpaqueName].get, "hello".toBox[OpaqueName].get, "me".toBox[OpaqueName].get).sorted)

    val ud: Map[UserId | DeviceId, String] = Map()
    val ud1 = ud + (1.toBox[DeviceId].get -> "DeviceName")
    val ud2 = ud1 + (2.toBox[UserId].get -> "UserName")
    val ud3 = ud2 + (1.toBox[UserId].get -> "UserName")
    println(ud3)

    type Concat[Xs <: Tuple, +Ys <: Tuple] <: Tuple = Xs match {
      case Unit => Ys
      case x *: xs => x *: Concat[xs, Ys]
    }

    println(Tuple.concat((1, 2), ("h", true)))
    val petEnumVals = EnumValues[EnumPet]
    println(petEnumVals.fromName)

    val phoneId = PhoneId(10)
    
    phoneId match {
      case Some(PhoneId(i)) =>
      case None => 

      //case None => println("No phone id created")
    }

    case class A(i: Int)
    Option(A(1)) match {
      case Some(A(i)) => println("as")
      case None => println("asdasd")
    }

    println(EnumPet("catt"))

    EnumPet("catt").get match {
      case EnumPet.Cat => println("cat")
      case EnumPet.Unsupported(s) => println(s"unsupported pet $s")
      case other => println(s"other pet $other")
      case str => println(s"no pet $str")
    }

    println(OpPet("dogs"))

    println("dog".toBox[Pet])

    //println(deviceId == userId)
/*
    case class SameId1(id: Int)
    case class SameId2(id: Int)
    println(SameId1(101).hashCode)
    println(SameId2(101).hashCode)
*/    
    //val devCodec: Codec[DeviceId] = boxFactCodec[Int, DeviceId]
    //println(devCodec.write(devCodec.read("1")))*/

    println(PhoneId(1))
    println(Codec[PhoneId].read("1"))
    println("3".convertTo[PhoneId].writeString)
    PhoneId(1) match {
      case Some(PhoneId(10)) => println("ten")
      case Some(PhoneId(1)) => println("one")
      case Some(PhoneId(i)) => println(i)
      case None => println("none")
    }

    PhoneId(10).get match {
      case PhoneId(10) => println("ten")
      case PhoneId(1) => println("one")
      case PhoneId(i) => println(i)
    }

    println(summon[BaseColumnType[PhoneId]].name(PhoneId(42).get))
    println(summon[DatabaseColumn[PhoneId]].name(PhoneId(42).get))

    println("caT".convertTo[EnumPet])

    println(1.toBox[DeviceId].map(_.value))
    println(52.toBox[DeviceId].get.value)
    println(UserId(71))
    println(EnumPet("dog"))
    
    Seq(1).map(_ * 2)
  }
}
