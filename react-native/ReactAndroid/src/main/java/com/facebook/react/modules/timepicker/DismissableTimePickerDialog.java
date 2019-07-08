/**
 * Copyright (c) Facebook, Inc. and its affiliates.
 *
 * <p>This source code is licensed under the MIT license found in the LICENSE file in the root
 * directory of this source tree.
 */
package com.facebook.react.modules.timepicker;

import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Build;
import javax.annotation.Nullable;

/**
 * Certain versions of Android (Jellybean-KitKat) have a bug where when dismissed, the {@link
 * TimePickerDialog} still calls the OnTimeSetListener. This class works around that issue by *not*
 * calling super.onStop on KitKat on lower, as that would erroneously call the OnTimeSetListener
 * when the dialog is dismissed, or call it twice when "OK" is pressed.
 *
 * <p>See: <a href="https://code.google.com/p/android/issues/detail?id=34833">Issue 34833</a>
 */
public class DismissableTimePickerDialog extends TimePickerDialog {

  public DismissableTimePickerDialog(
      Context context,
      @Nullable TimePickerDialog.OnTimeSetListener callback,
      int hourOfDay,
      int minute,
      boolean is24HourView) {
    super(context, callback, hourOfDay, minute, is24HourView);
  }

  public DismissableTimePickerDialog(
      Context context,
      int theme,
      @Nullable TimePickerDialog.OnTimeSetListener callback,
      int hourOfDay,
      int minute,
      boolean is24HourView) {
    super(context, theme, callback, hourOfDay, minute, is24HourView);
  }

  @Override
  protected void onStop() {
    if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
      super.onStop();
    }
  }
}
