package testtypes

import box.FactoryCompanionDefineCstr

opaque type DeviceId = Int
object DeviceId extends FactoryCompanionDefineCstr[Int, DeviceId]({
  case i if i >= 0 => Some(i)
  case _ => None
})