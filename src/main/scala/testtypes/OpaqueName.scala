package testtypes

import box.FactoryBuddyIdentity

opaque type OpaqueName = String
object OpaqueName extends FactoryBuddyIdentity[String, OpaqueName]

opaque type OtherName = String
object OtherName extends FactoryBuddyIdentity[String, OtherName]