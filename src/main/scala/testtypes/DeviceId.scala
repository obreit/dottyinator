package testtypes

import box.FactoryBuddyIdentity

opaque type DeviceId = Int
object DeviceId extends FactoryBuddyIdentity[Int, DeviceId] {

  override def apply(i: Int): Option[DeviceId] = i match {
    case _ if i >= 0 => Some(i)
    case _ => None
  }
  
  //given Ordering[DeviceId] = summon[Ordering[Int]] --> ops, gives you a nice infinite recursion at runtime because DeviceId = Int
}

opaque type PhoneId = Int
object PhoneId extends FactoryBuddyIdentity[Int, PhoneId] {
  override def apply(i: Int): Option[PhoneId] = if(i < 0) None else Some(i)
}