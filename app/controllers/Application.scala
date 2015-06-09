package controllers

import play.api._
import play.api.mvc._
import com.github.tototoshi.play2.scalate._

import repositories._
import models._

class Application extends Controller {

  def index = Action {
    Ok(Scalate.render("index.jade", Map("title" -> "Index")))
  }

}
