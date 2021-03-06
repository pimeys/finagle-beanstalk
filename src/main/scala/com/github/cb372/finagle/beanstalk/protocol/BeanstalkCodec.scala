package com.github.cb372.finagle.beanstalk.protocol

import org.jboss.netty.buffer.{ChannelBuffers, ChannelBuffer}

/**
 * Author: chris
 * Created: 7/27/12
 */

object BeanstalkCodec {
  val CHARSET = "UTF-8"

  val TOKEN_DELIMITER   = ' '
  val TOKEN_DELIMITER_BYTE = TOKEN_DELIMITER.toByte

  val EOL_DELIMITER     = "\r\n"
  val EOL_BYTES = EOL_DELIMITER.getBytes(CHARSET)

  def oneLineToChannelBuffer(tokens: Seq[Any]) = {
    val string = tokens.mkString(TOKEN_DELIMITER.toString) + EOL_DELIMITER
    ChannelBuffers.wrappedBuffer(string.getBytes(CHARSET))
  }

  def oneLinePlusDataToChannelBuffer(tokens: Seq[Any], data: Array[Byte]) = {
    val string = tokens.mkString(TOKEN_DELIMITER.toString) + EOL_DELIMITER
    val bytes = Array.concat(string.getBytes(CHARSET), data, EOL_BYTES)
    ChannelBuffers.wrappedBuffer(bytes)
  }
}

