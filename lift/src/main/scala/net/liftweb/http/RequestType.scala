/*
 * Copyright 2007-2008 WorldWide Conferencing, LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions
 * and limitations under the License.
 */
package net.liftweb.http

import _root_.javax.servlet.http.{HttpServletRequest}

@serializable
abstract class RequestType {
  def post_? : Boolean = false
  def get_? : Boolean = false
  def head_? : Boolean = false
  def put_? : Boolean = false
  def delete_? : Boolean = false
}

@serializable
case object GetRequest extends RequestType {
  override def get_? = true
}
@serializable
case object PostRequest extends RequestType {
  override def post_? = true
}
@serializable
case object HeadRequest extends RequestType {
  override def head_? = true
}
@serializable
case object PutRequest extends RequestType {
  override def put_? = true
}
@serializable
case object DeleteRequest extends RequestType {
  override def delete_? = true
}
@serializable
case class UnknownRequest(method: String) extends RequestType

object RequestType {
  def apply(req: HttpServletRequest): RequestType = {
    req.getMethod.toUpperCase match {
      case "GET" => GetRequest
      case "POST" => PostRequest
      case "HEAD" => HeadRequest
      case "PUT" => PutRequest
      case "DELETE" => DeleteRequest
      case meth => UnknownRequest(meth)
    }
  }
}
