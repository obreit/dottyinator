package testtypes

import box.FactoryCompanionDefineCstr

opaque type UserId = Int
object UserId extends FactoryCompanionDefineCstr[Int, UserId]({
    case i if i >= 0 => Some(i)
    case _ => None
})

opaque type BookId = Int
object BookId extends FactoryCompanionDefineCstr[Int, BookId]({
  case i if i >= 0 && i <= 100 => Some(i)
  case _ => None
})