package com.futurechimps.swagger.sample

import org.scalatra.test.specs2._

// For more on Specs2, see http://etorreborre.github.com/specs2/guide/org.specs2.guide.QuickStart.html
class FlowersSpec extends ScalatraSpec { def is =
  "GET / on Flowers"                     ^
    "should return status 200"                  ! root200^
                                                p^
  "GET / on Flowers with a name param"          ^
    "should return status 200"                  ! nameParamWorks^
                                                p^
  "GET /:slug on Flowers"                       ^
    "should return status 200"                  ! slugWorks
                                                end

  addServlet(classOf[FlowersController], "/flowers/*")

  def root200 = get("/") {
    status must_== 200
  }

  def nameParamWorks = get("/?name=rose") {
    status must_== 200
  }

  def slugWorks = get("/red-rose") {
    status must_== 200
  }
}
