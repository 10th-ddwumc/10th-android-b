package com.example.nike
import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp  // ← 이 한 줄로 Hilt 시작, 앱 전체 "의존성 창고" 생성
class MyApplication : Application()