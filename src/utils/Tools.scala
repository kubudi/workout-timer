package com.kubudi
package utils

import android.content.Context
import android.widget.Toast

object Tools {
  def popUp(text: String)(implicit context: Context) = Toast.makeText(context, text, Toast.LENGTH_LONG).show()
}
