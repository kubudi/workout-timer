package com.kubudi

import android.view.KeyEvent
import org.scaloid.common._
import android.widget._
import utils._
import utils.Tools._

import scala.concurrent.duration._

class WorkoutTimer extends SActivity {
  var active = -1

  onCreate {
    setContentView(R.layout.main)

    val display = find[TextView](R.id.display)
    val switcher = find[ViewSwitcher](R.id.switcher)
    val stopButton = find[Button](R.id.stop)

    val counters = List(
      counter(R.id.short_break, display, switcher, stopButton, 1 minutes),
      counter(R.id.long_break, display, switcher, stopButton, 2 minutes)
    )

    stopButton.onClick {
      counters.foreach(_.stop())
      switcher.showNext()
      active = -1
    }

  }


  def counter(buttonId: Int, display: TextView, switcher: ViewSwitcher, stopButton: Button, duration: Duration): CountDownTimer = {
    val countDown = new CountDownTimer(display, stopButton, duration)

    find[Button](buttonId).onClick {
      countDown.start()
      switcher.showNext()
      active = buttonId
      popUp("basladi")
    }
    countDown
  }


  override def onKeyDown(keyCode: Int, event: KeyEvent): Boolean = {
    val stopButton = find[Button](R.id.stop)
    val shortId = R.id.short_break
    val longId = R.id.long_break
    keyCode match {
      case KeyEvent.KEYCODE_VOLUME_DOWN if active == longId =>
        stopButton.callOnClick()
      case KeyEvent.KEYCODE_VOLUME_DOWN if active == -1 =>
        find[Button](longId).callOnClick()
      case KeyEvent.KEYCODE_VOLUME_UP if active == shortId =>
        stopButton.callOnClick()
      case KeyEvent.KEYCODE_VOLUME_UP if active == -1 =>
        find[Button](shortId).callOnClick()
      case _ =>
        true
    }
  }
}


