package com.suprematic.utils

import java.util.concurrent.TimeUnit

fun Long.formatAsTime(): String   =
    String.format("%02d:%02d:%02d",
        TimeUnit.MILLISECONDS.toHours(this * 1000),
        TimeUnit.MILLISECONDS.toMinutes(this * 1000) -
                TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(this * 1000)),
        TimeUnit.MILLISECONDS.toSeconds(this * 1000) -
                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(this * 1000)));