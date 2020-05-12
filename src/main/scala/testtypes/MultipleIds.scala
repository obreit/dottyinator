package testtypes

import box.FactoryBuddyIdentity

opaque type UserId = Int
object UserId extends FactoryBuddyIdentity[Int, UserId] {
  override def apply(i: Int): Option[UserId] = i match {
    case _ if i >= 0 => Some(i)
    case _ => None
  }
}

opaque type BookId = Int
object BookId extends FactoryBuddyIdentity[Int, BookId] {
  override def apply(i: Int): Option[BookId] = i match {
    case _ if i >= 0 && i <= 100 => Some(i)
    case _ => None
  }
}