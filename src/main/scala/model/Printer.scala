package model

case class Printer(id: String,
                   displayName: Option[String] = None,
                   ip: Option[String] = None,
                   url: Option[String] = None,
                   isActive: Option[Boolean] = Some(true),
                   description: Option[String] = None)