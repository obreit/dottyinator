package testtypes

import box.FactoryBuddyIdentity

opaque type IsReady = Boolean
object IsReady extends FactoryBuddyIdentity[Boolean, IsReady]