package com.futurechimps.swagger.sample

import org.scalatra.swagger.{JacksonSwaggerBase, Swagger, SwaggerBase}

import org.scalatra.ScalatraServlet
import com.fasterxml.jackson.databind._
import org.json4s.jackson.Json4sScalaModule
import org.json4s.{DefaultFormats, Formats}


class ResourcesApp(implicit val swagger: Swagger) extends ScalatraServlet with JacksonSwaggerBase {
  implicit val jsonFormats: Formats = DefaultFormats
} 

class FlowersSwagger extends Swagger("1.0", "1")
