package com.kubudi
package utils

import android.content.Context
import android.os.{CountDownTimer => androidCountDown}
import android.widget.{Button, TextView}
import org.scaloid.common.{MediaHelpers => media}

import scala.concurrent.duration._

class CountDownTimer(view: TextView, stopButton: Button, duration: Duration)(implicit context: Context) {
  val interval = 1 second

  val countDown = new androidCountDown(duration.toMillis, interval.toMillis) {
    def onTick(timeLeft: Long) = {
      val minutesLeft = timeLeft / (1000 * 60)
      val secondsLeft = (timeLeft / 1000) % 60

      val secondView =
        if(secondsLeft < 10) "0" + secondsLeft
        else secondsLeft.toString
      val minuteView =
        if(minutesLeft < 10) "0" + minutesLeft
        else minutesLeft.toString

      view.setText(s"$minuteView:$secondView")
    }

    def onFinish() = {
      stopButton.callOnClick()
      media.play(media.notificationSound)
      clear()
    }
  }

  def start() = countDown.start()

  def stop() = {
    countDown.cancel()
    clear()
  }

  def clear() = view.setText("00:00")
}
