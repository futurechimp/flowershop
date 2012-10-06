package com.futurechimps.swagger.sample

// The basic Scalatra framework code
import org.scalatra._

// JSON handling support from Scalatra
import org.scalatra.json._

// Scalatra's built-in Swagger integration
import org.scalatra.swagger._

// JSON-related libraries
import scala.collection.JavaConverters._
import org.json4s.{DefaultFormats, Formats}

// Swagger base code
import com.wordnik.swagger.core.ApiPropertiesReader

// Our models
import com.futurechimps.swagger.sample.models._

class FlowersController(implicit val swagger: Swagger) extends ScalatraServlet
  with JacksonJsonSupport with JValueResult with SwaggerSupport {

  // Sets up automatic case class to JSON output serialization, required by
  // the JValueResult trait.
  protected implicit val jsonFormats: Formats = DefaultFormats

  // The name of our application. This will show up in the Swagger docs.
  override protected val applicationName = Some("flower")

  // A description of our application. This will show up in the Swagger docs.
  protected val applicationDescription = "The flowershop API. It exposes operations for browing and searching lists of flowers"


  // Before every operation, set the output format to JSON.
  before() {
    contentType = formats("json")
  }

  models = Map(classOf[Flower])

  /**
   * Some fake flowers data so we can simulate retrievals.
   */
  lazy val flowers = List(
      Flower("yellow-tulip", "Yellow Tulip"),
      Flower("red-rose", "Red Rose"),
      Flower("black-rose", "Black Rose"))


  /*
   * Retrieve a list of flowers. It's possible to search by name by adding
   * a name=foo query string parameter.
   */
  get("/",
    summary("Show all flowers"),
    nickname("get flowers"),
    responseClass("List[Flower]"),
    parameters(Parameter("q", "query", DataType.String, required = false)),
    endpoint(""),
    notes("Shows all the flowers in the flower shop. You can search it too.")){
    params.get("name") match {
      case Some(name) => flowers filter (_.name.toLowerCase contains name.toLowerCase)
      case None => flowers
    }
  }

  /*
   * Retrieve a single book based on its slug.
   */
  get("/:slug",
    summary("Find by slug"),
    nickname("findBySlug"),
    responseClass("Flower"),
    endpoint("{slug}"),
    notes("Returns the flower for the provided slug, if a matching flower exists"),
    parameters(
      Parameter("slug", "Slug of flower that needs to be fetched",
        DataType.String,
        paramType = ParamType.Path))) {
    flowers find     (_.slug == params("slug")) match {
      case Some(b) => b
      case None => halt(404)
    }
  }

}
