package controllers

import play.api._
import play.api.mvc._
import repositories._
import models._

class Application extends Controller {

  def index = Action {
    UserRepository.insert(User("test"))
    Ok(views.html.index("Your new application is ready."))
  }

}
