/*
 * Copyright (c) 2023 Kodeco Inc
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * Notwithstanding the foregoing, you may not use, copy, modify, merge, publish,
 * distribute, sublicense, create a derivative work, and/or sell copies of the
 * Software in any work that is designed, intended, or marketed for pedagogical or
 * instructional purposes related to programming, coding, application development,
 * or information technology.  Permission for such use, copying, modification,
 * merger, publication, distribution, sublicensing, creation of derivative works,
 * or sale is expressly withheld.
 *
 * This project and source code may use libraries or frameworks that are
 * released under various Open-Source licenses. Use of those libraries and
 * frameworks are governed by their own individual licenses.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.kodeco.learn.ui.main

import androidx.annotation.StringRes
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.kodeco.learn.R

sealed class BottomNavigationScreens(
  val route: String,
  @StringRes val title: Int,
  val icon: @Composable () -> Unit
) {

  data object Home : BottomNavigationScreens(
    route = "Home",
    title = R.string.navigation_home,
    icon = {
      Icon(
        painter = painterResource(R.drawable.ic_home),
        contentDescription = stringResource(R.string.navigation_home)
      )
    }
  )

  data object Bookmark : BottomNavigationScreens(
    route = "Bookmark",
    title = R.string.navigation_bookmark,
    icon = {
      Icon(
        painter = painterResource(R.drawable.ic_bookmarks),
        contentDescription = stringResource(R.string.navigation_bookmark)
      )
    }
  )

  data object Latest : BottomNavigationScreens(
    route = "Latest",
    title = R.string.navigation_latest,
    icon = {
      Icon(
        painter = painterResource(R.drawable.ic_latest),
        contentDescription = stringResource(R.string.navigation_latest)
      )
    }
  )

  data object Search : BottomNavigationScreens(
    route = "Search",
    title = R.string.navigation_search,
    icon = {
      Icon(
        painter = painterResource(R.drawable.ic_search),
        contentDescription = stringResource(R.string.navigation_search)
      )
    }
  )
}