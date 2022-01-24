package com.example.mvvmsampleappication

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class UtilTest {
    @Test
    fun getVersionCode() {
        val versionCode = Util.getVersionCode()
        assertThat(versionCode).isEqualTo(1)
    }

    @Test
    fun getVersionName() {
        val versionName = Util.getVersionName()
        assertThat(versionName).isEqualTo("1.0")
    }
}