package testtypes

import box.FactoryCompanionOf

opaque type IsReady = Boolean
object IsReady extends FactoryCompanionOf[Boolean, IsReady]